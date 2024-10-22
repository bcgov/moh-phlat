
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResidueDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResidueDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NECESSARY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUPERFLUOUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UNRECOGNIZED" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResidueDetailed", propOrder = {
    "item",
    "necessary",
    "superfluous",
    "unrecognized"
})
public class ResidueDetailed {

    @XmlElement(name = "Item")
    protected int item;
    @XmlElement(name = "NECESSARY")
    protected String necessary;
    @XmlElement(name = "SUPERFLUOUS")
    protected String superfluous;
    @XmlElement(name = "UNRECOGNIZED")
    protected String unrecognized;

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
     * Gets the value of the necessary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNECESSARY() {
        return necessary;
    }

    /**
     * Sets the value of the necessary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNECESSARY(String value) {
        this.necessary = value;
    }

    /**
     * Gets the value of the superfluous property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUPERFLUOUS() {
        return superfluous;
    }

    /**
     * Sets the value of the superfluous property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUPERFLUOUS(String value) {
        this.superfluous = value;
    }

    /**
     * Gets the value of the unrecognized property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNRECOGNIZED() {
        return unrecognized;
    }

    /**
     * Sets the value of the unrecognized property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNRECOGNIZED(String value) {
        this.unrecognized = value;
    }

}
