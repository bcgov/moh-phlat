
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSubBuildingDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSubBuildingDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubBuildingDetailed" type="{http://validator5.AddressDoctor.com/Webservice5/v4}SubBuildingDetailed" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSubBuildingDetailed", propOrder = {
    "subBuildingDetailed"
})
public class ArrayOfSubBuildingDetailed {

    @XmlElement(name = "SubBuildingDetailed", nillable = true)
    protected List<SubBuildingDetailed> subBuildingDetailed;

    /**
     * Gets the value of the subBuildingDetailed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subBuildingDetailed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubBuildingDetailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubBuildingDetailed }
     * 
     * 
     */
    public List<SubBuildingDetailed> getSubBuildingDetailed() {
        if (subBuildingDetailed == null) {
            subBuildingDetailed = new ArrayList<SubBuildingDetailed>();
        }
        return this.subBuildingDetailed;
    }

}
