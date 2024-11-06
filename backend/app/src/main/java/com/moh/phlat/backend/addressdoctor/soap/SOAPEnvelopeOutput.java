package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "soapBody"
})
@XmlRootElement(name = "Envelope")
public class SOAPEnvelopeOutput {

	@XmlElement(name = "Body")
    protected SOAPBodyOutput soapBody;
	
}
