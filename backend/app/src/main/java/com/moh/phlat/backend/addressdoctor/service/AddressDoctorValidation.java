package com.moh.phlat.backend.addressdoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moh.phlat.backend.addressdoctor.soap.Address;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfAddress;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfString;
import com.moh.phlat.backend.addressdoctor.soap.ObjectFactory;
import com.moh.phlat.backend.addressdoctor.soap.Parameters;
import com.moh.phlat.backend.addressdoctor.soap.Process;
import com.moh.phlat.backend.addressdoctor.soap.SOAPBody;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelope;
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
		
		deliveryLines.getString().add("1175 Douglas St");
		locality.getString().add("Victoria");
		province.getString().add("BC");
		country.getString().add("Canada");
		postalCode.getString().add("V8W 2E1");
		
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
		
		SOAPEnvelope addressDoctorRequest = objectFactory.createSOAPEnvelope();
		SOAPBody body = objectFactory.createSOAPBody();
		body.setProcess(process);
		addressDoctorRequest.setSoapBody(body);
		
		String addressDoctorResponse = addressDoctorService.validateAddress(control, addressDoctorRequest);
	}
	
}
