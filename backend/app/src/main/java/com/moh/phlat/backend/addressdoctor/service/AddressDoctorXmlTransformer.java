package com.moh.phlat.backend.addressdoctor.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeOutput;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Component
public class AddressDoctorXmlTransformer {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressDoctorXmlTransformer.class);
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	@PostConstruct
	public void initAddressDoctorXmlTransformer() throws JAXBException{
		marshaller = JAXBContext.newInstance(SOAPEnvelopeInput.class).createMarshaller();
		unmarshaller = JAXBContext.newInstance(SOAPEnvelopeOutput.class).createUnmarshaller();
	}
	
	public String toXml(SOAPEnvelopeInput addressDoctorRequest) {
		try {
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(addressDoctorRequest, stringWriter);
			return stringWriter.toString(); 
		} catch (JAXBException ex) {
			logger.error("Could not marshall SOAPEnvelopeInput object into AddressDoctor XML due to: ", ex);
			return null;
		}
	}
	
	public SOAPEnvelopeOutput fromXml(String addressDoctorResponse) {
		try {
			InputStream inputStream = new ByteArrayInputStream(addressDoctorResponse.getBytes());
			return (SOAPEnvelopeOutput) unmarshaller.unmarshal(inputStream);
		} catch (JAXBException ex) {
			logger.error("Could not unmarshall AddressDoctor XML into SOAPEnvelopeOutput object due to: ", ex);
			return null;
		}
	}
}
