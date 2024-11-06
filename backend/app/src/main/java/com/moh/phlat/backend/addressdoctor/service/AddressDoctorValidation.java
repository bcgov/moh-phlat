package com.moh.phlat.backend.addressdoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.addressdoctor.soap.Address;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfAddress;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfString;
import com.moh.phlat.backend.addressdoctor.soap.ObjectFactory;
import com.moh.phlat.backend.addressdoctor.soap.Parameters;
import com.moh.phlat.backend.addressdoctor.soap.Process;
import com.moh.phlat.backend.addressdoctor.soap.SOAPBodyInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeOutput;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import lombok.Getter;

@Component
public class AddressDoctorValidation {

	@Autowired
	@Getter
	private AddressDoctorService addressDoctorService;
	
	private static ObjectFactory objectFactory;
	static {
		objectFactory = new ObjectFactory();
	}
	
	public void validateAddress(Control control, ProcessData processData) {
		
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
		
		SOAPEnvelopeOutput addressDoctorResponse = addressDoctorService.validateAddress(control, addressDoctorRequest);
	}
	
}
