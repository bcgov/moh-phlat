
package com.moh.phlat.backend.addressdoctor.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormatDelimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultCountryISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ForceCountryISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryOfOriginISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StreetWithNumber" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FormatWithCountry" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ElementAbbreviation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PreferredScript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PreferredLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AliasStreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AliasLocality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GlobalCasing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GlobalMaxLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GlobalPreferredDescriptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchingScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxResultCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DualAddressPriority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StandardizeInvalidAddresses" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RangesToExpand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlexibleRangeExpansion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GeoCodingType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchingAlternatives" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchingExtendedArchive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DisableCertifiedModeISO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormatMaxLines" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FormatAddressComplete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Standardizations" type="{http://validator5.AddressDoctor.com/Webservice5/v4}Standardize" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AdditionalInformationSet" type="{http://validator5.AddressDoctor.com/Webservice5/v4}AdditionalInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OutputOptions" type="{http://validator5.AddressDoctor.com/Webservice5/v4}AddressOptions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationParameters", propOrder = {
    "formatType",
    "formatDelimiter",
    "defaultCountryISO3",
    "forceCountryISO3",
    "countryType",
    "countryOfOriginISO3",
    "streetWithNumber",
    "formatWithCountry",
    "elementAbbreviation",
    "preferredScript",
    "preferredLanguage",
    "aliasStreet",
    "aliasLocality",
    "globalCasing",
    "globalMaxLength",
    "globalPreferredDescriptor",
    "matchingScope",
    "maxResultCount",
    "dualAddressPriority",
    "standardizeInvalidAddresses",
    "rangesToExpand",
    "flexibleRangeExpansion",
    "geoCodingType",
    "matchingAlternatives",
    "matchingExtendedArchive",
    "disableCertifiedModeISO3",
    "formatMaxLines",
    "formatAddressComplete",
    "standardizations",
    "additionalInformationSet",
    "outputOptions"
})
public class ValidationParameters {

    @XmlElement(name = "FormatType")
    protected String formatType;
    @XmlElement(name = "FormatDelimiter")
    protected String formatDelimiter;
    @XmlElement(name = "DefaultCountryISO3")
    protected String defaultCountryISO3;
    @XmlElement(name = "ForceCountryISO3")
    protected String forceCountryISO3;
    @XmlElement(name = "CountryType")
    protected String countryType;
    @XmlElement(name = "CountryOfOriginISO3")
    protected String countryOfOriginISO3;
    @XmlElement(name = "StreetWithNumber")
    protected boolean streetWithNumber;
    @XmlElement(name = "FormatWithCountry")
    protected boolean formatWithCountry;
    @XmlElement(name = "ElementAbbreviation")
    protected boolean elementAbbreviation;
    @XmlElement(name = "PreferredScript")
    protected String preferredScript;
    @XmlElement(name = "PreferredLanguage")
    protected String preferredLanguage;
    @XmlElement(name = "AliasStreet")
    protected String aliasStreet;
    @XmlElement(name = "AliasLocality")
    protected String aliasLocality;
    @XmlElement(name = "GlobalCasing")
    protected String globalCasing;
    @XmlElement(name = "GlobalMaxLength")
    protected int globalMaxLength;
    @XmlElement(name = "GlobalPreferredDescriptor")
    protected String globalPreferredDescriptor;
    @XmlElement(name = "MatchingScope")
    protected String matchingScope;
    @XmlElement(name = "MaxResultCount")
    protected int maxResultCount;
    @XmlElement(name = "DualAddressPriority")
    protected String dualAddressPriority;
    @XmlElement(name = "StandardizeInvalidAddresses")
    protected boolean standardizeInvalidAddresses;
    @XmlElement(name = "RangesToExpand")
    protected String rangesToExpand;
    @XmlElement(name = "FlexibleRangeExpansion")
    protected boolean flexibleRangeExpansion;
    @XmlElement(name = "GeoCodingType")
    protected String geoCodingType;
    @XmlElement(name = "MatchingAlternatives")
    protected String matchingAlternatives;
    @XmlElement(name = "MatchingExtendedArchive")
    protected boolean matchingExtendedArchive;
    @XmlElement(name = "DisableCertifiedModeISO3")
    protected String disableCertifiedModeISO3;
    @XmlElement(name = "FormatMaxLines")
    protected int formatMaxLines;
    @XmlElement(name = "FormatAddressComplete")
    protected String formatAddressComplete;
    @XmlElement(name = "Standardizations", nillable = true)
    protected List<Standardize> standardizations;
    @XmlElement(name = "AdditionalInformationSet", nillable = true)
    protected List<AdditionalInformation> additionalInformationSet;
    @XmlElement(name = "OutputOptions")
    protected AddressOptions outputOptions;

