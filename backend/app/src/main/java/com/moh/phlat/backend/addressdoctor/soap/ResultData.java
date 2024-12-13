
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailabilityScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultPercentage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ElementInputStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ElementResultStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ElementRelevance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtElementStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressResolutionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LanguageISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://validator5.AddressDoctor.com/Webservice5/v4}Address" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultData", propOrder = {
    "resultNumber",
    "mailabilityScore",
    "resultPercentage",
    "elementInputStatus",
    "elementResultStatus",
    "elementRelevance",
    "extElementStatus",
    "addressResolutionCode",
    "addressType",
    "languageISO3",
    "address"
})
public class ResultData {

    @XmlElement(name = "ResultNumber")
    protected String resultNumber;
    @XmlElement(name = "MailabilityScore")
    protected String mailabilityScore;
    @XmlElement(name = "ResultPercentage")
    protected String resultPercentage;
    @XmlElement(name = "ElementInputStatus")
    protected String elementInputStatus;
    @XmlElement(name = "ElementResultStatus")
    protected String elementResultStatus;
    @XmlElement(name = "ElementRelevance")
    protected String elementRelevance;
    @XmlElement(name = "ExtElementStatus")
    protected String extElementStatus;
    @XmlElement(name = "AddressResolutionCode")
    protected String addressResolutionCode;
    @XmlElement(name = "AddressType")
    protected String addressType;
    @XmlElement(name = "LanguageISO3")
    protected String languageISO3;
    @XmlElement(name = "Address")
    protected Address address;

    /**
     * Gets the value of the resultNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultNumber() {
        return resultNumber;
    }

    /**
     * Sets the value of the resultNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultNumber(String value) {
        this.resultNumber = value;
    }

    /**
     * Gets the value of the mailabilityScore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailabilityScore() {
        return mailabilityScore;
    }

    /**
     * Sets the value of the mailabilityScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailabilityScore(String value) {
        this.mailabilityScore = value;
    }

    /**
     * Gets the value of the resultPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultPercentage() {
        return resultPercentage;
    }

    /**
     * Sets the value of the resultPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultPercentage(String value) {
        this.resultPercentage = value;
    }

    /**
     * Gets the value of the elementInputStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementInputStatus() {
        return elementInputStatus;
    }

    /**
     * Sets the value of the elementInputStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementInputStatus(String value) {
        this.elementInputStatus = value;
    }

    /**
     * Gets the value of the elementResultStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementResultStatus() {
        return elementResultStatus;
    }

    /**
     * Sets the value of the elementResultStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementResultStatus(String value) {
        this.elementResultStatus = value;
    }

    /**
     * Gets the value of the elementRelevance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementRelevance() {
        return elementRelevance;
    }

    /**
     * Sets the value of the elementRelevance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementRelevance(String value) {
        this.elementRelevance = value;
    }

    /**
     * Gets the value of the extElementStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtElementStatus() {
        return extElementStatus;
    }

    /**
     * Sets the value of the extElementStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtElementStatus(String value) {
        this.extElementStatus = value;
    }

    /**
     * Gets the value of the addressResolutionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressResolutionCode() {
        return addressResolutionCode;
    }

    /**
     * Sets the value of the addressResolutionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressResolutionCode(String value) {
        this.addressResolutionCode = value;
    }

    /**
     * Gets the value of the addressType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * Sets the value of the addressType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressType(String value) {
        this.addressType = value;
    }

    /**
     * Gets the value of the languageISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageISO3() {
        return languageISO3;
    }

    /**
     * Sets the value of the languageISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageISO3(String value) {
        this.languageISO3 = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

}
