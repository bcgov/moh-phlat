
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Parameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceParameters" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ServiceParameters" minOccurs="0"/>
 *         &lt;element name="ValidationParameters" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ValidationParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {
    "processMode",
    "serviceParameters",
    "validationParameters"
})
public class Parameters {

    @XmlElement(name = "ProcessMode")
    protected String processMode;
    @XmlElement(name = "ServiceParameters")
    protected ServiceParameters serviceParameters;
    @XmlElement(name = "ValidationParameters")
    protected ValidationParameters validationParameters;

    /**
     * Gets the value of the processMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessMode() {
        return processMode;
    }

    /**
     * Sets the value of the processMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessMode(String value) {
        this.processMode = value;
    }

    /**
     * Gets the value of the serviceParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceParameters }
     *     
     */
    public ServiceParameters getServiceParameters() {
        return serviceParameters;
    }

    /**
     * Sets the value of the serviceParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceParameters }
     *     
     */
    public void setServiceParameters(ServiceParameters value) {
        this.serviceParameters = value;
    }

    /**
     * Gets the value of the validationParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationParameters }
     *     
     */
    public ValidationParameters getValidationParameters() {
        return validationParameters;
    }

    /**
     * Sets the value of the validationParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationParameters }
     *     
     */
    public void setValidationParameters(ValidationParameters value) {
        this.validationParameters = value;
    }

}