    /**
     * Gets the value of the formatType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatType() {
        return formatType;
    }

    /**
     * Sets the value of the formatType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatType(String value) {
        this.formatType = value;
    }

    /**
     * Gets the value of the formatDelimiter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatDelimiter() {
        return formatDelimiter;
    }

    /**
     * Sets the value of the formatDelimiter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatDelimiter(String value) {
        this.formatDelimiter = value;
    }

    /**
     * Gets the value of the defaultCountryISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultCountryISO3() {
        return defaultCountryISO3;
    }

    /**
     * Sets the value of the defaultCountryISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultCountryISO3(String value) {
        this.defaultCountryISO3 = value;
    }

    /**
     * Gets the value of the forceCountryISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForceCountryISO3() {
        return forceCountryISO3;
    }

    /**
     * Sets the value of the forceCountryISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForceCountryISO3(String value) {
        this.forceCountryISO3 = value;
    }

    /**
     * Gets the value of the countryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryType() {
        return countryType;
    }

    /**
     * Sets the value of the countryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryType(String value) {
        this.countryType = value;
    }

    /**
     * Gets the value of the countryOfOriginISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryOfOriginISO3() {
        return countryOfOriginISO3;
    }

    /**
     * Sets the value of the countryOfOriginISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryOfOriginISO3(String value) {
        this.countryOfOriginISO3 = value;
    }

    /**
     * Gets the value of the streetWithNumber property.
     * 
     */
    public boolean isStreetWithNumber() {
        return streetWithNumber;
    }

    /**
     * Sets the value of the streetWithNumber property.
     * 
     */
    public void setStreetWithNumber(boolean value) {
        this.streetWithNumber = value;
    }

    /**
     * Gets the value of the formatWithCountry property.
     * 
     */
    public boolean isFormatWithCountry() {
        return formatWithCountry;
    }

    /**
     * Sets the value of the formatWithCountry property.
     * 
     */
    public void setFormatWithCountry(boolean value) {
        this.formatWithCountry = value;
    }

    /**
     * Gets the value of the elementAbbreviation property.
     * 
     */
    public boolean isElementAbbreviation() {
        return elementAbbreviation;
    }

    /**
     * Sets the value of the elementAbbreviation property.
     * 
     */
    public void setElementAbbreviation(boolean value) {
        this.elementAbbreviation = value;
    }

    /**
     * Gets the value of the preferredScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredScript() {
        return preferredScript;
    }

    /**
     * Sets the value of the preferredScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredScript(String value) {
        this.preferredScript = value;
    }

    /**
     * Gets the value of the preferredLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * Sets the value of the preferredLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredLanguage(String value) {
        this.preferredLanguage = value;
    }

    /**
     * Gets the value of the aliasStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasStreet() {
        return aliasStreet;
    }

    /**
     * Sets the value of the aliasStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasStreet(String value) {
        this.aliasStreet = value;
    }

    /**
     * Gets the value of the aliasLocality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasLocality() {
        return aliasLocality;
    }

    /**
     * Sets the value of the aliasLocality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasLocality(String value) {
        this.aliasLocality = value;
    }

    /**
     * Gets the value of the globalCasing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalCasing() {
        return globalCasing;
    }

    /**
     * Sets the value of the globalCasing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalCasing(String value) {
        this.globalCasing = value;
    }

    /**
     * Gets the value of the globalMaxLength property.
     * 
     */
    public int getGlobalMaxLength() {
        return globalMaxLength;
    }

    /**
     * Sets the value of the globalMaxLength property.
     * 
     */
    public void setGlobalMaxLength(int value) {
        this.globalMaxLength = value;
    }

    /**
     * Gets the value of the globalPreferredDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalPreferredDescriptor() {
        return globalPreferredDescriptor;
    }

    /**
     * Sets the value of the globalPreferredDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalPreferredDescriptor(String value) {
        this.globalPreferredDescriptor = value;
    }

    /**
     * Gets the value of the matchingScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingScope() {
        return matchingScope;
    }

    /**
     * Sets the value of the matchingScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingScope(String value) {
        this.matchingScope = value;
    }

    /**
     * Gets the value of the maxResultCount property.
     * 
     */
    public int getMaxResultCount() {
        return maxResultCount;
    }

