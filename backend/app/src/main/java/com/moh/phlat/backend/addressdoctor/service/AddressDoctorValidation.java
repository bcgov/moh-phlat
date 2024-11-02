package com.moh.phlat.backend.addressdoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moh.phlat.backend.addressdoctor.soap.Address;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfAddress;
import com.moh.phlat.backend.addressdoctor.soap.ArrayOfString;
import com.moh.phlat.backend.addressdoctor.soap.ObjectFactory;
import com.moh.phlat.backend.addressdoctor.soap.Process;
import com.moh.phlat.backend.addressdoctor.soap.ProcessResponse;
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
		
		Process addressDoctorRequest = objectFactory.createProcess();
		Address address = objectFactory.createAddress();
		ArrayOfAddress arrayOfAddress = objectFactory.createArrayOfAddress();
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
		
		arrayOfAddress.getAddress().add(address);
		addressDoctorRequest.setAddresses(arrayOfAddress);
		
		ProcessResponse addressDoctorResponse = addressDoctorService.validateAddress(control, addressDoctorRequest);
		
	}
	
}
