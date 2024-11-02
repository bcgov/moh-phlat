
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="JobToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CampaignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReservedXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalInformationSet" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfAdditionalInformation" minOccurs="0"/>
 *         &lt;element name="Results" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "statusCode",
    "statusMessage",
    "jobToken",
    "campaignId",
    "reservedXml",
    "additionalInformationSet",
    "results"
})
public class Response {

    @XmlElement(name = "StatusCode")
    protected int statusCode;
    @XmlElement(name = "StatusMessage")
    protected String statusMessage;
    @XmlElement(name = "JobToken")
    protected String jobToken;
    @XmlElement(name = "CampaignId")
    protected String campaignId;
    @XmlElement(name = "ReservedXml")
    protected String reservedXml;
    @XmlElement(name = "AdditionalInformationSet")
    protected ArrayOfAdditionalInformation additionalInformationSet;
    @XmlElement(name = "Results")
    protected ArrayOfResult results;

    /**
     * Gets the value of the statusCode property.
     * 
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     */
    public void setStatusCode(int value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the jobToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobToken() {
        return jobToken;
    }

    /**
     * Sets the value of the jobToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobToken(String value) {
        this.jobToken = value;
    }

    /**
     * Gets the value of the campaignId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignId() {
        return campaignId;
    }

    /**
     * Sets the value of the campaignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignId(String value) {
        this.campaignId = value;
    }

    /**
     * Gets the value of the reservedXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservedXml() {
        return reservedXml;
    }

    /**
     * Sets the value of the reservedXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservedXml(String value) {
        this.reservedXml = value;
    }

    /**
     * Gets the value of the additionalInformationSet property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalInformation }
     *     
     */
    public ArrayOfAdditionalInformation getAdditionalInformationSet() {
        return additionalInformationSet;
    }

    /**
     * Sets the value of the additionalInformationSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalInformation }
     *     
     */
    public void setAdditionalInformationSet(ArrayOfAdditionalInformation value) {
        this.additionalInformationSet = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResult }
     *     
     */
    public ArrayOfResult getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResult }
     *     
     */
    public void setResults(ArrayOfResult value) {
        this.results = value;
    }

}
