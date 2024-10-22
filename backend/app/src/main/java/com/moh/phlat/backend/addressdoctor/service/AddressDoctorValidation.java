package com.moh.phlat.backend.addressdoctor.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.moh.phlat.backend.addressdoctor.soap.ObjectFactory;
import com.moh.phlat.backend.addressdoctor.soap.Process;
import com.moh.phlat.backend.addressdoctor.soap.ProcessResponse;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import lombok.Getter;

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
		ProcessResponse addressDoctorResponse = addressDoctorService.validateAddress(control, addressDoctorRequest);
		
	}
	
}
