
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Organization" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Contact" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Building" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="SubBuilding" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Street" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="HouseNumber" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="DeliveryService" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Locality" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="PreferredLocality" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Province" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Residue" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="RecipientLines" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="DeliveryAddressLines" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="CountrySpecificLocalityLine" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="FormattedAddress" type="{http://validator5.AddressDoctor.com/Webservice5/v4}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="AdditionalAddressInformation" type="{http://validator5.AddressDoctor.com/Webservice5/v4}AdditionalInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AddressComplete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressCode" type="{http://validator5.AddressDoctor.com/Webservice5/v4}AddressCode" minOccurs="0"/>
 *         &lt;element name="AddressDetailed" type="{http://validator5.AddressDoctor.com/Webservice5/v4}AddressDetailed" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = {
    "recordId",
    "organization",
    "department",
    "contact",
    "email",
    "building",
    "subBuilding",
    "street",
    "houseNumber",
    "deliveryService",
    "locality",
    "preferredLocality",
    "postalCode",
    "province",
    "country",
    "residue",
    "recipientLines",
    "deliveryAddressLines",
    "countrySpecificLocalityLine",
    "formattedAddress",
    "additionalAddressInformation",
    "addressComplete",
    "addressCode",
    "addressDetailed"
})
public class Address {

    @XmlElement(name = "RecordId")
    protected String recordId;
    @XmlElement(name = "Organization")
    protected ArrayOfString organization;
    @XmlElement(name = "Department")
    protected ArrayOfString department;
    @XmlElement(name = "Contact")
    protected ArrayOfString contact;
    @XmlElement(name = "Email")
    protected ArrayOfString email;
    @XmlElement(name = "Building")
    protected ArrayOfString building;
    @XmlElement(name = "SubBuilding")
    protected ArrayOfString subBuilding;
    @XmlElement(name = "Street")
    protected ArrayOfString street;
    @XmlElement(name = "HouseNumber")
    protected ArrayOfString houseNumber;
    @XmlElement(name = "DeliveryService")
    protected ArrayOfString deliveryService;
    @XmlElement(name = "Locality")
    protected ArrayOfString locality;
    @XmlElement(name = "PreferredLocality")
    protected ArrayOfString preferredLocality;
    @XmlElement(name = "PostalCode")
    protected ArrayOfString postalCode;
    @XmlElement(name = "Province")
    protected ArrayOfString province;
    @XmlElement(name = "Country")
    protected ArrayOfString country;
    @XmlElement(name = "Residue")
    protected ArrayOfString residue;
    @XmlElement(name = "RecipientLines")
    protected ArrayOfString recipientLines;
    @XmlElement(name = "DeliveryAddressLines")
    protected ArrayOfString deliveryAddressLines;
    @XmlElement(name = "CountrySpecificLocalityLine")
    protected ArrayOfString countrySpecificLocalityLine;
    @XmlElement(name = "FormattedAddress")
    protected ArrayOfString formattedAddress;
    @XmlElement(name = "AdditionalAddressInformation", nillable = true)
    protected List<AdditionalInformation> additionalAddressInformation;
    @XmlElement(name = "AddressComplete")
    protected String addressComplete;
    @XmlElement(name = "AddressCode")
    protected AddressCode addressCode;
    @XmlElement(name = "AddressDetailed")
    protected AddressDetailed addressDetailed;

    /**
     * Gets the value of the recordId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * Sets the value of the recordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordId(String value) {
        this.recordId = value;
    }

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setOrganization(ArrayOfString value) {
        this.organization = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDepartment(ArrayOfString value) {
        this.department = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setContact(ArrayOfString value) {
        this.contact = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setEmail(ArrayOfString value) {
        this.email = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setBuilding(ArrayOfString value) {
        this.building = value;
    }

    /**
     * Gets the value of the subBuilding property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSubBuilding() {
        return subBuilding;
    }

    /**
     * Sets the value of the subBuilding property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSubBuilding(ArrayOfString value) {
        this.subBuilding = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setStreet(ArrayOfString value) {
        this.street = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setHouseNumber(ArrayOfString value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the deliveryService property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDeliveryService() {
        return deliveryService;
    }

    /**
     * Sets the value of the deliveryService property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDeliveryService(ArrayOfString value) {
        this.deliveryService = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setLocality(ArrayOfString value) {
        this.locality = value;
    }

    /**
     * Gets the value of the preferredLocality property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getPreferredLocality() {
        return preferredLocality;
    }

    /**
     * Sets the value of the preferredLocality property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setPreferredLocality(ArrayOfString value) {
        this.preferredLocality = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setPostalCode(ArrayOfString value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setProvince(ArrayOfString value) {
        this.province = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCountry(ArrayOfString value) {
        this.country = value;
    }

    /**
     * Gets the value of the residue property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getResidue() {
        return residue;
    }

    /**
     * Sets the value of the residue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setResidue(ArrayOfString value) {
        this.residue = value;
    }

    /**
     * Gets the value of the recipientLines property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getRecipientLines() {
        return recipientLines;
    }

    /**
     * Sets the value of the recipientLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setRecipientLines(ArrayOfString value) {
        this.recipientLines = value;
    }

    /**
     * Gets the value of the deliveryAddressLines property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDeliveryAddressLines() {
        return deliveryAddressLines;
    }

    /**
     * Sets the value of the deliveryAddressLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDeliveryAddressLines(ArrayOfString value) {
        this.deliveryAddressLines = value;
    }

    /**
     * Gets the value of the countrySpecificLocalityLine property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCountrySpecificLocalityLine() {
        return countrySpecificLocalityLine;
    }

    /**
     * Sets the value of the countrySpecificLocalityLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCountrySpecificLocalityLine(ArrayOfString value) {
        this.countrySpecificLocalityLine = value;
    }

    /**
     * Gets the value of the formattedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * Sets the value of the formattedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setFormattedAddress(ArrayOfString value) {
        this.formattedAddress = value;
    }

    /**
     * Gets the value of the additionalAddressInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAddressInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAddressInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalInformation }
     * 
     * 
     */
    public List<AdditionalInformation> getAdditionalAddressInformation() {
        if (additionalAddressInformation == null) {
            additionalAddressInformation = new ArrayList<AdditionalInformation>();
        }
        return this.additionalAddressInformation;
    }

    /**
     * Gets the value of the addressComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressComplete() {
        return addressComplete;
    }

    /**
     * Sets the value of the addressComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressComplete(String value) {
        this.addressComplete = value;
    }

    /**
     * Gets the value of the addressCode property.
     * 
     * @return
     *     possible object is
     *     {@link AddressCode }
     *     
     */
    public AddressCode getAddressCode() {
        return addressCode;
    }

    /**
     * Sets the value of the addressCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressCode }
     *     
     */
    public void setAddressCode(AddressCode value) {
        this.addressCode = value;
    }

    /**
     * Gets the value of the addressDetailed property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDetailed }
     *     
     */
    public AddressDetailed getAddressDetailed() {
        return addressDetailed;
    }

    /**
     * Sets the value of the addressDetailed property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDetailed }
     *     
     */
    public void setAddressDetailed(AddressDetailed value) {
        this.addressDetailed = value;
    }

}
