
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StreetDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StreetDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="COMPLETE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COMPLETE_WITH_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRE_DESCRIPTOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POST_DESCRIPTOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRE_DIRECTIONAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POST_DIRECTIONAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "StreetDetailed", propOrder = {
    "item",
    "complete",
    "completewithnumber",
    "name",
    "predescriptor",
    "postdescriptor",
    "predirectional",
    "postdirectional",
    "addinfo"
})
public class StreetDetailed {

    @XmlElement(name = "Item")
    protected int item;
    @XmlElement(name = "COMPLETE")
    protected String complete;
    @XmlElement(name = "COMPLETE_WITH_NUMBER")
    protected String completewithnumber;
    @XmlElement(name = "NAME")
    protected String name;
    @XmlElement(name = "PRE_DESCRIPTOR")
    protected String predescriptor;
    @XmlElement(name = "POST_DESCRIPTOR")
    protected String postdescriptor;
    @XmlElement(name = "PRE_DIRECTIONAL")
    protected String predirectional;
    @XmlElement(name = "POST_DIRECTIONAL")
    protected String postdirectional;
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
     * Gets the value of the completewithnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPLETEWITHNUMBER() {
        return completewithnumber;
    }

    /**
     * Sets the value of the completewithnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPLETEWITHNUMBER(String value) {
        this.completewithnumber = value;
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
     * Gets the value of the predescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPREDESCRIPTOR() {
        return predescriptor;
    }

    /**
     * Sets the value of the predescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPREDESCRIPTOR(String value) {
        this.predescriptor = value;
    }

    /**
     * Gets the value of the postdescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSTDESCRIPTOR() {
        return postdescriptor;
    }

    /**
     * Sets the value of the postdescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSTDESCRIPTOR(String value) {
        this.postdescriptor = value;
    }

    /**
     * Gets the value of the predirectional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPREDIRECTIONAL() {
        return predirectional;
    }

    /**
     * Sets the value of the predirectional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPREDIRECTIONAL(String value) {
        this.predirectional = value;
    }

    /**
     * Gets the value of the postdirectional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSTDIRECTIONAL() {
        return postdirectional;
    }

    /**
     * Sets the value of the postdirectional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSTDIRECTIONAL(String value) {
        this.postdirectional = value;
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
