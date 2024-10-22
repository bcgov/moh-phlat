
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Standardize complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Standardize">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Element" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Casing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxItemCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Standardize", propOrder = {
    "element",
    "casing",
    "maxLength",
    "maxItemCount"
})
public class Standardize {

    @XmlElement(name = "Element")
    protected String element;
    @XmlElement(name = "Casing")
    protected String casing;
    @XmlElement(name = "MaxLength")
    protected int maxLength;
    @XmlElement(name = "MaxItemCount")
    protected int maxItemCount;

    /**
     * Gets the value of the element property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElement() {
        return element;
    }

    /**
     * Sets the value of the element property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElement(String value) {
        this.element = value;
    }

    /**
     * Gets the value of the casing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCasing() {
        return casing;
    }

    /**
     * Sets the value of the casing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCasing(String value) {
        this.casing = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     */
    public void setMaxLength(int value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the maxItemCount property.
     * 
     */
    public int getMaxItemCount() {
        return maxItemCount;
    }

    /**
     * Sets the value of the maxItemCount property.
     * 
     */
    public void setMaxItemCount(int value) {
        this.maxItemCount = value;
    }

}
