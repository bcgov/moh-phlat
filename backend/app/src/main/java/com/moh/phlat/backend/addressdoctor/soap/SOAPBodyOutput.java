package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
    "processResponse",
    "fault"
})
public class SOAPBodyOutput {

	@XmlElement(name = "ProcessResponse")
	protected ProcessResponse processResponse;

	@XmlElement(name = "Fault")
	protected SOAPFault fault;
	
}
