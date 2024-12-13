
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JobToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CampaignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReservedXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UseTransactionPool" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceParameters", propOrder = {
    "jobToken",
    "campaignId",
    "reservedXml",
    "useTransactionPool"
})
public class ServiceParameters {

    @XmlElement(name = "JobToken")
    protected String jobToken;
    @XmlElement(name = "CampaignId")
    protected String campaignId;
    @XmlElement(name = "ReservedXml")
    protected String reservedXml;
    @XmlElement(name = "UseTransactionPool")
    protected String useTransactionPool;

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
     * Gets the value of the useTransactionPool property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseTransactionPool() {
        return useTransactionPool;
    }

    /**
     * Sets the value of the useTransactionPool property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseTransactionPool(String value) {
        this.useTransactionPool = value;
    }

}
