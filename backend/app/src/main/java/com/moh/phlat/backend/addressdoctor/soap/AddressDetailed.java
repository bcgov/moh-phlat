
package com.moh.phlat.backend.addressdoctor.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressDetailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressDetailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Organization" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfOrganizationDetailed" minOccurs="0"/>
 *         &lt;element name="Contact" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfContactDetailed" minOccurs="0"/>
 *         &lt;element name="Building" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfBuildingDetailed" minOccurs="0"/>
 *         &lt;element name="SubBuilding" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfSubBuildingDetailed" minOccurs="0"/>
 *         &lt;element name="Street" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfStreetDetailed" minOccurs="0"/>
 *         &lt;element name="HouseNumber" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfHouseNumberDetailed" minOccurs="0"/>
 *         &lt;element name="DeliveryService" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfDeliveryServiceDetailed" minOccurs="0"/>
 *         &lt;element name="Locality" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfLocalityDetailed" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfPostalCodeDetailed" minOccurs="0"/>
 *         &lt;element name="Province" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfProvinceDetailed" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfCountryDetailed" minOccurs="0"/>
 *         &lt;element name="Residue" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfResidueDetailed" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressDetailed", propOrder = {
    "organization",
    "contact",
    "building",
    "subBuilding",
    "street",
    "houseNumber",
    "deliveryService",
    "locality",
    "postalCode",
    "province",
    "country",
    "residue"
})
public class AddressDetailed {

    @XmlElement(name = "Organization")
    protected ArrayOfOrganizationDetailed organization;
    @XmlElement(name = "Contact")
    protected ArrayOfContactDetailed contact;
    @XmlElement(name = "Building")
    protected ArrayOfBuildingDetailed building;
    @XmlElement(name = "SubBuilding")
    protected ArrayOfSubBuildingDetailed subBuilding;
    @XmlElement(name = "Street")
    protected ArrayOfStreetDetailed street;
    @XmlElement(name = "HouseNumber")
    protected ArrayOfHouseNumberDetailed houseNumber;
    @XmlElement(name = "DeliveryService")
    protected ArrayOfDeliveryServiceDetailed deliveryService;
    @XmlElement(name = "Locality")
    protected ArrayOfLocalityDetailed locality;
    @XmlElement(name = "PostalCode")
    protected ArrayOfPostalCodeDetailed postalCode;
    @XmlElement(name = "Province")
    protected ArrayOfProvinceDetailed province;
    @XmlElement(name = "Country")
    protected ArrayOfCountryDetailed country;
    @XmlElement(name = "Residue")
    protected ArrayOfResidueDetailed residue;

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrganizationDetailed }
     *     
     */
    public ArrayOfOrganizationDetailed getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrganizationDetailed }
     *     
     */
    public void setOrganization(ArrayOfOrganizationDetailed value) {
        this.organization = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfContactDetailed }
     *     
     */
    public ArrayOfContactDetailed getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfContactDetailed }
     *     
     */
    public void setContact(ArrayOfContactDetailed value) {
        this.contact = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBuildingDetailed }
     *     
     */
    public ArrayOfBuildingDetailed getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBuildingDetailed }
     *     
     */
    public void setBuilding(ArrayOfBuildingDetailed value) {
        this.building = value;
    }

    /**
     * Gets the value of the subBuilding property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubBuildingDetailed }
     *     
     */
    public ArrayOfSubBuildingDetailed getSubBuilding() {
        return subBuilding;
    }

    /**
     * Sets the value of the subBuilding property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubBuildingDetailed }
     *     
     */
    public void setSubBuilding(ArrayOfSubBuildingDetailed value) {
        this.subBuilding = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfStreetDetailed }
     *     
     */
    public ArrayOfStreetDetailed getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfStreetDetailed }
     *     
     */
    public void setStreet(ArrayOfStreetDetailed value) {
        this.street = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHouseNumberDetailed }
     *     
     */
    public ArrayOfHouseNumberDetailed getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHouseNumberDetailed }
     *     
     */
    public void setHouseNumber(ArrayOfHouseNumberDetailed value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the deliveryService property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDeliveryServiceDetailed }
     *     
     */
    public ArrayOfDeliveryServiceDetailed getDeliveryService() {
        return deliveryService;
    }

    /**
     * Sets the value of the deliveryService property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDeliveryServiceDetailed }
     *     
     */
    public void setDeliveryService(ArrayOfDeliveryServiceDetailed value) {
        this.deliveryService = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLocalityDetailed }
     *     
     */
    public ArrayOfLocalityDetailed getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLocalityDetailed }
     *     
     */
    public void setLocality(ArrayOfLocalityDetailed value) {
        this.locality = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPostalCodeDetailed }
     *     
     */
    public ArrayOfPostalCodeDetailed getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPostalCodeDetailed }
     *     
     */
    public void setPostalCode(ArrayOfPostalCodeDetailed value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProvinceDetailed }
     *     
     */
    public ArrayOfProvinceDetailed getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProvinceDetailed }
     *     
     */
    public void setProvince(ArrayOfProvinceDetailed value) {
        this.province = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCountryDetailed }
     *     
     */
    public ArrayOfCountryDetailed getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCountryDetailed }
     *     
     */
    public void setCountry(ArrayOfCountryDetailed value) {
        this.country = value;
    }

    /**
     * Gets the value of the residue property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResidueDetailed }
     *     
     */
    public ArrayOfResidueDetailed getResidue() {
        return residue;
    }

    /**
     * Sets the value of the residue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResidueDetailed }
     *     
     */
    public void setResidue(ArrayOfResidueDetailed value) {
        this.residue = value;
    }

}
