package com.moh.phlat.backend.model;



import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "SOURCE_DATA")
public class SourceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
    private Long id;
    
    @Column(name="CONTROL_ID")
    private Long controlTableId;    
    
//    @Column(name="FILE_NAME")
//    private String fileName;;
//    
//    @Column(name="USER_ID")
//    private String userId;
    
    @Column(name="DO_NOT_LOAD")
    private String doNotLoad;


    @Column(name="INTERNAL_ID")
    private String internalId;

    @Column(name="HDS_SURV_INTERNAL_ID")
    private String hdsSurvInternalId;


    @Column(name="FACILITY_SURV_INTERNAL_ID")
    private String facilitySurvInternalId;


    @Column(name="HDS_SOURCE")
    private String hdsSource;
    
    @Column(name="HDS_TYPE_CONCAT")
    private String hdsTypeConcat;

    
    @Column(name="HDS_NAME")
    private String hdsName;
    
    
    @Column(name="CIVIC_ADDRESS_CLEAN")
    private String civicAddressClean;
    

    @Column(name="BUS_NAME")
    private String busName;
    
    @Column(name="FACILITY_DETAILS_ADDITIONAL_INFO")
    private String facilityDetailsAdditionalInfo;
    
    @Column(name="HDS_TEL_AREA_CODE")
    private String hdsTelAreaCode;
    
    @Column(name="HDS_TEL_NUMBER")
    private String hdsTelNumber;
    
    @Column(name="HDS_CELL_AREA_CODE")
    private String hdsCellAreaCode;
    
    @Column(name="HDS_CELL_NUMBER")
    private String hdsCellNumber;
    
   
    @Column(name="HDS_FAX_AREA_CODE")
    private String hdsFaxAreaCode;
    
    @Column(name="HDS_FAX_NUMBER")
    private String hdsFaxNumber;   

    @Column(name="PHYS_PROCESS_STATUS")
    private String physProcessStatus;   
    
    @Column(name="PHYS_MAILABILITY_SCORE")
    private String physMailabilityScore;   

    @Column(name="PHYS_CITY")
    private String physCity;  
    
    @Column(name="PHYS_PROVINCE")
    private String physProvince;  

    @Column(name="PHYS_PCODE")
    private String physPcode;  
    
    @Column(name="PHYS_COUNTRY")
    private String physCountry;  

    
    @Column(name="PHYS_ADDR_1")
    private String physAddress1;  
    
    @Column(name="PHYS_ADDR_2")
    private String physAddress2;  
    
    @Column(name="PHYS_ADDR_3")
    private String physAddress3;  
    
    @Column(name="PHYS_ADDR_4")
    private String physAddress4;  
    

    @Column(name="BUILDING")
    private String building;  
    
    @Column(name="UNIT")
    private String unit;  

    @Column(name="CIVIC_ADDRESS_CLEAN_OLD")
    private String civicAddressCleanOld;  

    @Column(name="MAIL_PROCESS_STATUS")
    private String mailProcessStatus;  
    
    @Column(name="MAIL_MAILABILITY_SCORE")
    private String mailMailabilityScore;  

    @Column(name="MAIL_CITY")
    private String mailCity;  
    
    @Column(name="MAIL_PROVINCE")
    private String mailProvince;
    
    @Column(name="MAIL_PCODE")
    private String mailPcode;
    
    @Column(name="MAIL_COUNTRY")
    private String mailCountry;  
    
    @Column(name="MAIL_ADDR_1")
    private String mailAddress1;  
    
    @Column(name="MAIL_ADDR_2")
    private String mailAddress2;  

    @Column(name="MAIL_ADDR_3")
    private String mailAddress3;
    
    @Column(name="MAIL_ADDR_4")
    private String mailAddress4;  
    
    @Column(name="DATABC_LATITUDE")
    private String databcLatitude;  

    @Column(name="DATABC_LONGITUDE")
    private String databcLongitude;  
    
    @Column(name="DATABC_UNIT_NO")
    private String databcUnitNo;  
    

    @Column(name="DATABC_CIVIC_NUMBER")
    private String databcCivicNumber;  
    

    @Column(name="DATABC_STREET_NAME")
    private String databcStreetName;  
       

    @Column(name="DATABC_STREET_TYPE")
    private String databcStreetType;  
    
    @Column(name="DATABC_LOCALITY_NAME")
    private String databcLocalityName;  

    @Column(name="DATABC_PROVINCE_CODE")
    private String databcProvinceCode;  
    
    @Column(name="DATABC_SITE_ID")
    private String databcSiteId;  
    
    @Column(name="DATABC_SCORE")
    private String databcScore;  
    
    @Column(name="DATABC_MATCH_PRECISION")
    private String databcMatchPrecision;  
    
    @Column(name="DATABC_PRECISION_POINTS")
    private String databcPrecisionPoints;  
    
    @Column(name="DATABC_CHSA_CODE")
    private String databcChsaCode;  
    
    @Column(name="DATABC_CHSA_NAME")
    private String databcChsaName;  
    
    @Column(name="DATABC_LHA_NAME")
    private String databcLhaName;  
       
    @Column(name="DATABC_HSDA_NAME")
    private String databcHsdaName;  
    
    @Column(name="DATABC_HA_NAME")
    private String databcHaName;  
    
    @Column(name="DATABC_USER_CHID")
    private String databcUserChid;  
    
    @Column(name="DATABC_PCN_CODE")
    private String databcPcnCode;  
    
    @Column(name="DATABC_PCN_NAME")
    private String databcPcnName;  

    @Column(name="DATABC_STATUS")
    private String databcStatus;
    
	@Column(name="CREATED_AT")
    private Date createdAt;

	@Column(name="CREATED_BY")
    private String createdBy;
    
    @Column(name="UPDATED_AT")
    private Date updatedAt;

	@Column(name="UPDATED_BY")
    private String updatedBy;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getControlTableId() {
		return controlTableId;
	}

	public void setControlTableId(Long controlTableId) {
		this.controlTableId = controlTableId;
	}
	
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public String getDoNotLoad() {
		return doNotLoad;
	}

	public void setDoNotLoad(String doNotLoad) {
		this.doNotLoad = doNotLoad;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public String getHdsSurvInternalId() {
		return hdsSurvInternalId;
	}

	public void setHdsSurvInternalId(String hdsSurvInternalId) {
		this.hdsSurvInternalId = hdsSurvInternalId;
	}

	public String getFacilitySurvInternalId() {
		return facilitySurvInternalId;
	}

	public void setFacilitySurvInternalId(String facilitySurvInternalId) {
		this.facilitySurvInternalId = facilitySurvInternalId;
	}

	public String getHdsSource() {
		return hdsSource;
	}

	public void setHdsSource(String hdsSource) {
		this.hdsSource = hdsSource;
	}

	public String getHdsTypeConcat() {
		return hdsTypeConcat;
	}

	public void setHdsTypeConcat(String hdsTypeConcat) {
		this.hdsTypeConcat = hdsTypeConcat;
	}

	public String getHdsName() {
		return hdsName;
	}

	public void setHdsName(String hdsName) {
		this.hdsName = hdsName;
	}

	public String getCivicAddressClean() {
		return civicAddressClean;
	}

	public void setCivicAddressClean(String civicAddressClean) {
		this.civicAddressClean = civicAddressClean;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getFacilityDetailsAdditionalInfo() {
		return facilityDetailsAdditionalInfo;
	}

	public void setFacilityDetailsAdditionalInfo(String facilityDetailsAdditionalInfo) {
		this.facilityDetailsAdditionalInfo = facilityDetailsAdditionalInfo;
	}

	public String getHdsTelAreaCode() {
		return hdsTelAreaCode;
	}

	public void setHdsTelAreaCode(String hdsTelAreaCode) {
		this.hdsTelAreaCode = hdsTelAreaCode;
	}

	public String getHdsTelNumber() {
		return hdsTelNumber;
	}

	public void setHdsTelNumber(String hdsTelNumber) {
		this.hdsTelNumber = hdsTelNumber;
	}

	public String getHdsCellAreaCode() {
		return hdsCellAreaCode;
	}

	public void setHdsCellAreaCode(String hdsCellAreaCode) {
		this.hdsCellAreaCode = hdsCellAreaCode;
	}

	public String getHdsCellNumber() {
		return hdsCellNumber;
	}

	public void setHdsCellNumber(String hdsCellNumber) {
		this.hdsCellNumber = hdsCellNumber;
	}

	public String getHdsFaxAreaCode() {
		return hdsFaxAreaCode;
	}

	public void setHdsFaxAreaCode(String hdsFaxAreaCode) {
		this.hdsFaxAreaCode = hdsFaxAreaCode;
	}

	public String getHdsFaxNumber() {
		return hdsFaxNumber;
	}

	public void setHdsFaxNumber(String hdsFaxNumber) {
		this.hdsFaxNumber = hdsFaxNumber;
	}

	public String getPhysProcessStatus() {
		return physProcessStatus;
	}

	public void setPhysProcessStatus(String physProcessStatus) {
		this.physProcessStatus = physProcessStatus;
	}

	public String getPhysMailabilityScore() {
		return physMailabilityScore;
	}

	public void setPhysMailabilityScore(String physMailabilityScore) {
		this.physMailabilityScore = physMailabilityScore;
	}

	public String getPhysCity() {
		return physCity;
	}

	public void setPhysCity(String physCity) {
		this.physCity = physCity;
	}

	public String getPhysProvince() {
		return physProvince;
	}

	public void setPhysProvince(String physProvince) {
		this.physProvince = physProvince;
	}

	public String getPhysPcode() {
		return physPcode;
	}

	public void setPhysPcode(String physPcode) {
		this.physPcode = physPcode;
	}

	public String getPhysCountry() {
		return physCountry;
	}

	public void setPhysCountry(String physCountry) {
		this.physCountry = physCountry;
	}

	public String getPhysAddress1() {
		return physAddress1;
	}

	public void setPhysAddress1(String physAddress1) {
		this.physAddress1 = physAddress1;
	}

	public String getPhysAddress2() {
		return physAddress2;
	}

	public void setPhysAddress2(String physAddress2) {
		this.physAddress2 = physAddress2;
	}

	public String getPhysAddress3() {
		return physAddress3;
	}

	public void setPhysAddress3(String physAddress3) {
		this.physAddress3 = physAddress3;
	}

	public String getPhysAddress4() {
		return physAddress4;
	}

	public void setPhysAddress4(String physAddress4) {
		this.physAddress4 = physAddress4;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCivicAddressCleanOld() {
		return civicAddressCleanOld;
	}

	public void setCivicAddressCleanOld(String civicAddressCleanOld) {
		this.civicAddressCleanOld = civicAddressCleanOld;
	}

	public String getMailProcessStatus() {
		return mailProcessStatus;
	}

	public void setMailProcessStatus(String mailProcessStatus) {
		this.mailProcessStatus = mailProcessStatus;
	}
	
	public String getMailMailabilityScore() {
		return mailMailabilityScore;
	}

	public void setMailMailabilityScore(String mailMailabilityScore) {
		this.mailMailabilityScore = mailMailabilityScore;
	}

	public String getMailCity() {
		return mailCity;
	}

	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	public String getMailProvince() {
		return mailProvince;
	}

	public void setMailProvince(String mailProvince) {
		this.mailProvince = mailProvince;
	}

	public String getMailPcode() {
		return mailPcode;
	}

	public void setMailPcode(String mailPcode) {
		this.mailPcode = mailPcode;
	}

	public String getMailCountry() {
		return mailCountry;
	}

	public void setMailCountry(String mailCountry) {
		this.mailCountry = mailCountry;
	}

	public String getMailAddress1() {
		return mailAddress1;
	}

	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
	}

	public String getMailAddress2() {
		return mailAddress2;
	}

	public void setMailAddress2(String mailAddress2) {
		this.mailAddress2 = mailAddress2;
	}

	public String getMailAddress3() {
		return mailAddress3;
	}

	public void setMailAddress3(String mailAddress3) {
		this.mailAddress3 = mailAddress3;
	}

	public String getMailAddress4() {
		return mailAddress4;
	}

	public void setMailAddress4(String mailAddress4) {
		this.mailAddress4 = mailAddress4;
	}

	public String getDatabcLatitude() {
		return databcLatitude;
	}

	public void setDatabcLatitude(String databcLatitude) {
		this.databcLatitude = databcLatitude;
	}

	public String getDatabcLongitude() {
		return databcLongitude;
	}

	public void setDatabcLongitude(String databcLongitude) {
		this.databcLongitude = databcLongitude;
	}

	public String getDatabcUnitNo() {
		return databcUnitNo;
	}

	public void setDatabcUnitNo(String databcUnitNo) {
		this.databcUnitNo = databcUnitNo;
	}

	public String getDatabcCivicNumber() {
		return databcCivicNumber;
	}

	public void setDatabcCivicNumber(String databcCivicNumber) {
		this.databcCivicNumber = databcCivicNumber;
	}

	public String getDatabcStreetName() {
		return databcStreetName;
	}

	public void setDatabcStreetName(String databcStreetName) {
		this.databcStreetName = databcStreetName;
	}

	public String getDatabcStreetType() {
		return databcStreetType;
	}

	public void setDatabcStreetType(String databcStreetType) {
		this.databcStreetType = databcStreetType;
	}

	public String getDatabcLocalityName() {
		return databcLocalityName;
	}

	public void setDatabcLocalityName(String databcLocalityName) {
		this.databcLocalityName = databcLocalityName;
	}

	public String getDatabcProvinceCode() {
		return databcProvinceCode;
	}

	public void setDatabcProvinceCode(String databcProvinceCode) {
		this.databcProvinceCode = databcProvinceCode;
	}

	public String getDatabcSiteId() {
		return databcSiteId;
	}

	public void setDatabcSiteId(String databcSiteId) {
		this.databcSiteId = databcSiteId;
	}

	public String getDatabcScore() {
		return databcScore;
	}

	public void setDatabcScore(String databcScore) {
		this.databcScore = databcScore;
	}

	public String getDatabcMatchPrecision() {
		return databcMatchPrecision;
	}

	public void setDatabcMatchPrecision(String databcMatchPrecision) {
		this.databcMatchPrecision = databcMatchPrecision;
	}

	public String getDatabcPrecisionPoints() {
		return databcPrecisionPoints;
	}

	public void setDatabcPrecisionPoints(String databcPrecisionPoints) {
		this.databcPrecisionPoints = databcPrecisionPoints;
	}

	public String getDatabcChsaCode() {
		return databcChsaCode;
	}

	public void setDatabcChsaCode(String databcChsaCode) {
		this.databcChsaCode = databcChsaCode;
	}

	public String getDatabcChsaName() {
		return databcChsaName;
	}

	public void setDatabcChsaName(String databcChsaName) {
		this.databcChsaName = databcChsaName;
	}

	public String getDatabcLhaName() {
		return databcLhaName;
	}

	public void setDatabcLhaName(String databcLhaName) {
		this.databcLhaName = databcLhaName;
	}

	public String getDatabcHsdaName() {
		return databcHsdaName;
	}

	public void setDatabcHsdaName(String databcHsdaName) {
		this.databcHsdaName = databcHsdaName;
	}

	public String getDatabcHaName() {
		return databcHaName;
	}

	public void setDatabcHaName(String databcHaName) {
		this.databcHaName = databcHaName;
	}

	public String getDatabcUserChid() {
		return databcUserChid;
	}

	public void setDatabcUserChid(String databcUserChid) {
		this.databcUserChid = databcUserChid;
	}

	public String getDatabcPcnCode() {
		return databcPcnCode;
	}

	public void setDatabcPcnCode(String databcPcnCode) {
		this.databcPcnCode = databcPcnCode;
	}

	public String getDatabcPcnName() {
		return databcPcnName;
	}

	public void setDatabcPcnName(String databcPcnName) {
		this.databcPcnName = databcPcnName;
	}

	public String getDatabcStatus() {
		return databcStatus;
	}

	public void setDatabcStatus(String databcStatus) {
		this.databcStatus = databcStatus;
	}  

    public Date getCreatedAt() {
		return createdAt;
	}
    
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
    public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public SourceData() {
	}

	public SourceData(Long controlTableId, String doNotLoad, String internalId, String hdsSurvInternalId,
			String facilitySurvInternalId, String hdsSource, String hdsTypeConcat, String hdsName,
			String civicAddressClean, String busName, String facilityDetailsAdditionalInfo, String hdsTelAreaCode,
			String hdsTelNumber, String hdsCellAreaCode, String hdsCellNumber, String hdsFaxAreaCode,
			String hdsFaxNumber, String physProcessStatus, String physMailabilityScore, String physCity,
			String physProvince, String physPcode, String physCountry, String physAddress1, String physAddress2,
			String physAddress3, String physAddress4, String building, String unit, String civicAddressCleanOld,
			String mailProcessStatus, String mailMailabilityScore, String mailCity, String mailProvince,
			String mailPcode, String mailCountry, String mailAddress1, String mailAddress2, String mailAddress3,
			String mailAddress4, String databcLatitude, String databcLongitude, String databcUnitNo,
			String databcCivicNumber, String databcStreetName, String databcStreetType, String databcLocalityName,
			String databcProvinceCode, String databcSiteId, String databcScore, String databcMatchPrecision,
			String databcPrecisionPoints, String databcChsaCode, String databcChsaName, String databcLhaName,
			String databcHsdaName, String databcHaName, String databcUserChid, String databcPcnCode,
			String databcPcnName, String databcStatus, Date createdAt, String createdBy, Date updatedAt,
			String updatedBy) {
		this.controlTableId = controlTableId;
		this.doNotLoad = doNotLoad;
		this.internalId = internalId;
		this.hdsSurvInternalId = hdsSurvInternalId;
		this.facilitySurvInternalId = facilitySurvInternalId;
		this.hdsSource = hdsSource;
		this.hdsTypeConcat = hdsTypeConcat;
		this.hdsName = hdsName;
		this.civicAddressClean = civicAddressClean;
		this.busName = busName;
		this.facilityDetailsAdditionalInfo = facilityDetailsAdditionalInfo;
		this.hdsTelAreaCode = hdsTelAreaCode;
		this.hdsTelNumber = hdsTelNumber;
		this.hdsCellAreaCode = hdsCellAreaCode;
		this.hdsCellNumber = hdsCellNumber;
		this.hdsFaxAreaCode = hdsFaxAreaCode;
		this.hdsFaxNumber = hdsFaxNumber;
		this.physProcessStatus = physProcessStatus;
		this.physMailabilityScore = physMailabilityScore;
		this.physCity = physCity;
		this.physProvince = physProvince;
		this.physPcode = physPcode;
		this.physCountry = physCountry;
		this.physAddress1 = physAddress1;
		this.physAddress2 = physAddress2;
		this.physAddress3 = physAddress3;
		this.physAddress4 = physAddress4;
		this.building = building;
		this.unit = unit;
		this.civicAddressCleanOld = civicAddressCleanOld;
		this.mailProcessStatus = mailProcessStatus;
		this.mailMailabilityScore = mailMailabilityScore;
		this.mailCity = mailCity;
		this.mailProvince = mailProvince;
		this.mailPcode = mailPcode;
		this.mailCountry = mailCountry;
		this.mailAddress1 = mailAddress1;
		this.mailAddress2 = mailAddress2;
		this.mailAddress3 = mailAddress3;
		this.mailAddress4 = mailAddress4;
		this.databcLatitude = databcLatitude;
		this.databcLongitude = databcLongitude;
		this.databcUnitNo = databcUnitNo;
		this.databcCivicNumber = databcCivicNumber;
		this.databcStreetName = databcStreetName;
		this.databcStreetType = databcStreetType;
		this.databcLocalityName = databcLocalityName;
		this.databcProvinceCode = databcProvinceCode;
		this.databcSiteId = databcSiteId;
		this.databcScore = databcScore;
		this.databcMatchPrecision = databcMatchPrecision;
		this.databcPrecisionPoints = databcPrecisionPoints;
		this.databcChsaCode = databcChsaCode;
		this.databcChsaName = databcChsaName;
		this.databcLhaName = databcLhaName;
		this.databcHsdaName = databcHsdaName;
		this.databcHaName = databcHaName;
		this.databcUserChid = databcUserChid;
		this.databcPcnCode = databcPcnCode;
		this.databcPcnName = databcPcnName;
		this.databcStatus = databcStatus;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

//	public InputFile(Long controlTableId, String doNotLoad, String internalId, String hdsSurvInternalId,
//			String facilitySurvInternalId, String hdsSource, String hdsTypeConcat, String hdsName,
//			String civicAddressClean, String busName, String facilityDetailsAdditionalInfo, String hdsTelAreaCode,
//			String hdsTelNumber, String hdsCellAreaCode, String hdsCellNumber, String hdsFaxAreaCode,
//			String hdsFaxNumber, String physProcessStatus, String physMailabilityScore, String physCity,
//			String physProvince, String physPcode, String physCountry, String physAddress1, String physAddress2,
//			String physAddress3, String physAddress4, String building, String unit, String civicAddressCleanOld,
//			String mailProcessStatus, String mailMailabilityScore, String mailCity, String mailProvince,
//			String mailPcode, String mailCountry, String mailAddress1, String mailAddress2, String mailAddress3,
//			String mailAddress4, String databcLatitude, String databcLongitude, String databcUnitNo,
//			String databcCivicNumber, String databcStreetName, String databcStreetType, String databcLocalityName,
//			String databcProvinceCode, String databcSiteId, String databcScore, String databcMatchPrecision,
//			String databcPrecisionPoints, String databcChsaCode, String databcChsaName, String databcLhaName,
//			String databcHsdaName, String databcHaName, String databcUserChid, String databcPcnCode,
//			String databcPcnName, String databcStatus) {
//		super();
//		this.controlTableId = controlTableId;
//		this.doNotLoad = doNotLoad;
//		this.internalId = internalId;
//		this.hdsSurvInternalId = hdsSurvInternalId;
//		this.facilitySurvInternalId = facilitySurvInternalId;
//		this.hdsSource = hdsSource;
//		this.hdsTypeConcat = hdsTypeConcat;
//		this.hdsName = hdsName;
//		this.civicAddressClean = civicAddressClean;
//		this.busName = busName;
//		this.facilityDetailsAdditionalInfo = facilityDetailsAdditionalInfo;
//		this.hdsTelAreaCode = hdsTelAreaCode;
//		this.hdsTelNumber = hdsTelNumber;
//		this.hdsCellAreaCode = hdsCellAreaCode;
//		this.hdsCellNumber = hdsCellNumber;
//		this.hdsFaxAreaCode = hdsFaxAreaCode;
//		this.hdsFaxNumber = hdsFaxNumber;
//		this.physProcessStatus = physProcessStatus;
//		this.physMailabilityScore = physMailabilityScore;
//		this.physCity = physCity;
//		this.physProvince = physProvince;
//		this.physPcode = physPcode;
//		this.physCountry = physCountry;
//		this.physAddress1 = physAddress1;
//		this.physAddress2 = physAddress2;
//		this.physAddress3 = physAddress3;
//		this.physAddress4 = physAddress4;
//		this.building = building;
//		this.unit = unit;
//		this.civicAddressCleanOld = civicAddressCleanOld;
//		this.mailProcessStatus = mailProcessStatus;
//		this.mailMailabilityScore = mailMailabilityScore;
//		this.mailCity = mailCity;
//		this.mailProvince = mailProvince;
//		this.mailPcode = mailPcode;
//		this.mailCountry = mailCountry;
//		this.mailAddress1 = mailAddress1;
//		this.mailAddress2 = mailAddress2;
//		this.mailAddress3 = mailAddress3;
//		this.mailAddress4 = mailAddress4;
//		this.databcLatitude = databcLatitude;
//		this.databcLongitude = databcLongitude;
//		this.databcUnitNo = databcUnitNo;
//		this.databcCivicNumber = databcCivicNumber;
//		this.databcStreetName = databcStreetName;
//		this.databcStreetType = databcStreetType;
//		this.databcLocalityName = databcLocalityName;
//		this.databcProvinceCode = databcProvinceCode;
//		this.databcSiteId = databcSiteId;
//		this.databcScore = databcScore;
//		this.databcMatchPrecision = databcMatchPrecision;
//		this.databcPrecisionPoints = databcPrecisionPoints;
//		this.databcChsaCode = databcChsaCode;
//		this.databcChsaName = databcChsaName;
//		this.databcLhaName = databcLhaName;
//		this.databcHsdaName = databcHsdaName;
//		this.databcHaName = databcHaName;
//		this.databcUserChid = databcUserChid;
//		this.databcPcnCode = databcPcnCode;
//		this.databcPcnName = databcPcnName;
//		this.databcStatus = databcStatus;
//	}
	

	
	
}
