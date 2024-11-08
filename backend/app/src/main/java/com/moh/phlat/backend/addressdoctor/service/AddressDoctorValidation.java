package com.moh.phlat.backend.addressdoctor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.addressdoctor.soap.Address;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfAddress;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfString;
import com.moh.phlat.backend.addressdoctor.soap.ObjectFactory;
import com.moh.phlat.backend.addressdoctor.soap.Parameters;
import com.moh.phlat.backend.addressdoctor.soap.Process;
import com.moh.phlat.backend.addressdoctor.soap.ProcessResponse;
import com.moh.phlat.backend.addressdoctor.soap.Response;
import com.moh.phlat.backend.addressdoctor.soap.Result;
import com.moh.phlat.backend.addressdoctor.soap.ResultData;
import com.moh.phlat.backend.addressdoctor.soap.SOAPBodyInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPBodyOutput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeOutput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPFault;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.MessageSourceSystem;

import lombok.Getter;

@Component
public class AddressDoctorValidation {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressDoctorValidation.class);
	
	@Autowired
	@Getter
	private AddressDoctorService addressDoctorService;
	
	private static ObjectFactory objectFactory;
	static {
		objectFactory = new ObjectFactory();
	}
	
	public AddressDoctorResults validateAddress(Control control, ProcessData processData) {
		
		SOAPEnvelopeInput addressDoctorRequest = convertToAddressDoctorRequest(processData);
		SOAPEnvelopeOutput addressDoctorResponse = addressDoctorService.validateAddress(control, addressDoctorRequest);
		return processAddressDoctorResults(addressDoctorResponse, processData);
		
	}
	
	private SOAPEnvelopeInput convertToAddressDoctorRequest(ProcessData processData) {
		
		Process process = objectFactory.createProcess();
		
		Address address = objectFactory.createAddress();
		ArrayOfString deliveryLines = objectFactory.createArrayOfString();
		ArrayOfString locality = objectFactory.createArrayOfString();
		ArrayOfString province = objectFactory.createArrayOfString();
		ArrayOfString country = objectFactory.createArrayOfString();
		ArrayOfString postalCode = objectFactory.createArrayOfString();
		
		deliveryLines.getString().add(processData.getPhysicalAddr1());
		if (StringUtils.hasText(processData.getPhysicalAddr2())) {
			deliveryLines.getString().add(processData.getPhysicalAddr2());
		}
		if (StringUtils.hasText(processData.getPhysicalAddr3())) {
			deliveryLines.getString().add(processData.getPhysicalAddr3());
		}
		if (StringUtils.hasText(processData.getPhysicalAddr4())) {
			deliveryLines.getString().add(processData.getPhysicalAddr4());
		}
		locality.getString().add(processData.getPhysicalCity());
		province.getString().add(processData.getPhysicalProvince());
		country.getString().add(processData.getPhysicalCountry());
		postalCode.getString().add(processData.getPhysicalPcode());
		
		address.setDeliveryAddressLines(deliveryLines);
		address.setLocality(locality);
		address.setProvince(province);
		address.setCountry(country);
		address.setPostalCode(postalCode);
		
		ArrayOfAddress arrayOfAddress = objectFactory.createArrayOfAddress();
		arrayOfAddress.getAddress().add(address);
		process.setAddresses(arrayOfAddress);
		
		Parameters parameters = objectFactory.createParameters();
		parameters.setProcessMode("CERTIFIED");
		process.setParameters(parameters);
		
		SOAPEnvelopeInput addressDoctorRequest = objectFactory.createSOAPEnvelopeInput();
		SOAPBodyInput body = objectFactory.createSOAPBodyInput();
		body.setProcess(process);
		addressDoctorRequest.setSoapBody(body);
		
		return addressDoctorRequest;
	}
	
	private AddressDoctorResults processAddressDoctorResults(SOAPEnvelopeOutput addressDoctorResponse, ProcessData processData) {
		
		AddressDoctorResults addressDoctorResults = new AddressDoctorResults();
		
		SOAPBodyOutput soapBody = addressDoctorResponse.getSoapBody();
		if (soapBody == null) {
			logger.error("The SOAP Body message is missing due to a possible PHLAT misconfiguration or an issue with AddressDoctor");
			addError(processData, "ValidationError", "ERROR", "Could not reach or get a response from the AddressDoctor service");
			addressDoctorResults.setError(true);
			return addressDoctorResults;
		} else if (soapBody.getFault() != null) {
			SOAPFault soapFault = soapBody.getFault();
			logger.error("SOAPFault error occured attempting to call AddressDoctor\r\nFault Code: {}\r\nFault String: {}", soapFault.getFaultcode(), soapFault.getFaultstring());
			addError(processData, "ValidationError", "ERROR", "The AddressDoctor service could not process the request");
			addressDoctorResults.setError(true);
			return addressDoctorResults;
		}
		
		ProcessResponse processResponse = soapBody.getProcessResponse();
		if (processResponse != null) {
			
			Response response = processResponse.getProcessResult();
			if (response.getStatusCode() != 100 && !response.getStatusMessage().equals("OK")) {
				logger.error("AddressDoctor returned an error response: {} | {}", response.getStatusCode(), response.getStatusMessage());
				addError(processData, "ValidationError", "ERROR", "The AddressDoctor service could not process the request");
				addressDoctorResults.setError(true);
				return addressDoctorResults;
			}
			
			Result result = response.getResults().getResult().get(0);
			addressDoctorResults.setProcessStatus(result.getProcessStatus());
			
			ResultData resultData = result.getResultDataSet().getResultData().get(0);
			addressDoctorResults.setMailabilityScore(resultData.getMailabilityScore());
			addressDoctorResults.setResultPercentage(resultData.getResultPercentage());
			addressDoctorResults.setAddress(resultData.getAddress());
		}
		return addressDoctorResults;
	}
	
	private void addError(ProcessData processData, String errorCode, String errorType, String errorMessage) {
		
		Message msg = Message.builder()
				 .messageType(errorType)
				 .messageCode(errorCode)
				 .messageDesc(errorMessage)
				 .sourceSystemName(MessageSourceSystem.PHLAT)
				 .processData(processData)
				 .build();
		processData.getMessages().add(msg);
	}
	
}
