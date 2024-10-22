
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultDataSet" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfResultData" minOccurs="0"/>
 *         &lt;element name="Enrichments" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfEnrichment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = {
    "processStatus",
    "errorMessage",
    "countryISO3",
    "resultDataSet",
    "enrichments"
})
public class Result {

    @XmlElement(name = "ProcessStatus")
    protected String processStatus;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "CountryISO3")
    protected String countryISO3;
    @XmlElement(name = "ResultDataSet")
    protected ArrayOfResultData resultDataSet;
    @XmlElement(name = "Enrichments")
    protected ArrayOfEnrichment enrichments;

    /**
     * Gets the value of the processStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessStatus() {
        return processStatus;
    }

    /**
     * Sets the value of the processStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessStatus(String value) {
        this.processStatus = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the countryISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryISO3() {
        return countryISO3;
    }

    /**
     * Sets the value of the countryISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryISO3(String value) {
        this.countryISO3 = value;
    }

    /**
     * Gets the value of the resultDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResultData }
     *     
     */
    public ArrayOfResultData getResultDataSet() {
        return resultDataSet;
    }

    /**
     * Sets the value of the resultDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResultData }
     *     
     */
    public void setResultDataSet(ArrayOfResultData value) {
        this.resultDataSet = value;
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
