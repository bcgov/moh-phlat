
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPostalCodeDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPostalCodeDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PostalCodeDetailed" type="{http://validator5.AddressDoctor.com/Webservice5/v4}PostalCodeDetailed" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPostalCodeDetailed", propOrder = {
    "postalCodeDetailed"
})
public class ArrayOfPostalCodeDetailed {

    @XmlElement(name = "PostalCodeDetailed", nillable = true)
    protected List<PostalCodeDetailed> postalCodeDetailed;

    /**
     * Gets the value of the postalCodeDetailed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postalCodeDetailed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostalCodeDetailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PostalCodeDetailed }
     * 
     * 
     */
    public List<PostalCodeDetailed> getPostalCodeDetailed() {
        if (postalCodeDetailed == null) {
            postalCodeDetailed = new ArrayList<PostalCodeDetailed>();
        }
        return this.postalCodeDetailed;
    }

}
