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
	
	public void validateAddresses(Control control, ProcessData processData) {
		
		SOAPEnvelopeInput physicalRequest = physicalAddressToAddressDoctorRequest(processData);
		SOAPEnvelopeOutput physicalResponse = addressDoctorService.validateAddress(control, physicalRequest);
		processPhysicalAddressResult(physicalResponse, processData);
		
		if (StringUtils.hasText(processData.getMailAddr1()) && StringUtils.hasText(processData.getMailCity())) {
			SOAPEnvelopeInput mailingRequest = mailingAddressToAddressDoctorRequest(processData);
			SOAPEnvelopeOutput mailingResponse = addressDoctorService.validateAddress(control, mailingRequest);
			processMailingAddressResult(mailingResponse, processData);
		}
		
	}
	
	private SOAPEnvelopeInput physicalAddressToAddressDoctorRequest(ProcessData processData) {
		return addressToAddressDoctorRequest(processData, true);
	}
	
	private SOAPEnvelopeInput mailingAddressToAddressDoctorRequest(ProcessData processData) {
		return addressToAddressDoctorRequest(processData, false);
	}
	
	private SOAPEnvelopeInput addressToAddressDoctorRequest(ProcessData processData, boolean isPhysical) {
		
		Process process = objectFactory.createProcess();
		
		Address address = objectFactory.createAddress();
		ArrayOfString deliveryLines = objectFactory.createArrayOfString();
		ArrayOfString locality = objectFactory.createArrayOfString();
		ArrayOfString province = objectFactory.createArrayOfString();
		ArrayOfString country = objectFactory.createArrayOfString();
		ArrayOfString postalCode = objectFactory.createArrayOfString();
		
		String processAddr1 = isPhysical ? processData.getPhysicalAddr1() : processData.getMailAddr1();
		String processAddr2 = isPhysical ? processData.getPhysicalAddr2() : processData.getMailAddr2();
		String processAddr3 = isPhysical ? processData.getPhysicalAddr3() : processData.getMailAddr3();
		String processAddr4 = isPhysical ? processData.getPhysicalAddr4() : processData.getMailAddr4();
		String processCity = isPhysical ? processData.getPhysicalCity() : processData.getMailCity();
		String processProvinceBc = isPhysical ? processData.getPhysicalProvince() : processData.getMailProvince();
		String processCountry = isPhysical ? processData.getPhysicalCountry() : processData.getMailCountry();
		String processPcode = isPhysical ? processData.getPhysicalPcode() : processData.getMailPcode();
		
		// If Country is empty, assume it's a Canadian address
		if (!StringUtils.hasText(processCountry)) {
			processCountry = "CA";
		}
		
		deliveryLines.getString().add(processAddr1);
		if (StringUtils.hasText(processAddr2)) {
			deliveryLines.getString().add(processAddr2);
		}
		if (StringUtils.hasText(processAddr3)) {
			deliveryLines.getString().add(processAddr3);
		}
		if (StringUtils.hasText(processAddr4)) {
			deliveryLines.getString().add(processAddr4);
		}
		locality.getString().add(processCity);
		province.getString().add(processProvinceBc);
		country.getString().add(processCountry);
		postalCode.getString().add(processPcode);
		
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
	
	private void processPhysicalAddressResult(SOAPEnvelopeOutput addressDoctorResponse, ProcessData processData) {
		
		Result result = parseAdressDoctorResult(addressDoctorResponse, processData);
		if (result == null) {
			return;
		}
		processData.setPhysicalAddrValidationStatus(result.getProcessStatus());
		
		if (result.getResultDataSet().getResultData().isEmpty()) {
			return;
		}
		ResultData resultData = result.getResultDataSet().getResultData().get(0);
		processData.setPhysicalAddrMailabilityScore(resultData.getMailabilityScore());
		
		Address address = resultData.getAddress();
		// Address Lines
		if (!address.getDeliveryAddressLines().getString().isEmpty()) {
			processData.setPhysicalAddr1(address.getDeliveryAddressLines().getString().get(0));
		}
		if (address.getDeliveryAddressLines().getString().size() > 1) {
			processData.setPhysicalAddr2(address.getDeliveryAddressLines().getString().get(1));
		}
		if (address.getDeliveryAddressLines().getString().size() > 2) {
			processData.setPhysicalAddr3(address.getDeliveryAddressLines().getString().get(2));
		}
		if (address.getDeliveryAddressLines().getString().size() > 3) {
			processData.setPhysicalAddr4(address.getDeliveryAddressLines().getString().get(3));
		}
		// City, Province, Country and PostalCode
		if (!address.getLocality().getString().isEmpty()) {
			processData.setPhysicalCity(address.getLocality().getString().get(0));
		}
		if (!address.getProvince().getString().isEmpty()) {
			processData.setPhysicalProvince(address.getProvince().getString().get(0));
		}
		if (!address.getCountry().getString().isEmpty()) {
			processData.setPhysicalCountry(address.getCountry().getString().get(0));
		}
		if (!address.getPostalCode().getString().isEmpty()) {
			processData.setPhysicalPcode(address.getPostalCode().getString().get(0));
		}
		// Civic Address
		if (!address.getHouseNumber().getString().isEmpty()
				&& !address.getStreet().getString().isEmpty()
				&& !address.getLocality().getString().isEmpty()
				&& !address.getProvince().getString().isEmpty()) {
			String civicAddress = address.getHouseNumber().getString().get(0) + " "
					+ address.getStreet().getString().get(0) + ", "
					+ address.getLocality().getString().get(0) + ", "
					+ address.getProvince().getString().get(0);
			processData.setFacCivicAddr(civicAddress);
		}
		if (!address.getBuilding().getString().isEmpty()) {
			processData.setFacBuildingName(address.getBuilding().getString().get(0));
		}
		if (!address.getSubBuilding().getString().isEmpty()) {
			processData.setFacAddressUnit(address.getSubBuilding().getString().get(0));
		}
	}
	
	private void processMailingAddressResult(SOAPEnvelopeOutput addressDoctorResponse, ProcessData processData) {
		
		Result result = parseAdressDoctorResult(addressDoctorResponse, processData);
		if (result == null) {
			return;
		}
		processData.setMailAddrValidationStatus(result.getProcessStatus());

		if (result.getResultDataSet().getResultData().isEmpty()) {
			return;
		}
		ResultData resultData = result.getResultDataSet().getResultData().get(0);
		processData.setMailAddrMailabilityScore(resultData.getMailabilityScore());
		
		Address address = resultData.getAddress();
		// Address Lines
		if (!address.getDeliveryAddressLines().getString().isEmpty()) {
			processData.setMailAddr1(address.getDeliveryAddressLines().getString().get(0));
		}
		if (address.getDeliveryAddressLines().getString().size() > 1) {
			processData.setMailAddr2(address.getDeliveryAddressLines().getString().get(1));
		}
		if (address.getDeliveryAddressLines().getString().size() > 2) {
			processData.setMailAddr3(address.getDeliveryAddressLines().getString().get(2));
		}
		if (address.getDeliveryAddressLines().getString().size() > 3) {
			processData.setMailAddr4(address.getDeliveryAddressLines().getString().get(3));
		}
		// City, Province, Country and PostalCode
		if (!address.getLocality().getString().isEmpty()) {
			processData.setMailCity(address.getLocality().getString().get(0));
		}
		if (!address.getProvince().getString().isEmpty()) {
			processData.setMailProvince(address.getProvince().getString().get(0));
		}
		if (!address.getCountry().getString().isEmpty()) {
			processData.setMailCountry(address.getCountry().getString().get(0));
		}
		if (!address.getPostalCode().getString().isEmpty()) {
			processData.setMailPcode(address.getPostalCode().getString().get(0));
		}
	}
	
	private Result parseAdressDoctorResult(SOAPEnvelopeOutput addressDoctorResponse, ProcessData processData) {
		
		if (addressDoctorResponse == null) {
			addError(processData, "100", "ERROR", "Could not reach or get a response from the AddressDoctor service");
			return null;
		}
		
		SOAPBodyOutput soapBody = addressDoctorResponse.getSoapBody();
		if (soapBody == null) {
			logger.error("The SOAP Body message is missing due to a possible PHLAT misconfiguration or an issue with AddressDoctor");
			addError(processData, "100", "ERROR", "Could not reach or get a response from the AddressDoctor service");
			return null;
		} else if (soapBody.getFault() != null) {
			SOAPFault soapFault = soapBody.getFault();
			logger.error("SOAPFault error occured attempting to call AddressDoctor\r\nFault Code: {}\r\nFault String: {}", soapFault.getFaultcode(), soapFault.getFaultstring());
			addError(processData, "100", "ERROR", "The AddressDoctor service could not process the request");
			return null;
		}
		
		ProcessResponse processResponse = soapBody.getProcessResponse();
		if (processResponse == null) {
			logger.error("Could not find the ProcessResponse object due to an unparsable or unexpected AddressDoctor response");
			addError(processData, "100", "ERROR", "The AddressDoctor service could not process the request");
			return null;
		}
			
		Response response = processResponse.getProcessResult();
		if (response.getStatusCode() != 100 && !response.getStatusMessage().equals("OK")) {
			logger.error("AddressDoctor returned an error response: {} | {}", response.getStatusCode(), response.getStatusMessage());
			addError(processData, "100", "ERROR", "The AddressDoctor service could not process the request");
			return null;
		}
		
		return response.getResults().getResult().get(0);
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
		processData.setRowstatusCode("INVALID");
	}
	
}
