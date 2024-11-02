
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfStreetDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStreetDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StreetDetailed" type="{http://validator5.AddressDoctor.com/Webservice5/v4}StreetDetailed" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStreetDetailed", propOrder = {
    "streetDetailed"
})
public class ArrayOfStreetDetailed {

    @XmlElement(name = "StreetDetailed", nillable = true)
    protected List<StreetDetailed> streetDetailed;

    /**
     * Gets the value of the streetDetailed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streetDetailed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreetDetailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StreetDetailed }
     * 
     * 
     */
    public List<StreetDetailed> getStreetDetailed() {
        if (streetDetailed == null) {
            streetDetailed = new ArrayList<StreetDetailed>();
        }
        return this.streetDetailed;
    }

}
