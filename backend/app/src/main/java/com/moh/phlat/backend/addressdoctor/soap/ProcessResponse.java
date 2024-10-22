
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessResult" type="{http://validator5.AddressDoctor.com/Webservice5/v4}Response" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processResult"
})
@XmlRootElement(name = "ProcessResponse")
public class ProcessResponse {

    @XmlElement(name = "ProcessResult")
    protected Response processResult;

    /**
     * Gets the value of the processResult property.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getProcessResult() {
        return processResult;
    }

    /**
     * Sets the value of the processResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setProcessResult(Response value) {
        this.processResult = value;
    }

}
