package com.moh.phlat.backend.addressdoctor.service;

import com.moh.phlat.backend.addressdoctor.soap.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDoctorResults {
	
	private boolean error = false;
	
	private String processStatus;
	private String mailabilityScore;
	private String resultPercentage;
	
	private Address address;
	
}
