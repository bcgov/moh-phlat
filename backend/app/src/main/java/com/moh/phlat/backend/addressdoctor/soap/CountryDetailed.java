
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CountryDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CountryDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NAME_EN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ABBREVIATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISO2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISO_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CountryDetailed", propOrder = {
    "item",
    "nameen",
    "abbreviation",
    "iso2",
    "iso3",
    "isonumber"
})
public class CountryDetailed {

    @XmlElement(name = "Item")
    protected int item;
    @XmlElement(name = "NAME_EN")
    protected String nameen;
    @XmlElement(name = "ABBREVIATION")
    protected String abbreviation;
    @XmlElement(name = "ISO2")
    protected String iso2;
    @XmlElement(name = "ISO3")
    protected String iso3;
    @XmlElement(name = "ISO_NUMBER")
    protected String isonumber;

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
     * Gets the value of the nameen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAMEEN() {
        return nameen;
    }

    /**
     * Sets the value of the nameen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAMEEN(String value) {
        this.nameen = value;
    }

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getABBREVIATION() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setABBREVIATION(String value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the iso2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISO2() {
        return iso2;
    }

    /**
     * Sets the value of the iso2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISO2(String value) {
        this.iso2 = value;
    }

    /**
     * Gets the value of the iso3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISO3() {
        return iso3;
    }

    /**
     * Sets the value of the iso3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISO3(String value) {
        this.iso3 = value;
    }

    /**
     * Gets the value of the isonumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISONUMBER() {
        return isonumber;
    }

    /**
     * Sets the value of the isonumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISONUMBER(String value) {
        this.isonumber = value;
    }

}
