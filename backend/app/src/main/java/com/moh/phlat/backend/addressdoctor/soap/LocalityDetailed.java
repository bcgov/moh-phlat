
package com.moh.phlat.backend.addressdoctor.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocalityDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocalityDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMPLETE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PREFERRED_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SORTING_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ADD_INFO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocalityDetailed", propOrder = {
    "item",
    "complete",
    "name",
    "preferredname",
    "sortingcode",
    "addinfo"
})
public class LocalityDetailed {

    @XmlElement(name = "Item")
    protected int item;
    @XmlElement(name = "COMPLETE")
    protected String complete;
    @XmlElement(name = "NAME")
    protected String name;
    @XmlElement(name = "PREFERRED_NAME")
    protected String preferredname;
    @XmlElement(name = "SORTING_CODE")
    protected String sortingcode;
    @XmlElement(name = "ADD_INFO")
    protected String addinfo;

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
     * Gets the value of the complete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPLETE() {
        return complete;
    }

    /**
     * Sets the value of the complete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPLETE(String value) {
        this.complete = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the preferredname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPREFERREDNAME() {
        return preferredname;
    }

    /**
     * Sets the value of the preferredname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPREFERREDNAME(String value) {
        this.preferredname = value;
    }

    /**
     * Gets the value of the sortingcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSORTINGCODE() {
        return sortingcode;
    }

    /**
     * Sets the value of the sortingcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSORTINGCODE(String value) {
        this.sortingcode = value;
    }

    /**
     * Gets the value of the addinfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDINFO() {
        return addinfo;
    }

    /**
     * Sets the value of the addinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDINFO(String value) {
        this.addinfo = value;
    }

}
