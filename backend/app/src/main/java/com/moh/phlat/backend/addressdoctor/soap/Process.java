
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="parameters" type="{http://validator5.AddressDoctor.com/Webservice5/v4}Parameters" minOccurs="0"/>
 *         &lt;element name="addresses" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfAddress" minOccurs="0"/>
 *         &lt;element name="enrichments" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfEnrichment" minOccurs="0"/>
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
    "parameters",
    "addresses",
    "enrichments"
})
@XmlRootElement(name = "Process")
public class Process {

    protected Parameters parameters;
    protected ArrayOfAddress addresses;
    protected ArrayOfEnrichment enrichments;

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddress }
     *     
     */
    public ArrayOfAddress getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddress }
     *     
     */
    public void setAddresses(ArrayOfAddress value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the enrichments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnrichment }
     *     
     */
    public ArrayOfEnrichment getEnrichments() {
        return enrichments;
    }

    /**
     * Sets the value of the enrichments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnrichment }
     *     
     */
    public void setEnrichments(ArrayOfEnrichment value) {
        this.enrichments = value;
    }

}
