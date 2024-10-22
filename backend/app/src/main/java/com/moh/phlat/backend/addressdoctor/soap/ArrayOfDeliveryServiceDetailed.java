
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDeliveryServiceDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDeliveryServiceDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeliveryServiceDetailed" type="{http://validator5.AddressDoctor.com/Webservice5/v4}DeliveryServiceDetailed" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDeliveryServiceDetailed", propOrder = {
    "deliveryServiceDetailed"
})
public class ArrayOfDeliveryServiceDetailed {

    @XmlElement(name = "DeliveryServiceDetailed", nillable = true)
    protected List<DeliveryServiceDetailed> deliveryServiceDetailed;

    /**
     * Gets the value of the deliveryServiceDetailed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryServiceDetailed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryServiceDetailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryServiceDetailed }
     * 
     * 
     */
    public List<DeliveryServiceDetailed> getDeliveryServiceDetailed() {
        if (deliveryServiceDetailed == null) {
            deliveryServiceDetailed = new ArrayList<DeliveryServiceDetailed>();
        }
        return this.deliveryServiceDetailed;
    }

}