    /**
     * Sets the value of the maxResultCount property.
     * 
     */
    public void setMaxResultCount(int value) {
        this.maxResultCount = value;
    }

    /**
     * Gets the value of the dualAddressPriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDualAddressPriority() {
        return dualAddressPriority;
    }

    /**
     * Sets the value of the dualAddressPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDualAddressPriority(String value) {
        this.dualAddressPriority = value;
    }

    /**
     * Gets the value of the standardizeInvalidAddresses property.
     * 
     */
    public boolean isStandardizeInvalidAddresses() {
        return standardizeInvalidAddresses;
    }

    /**
     * Sets the value of the standardizeInvalidAddresses property.
     * 
     */
    public void setStandardizeInvalidAddresses(boolean value) {
        this.standardizeInvalidAddresses = value;
    }

    /**
     * Gets the value of the rangesToExpand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangesToExpand() {
        return rangesToExpand;
    }

    /**
     * Sets the value of the rangesToExpand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangesToExpand(String value) {
        this.rangesToExpand = value;
    }

    /**
     * Gets the value of the flexibleRangeExpansion property.
     * 
     */
    public boolean isFlexibleRangeExpansion() {
        return flexibleRangeExpansion;
    }

    /**
     * Sets the value of the flexibleRangeExpansion property.
     * 
     */
    public void setFlexibleRangeExpansion(boolean value) {
        this.flexibleRangeExpansion = value;
    }

    /**
     * Gets the value of the geoCodingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoCodingType() {
        return geoCodingType;
    }

    /**
     * Sets the value of the geoCodingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoCodingType(String value) {
        this.geoCodingType = value;
    }

    /**
     * Gets the value of the matchingAlternatives property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingAlternatives() {
        return matchingAlternatives;
    }

    /**
     * Sets the value of the matchingAlternatives property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingAlternatives(String value) {
        this.matchingAlternatives = value;
    }

    /**
     * Gets the value of the matchingExtendedArchive property.
     * 
     */
    public boolean isMatchingExtendedArchive() {
        return matchingExtendedArchive;
    }

    /**
     * Sets the value of the matchingExtendedArchive property.
     * 
     */
    public void setMatchingExtendedArchive(boolean value) {
        this.matchingExtendedArchive = value;
    }

    /**
     * Gets the value of the disableCertifiedModeISO3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisableCertifiedModeISO3() {
        return disableCertifiedModeISO3;
    }

    /**
     * Sets the value of the disableCertifiedModeISO3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisableCertifiedModeISO3(String value) {
        this.disableCertifiedModeISO3 = value;
    }

    /**
     * Gets the value of the formatMaxLines property.
     * 
     */
    public int getFormatMaxLines() {
        return formatMaxLines;
    }

    /**
     * Sets the value of the formatMaxLines property.
     * 
     */
    public void setFormatMaxLines(int value) {
        this.formatMaxLines = value;
    }

    /**
     * Gets the value of the formatAddressComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatAddressComplete() {
        return formatAddressComplete;
    }

    /**
     * Sets the value of the formatAddressComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatAddressComplete(String value) {
        this.formatAddressComplete = value;
    }

    /**
     * Gets the value of the standardizations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardizations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardizations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Standardize }
     * 
     * 
     */
    public List<Standardize> getStandardizations() {
        if (standardizations == null) {
            standardizations = new ArrayList<Standardize>();
        }
        return this.standardizations;
    }

    /**
     * Gets the value of the additionalInformationSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalInformationSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalInformationSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalInformation }
     * 
     * 
     */
    public List<AdditionalInformation> getAdditionalInformationSet() {
        if (additionalInformationSet == null) {
            additionalInformationSet = new ArrayList<AdditionalInformation>();
        }
        return this.additionalInformationSet;
    }

    /**
     * Gets the value of the outputOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AddressOptions }
     *     
     */
    public AddressOptions getOutputOptions() {
        return outputOptions;
    }

    /**
     * Sets the value of the outputOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressOptions }
     *     
     */
    public void setOutputOptions(AddressOptions value) {
        this.outputOptions = value;
    }

}
