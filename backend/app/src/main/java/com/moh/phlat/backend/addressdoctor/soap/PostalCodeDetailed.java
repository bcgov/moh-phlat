
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostalCodeDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalCodeDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FORMATTED" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UNFORMATTED" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BASE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ADD_ON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalCodeDetailed", propOrder = {
    "item",
    "formatted",
    "unformatted",
    "base",
    "addon"
})
public class PostalCodeDetailed {

    @XmlElement(name = "Item")
    protected int item;
    @XmlElement(name = "FORMATTED")
    protected String formatted;
    @XmlElement(name = "UNFORMATTED")
    protected String unformatted;
    @XmlElement(name = "BASE")
    protected String base;
    @XmlElement(name = "ADD_ON")
    protected String addon;

    /**
     * Gets the value of the item property.
     * 
     */
    public int getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     */
    public void setItem(int value) {
        this.item = value;
    }

    /**
     * Gets the value of the formatted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFORMATTED() {
        return formatted;
    }

    /**
     * Sets the value of the formatted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFORMATTED(String value) {
        this.formatted = value;
    }

    /**
     * Gets the value of the unformatted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNFORMATTED() {
        return unformatted;
    }

    /**
     * Sets the value of the unformatted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNFORMATTED(String value) {
        this.unformatted = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBASE() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBASE(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the addon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDON() {
        return addon;
    }

    /**
     * Sets the value of the addon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDON(String value) {
        this.addon = value;
    }

}
