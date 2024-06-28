package com.moh.phlat.backend.testsupport.factories;

import com.moh.phlat.backend.model.ProcessData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProcessDataFactory extends BaseFactory {


    public static List<ProcessData> createProcessDataListWithAllAttributes() {
        List<ProcessData> processData = new ArrayList<>();

        processData.add(createProcessData(1L, 2L, "N", "HOSPITAL", "stakeholderid_1",
                                         "IpcId_1", "CpnId_1", 
                                         "identifier1_1", "identifier2_1", "identifier3_1",
                                          "identType1_1", "identType2_1",
                                          "identType3_1", "mspFacilityNumber_1", 
                                          "pauth_lysonle", "catCode_1", "roleTypeCode_1",
                                          "hdsType_2", "hdsSubType_2", 
                                          "lysone@0001", "2024-05-27", "3000-12-31", "hdsName_1",
                                          "N", "test@example.com", "www.test.com",
                                          "456", "1234567", "ext456", 
                                          "789", "0123456", "fax456", "1234567", 
                                          "pcdServiceDeliveryType_2",
                                          "clinicType_1",
                                          "Y", 
                                          "sourceStatus_1",
                                          "clinicStatus_Active", 
                                          "2024-050-28", 
                                          "9999-12-31",
                                          "addressUnit_1",
                                          "buildingName_1",
                                          "civicAddrId_1",
                                          "civic_addr_2306 Selwyn Rd",
                                          "83.2232", "-78.99343",
                                          "West", "w", "typeprefix",
                                          "2306", "Selwyn", "Road",
                                          "localityName","BC","siteId_121","score_100",
                                          "precision_100", "100",
                                          "hsdaName", "databc_result",
                                          "pcnCode", "pcnName", "chsaStatus", "pcnStatus",
                                          "chsaCode", "chsaName", "lhaName", "haName",
                                          "relnType", "facTypeCode",
                                          "Address 5", "Address 6",
                                          "Address 7", "Address 8", "City2",
                                          "Province2", "Pcode2", "CANADA", "PRPS", "Valid",
                                          "Mailing 5", "Mailing 6", "Mailing 7", "Mailing 8",
                                          "Mail City2", "BC2", "Mailing Pcode2", "CANADA",
                                          "PRPS1","Invalid", "1000000", "VALID",
                                          createDate(2024, Calendar.FEBRUARY, 1), "Admin2",
                                          createDate(2024, Calendar.FEBRUARY, 2), "Admin3"));


        processData.add(createProcessData(2L, 3L, "Y", "RESIDENTAL CARE", "stakeholderid02",
                                         "IpcId321", "CpnId654", 
                                         "identifier1_2", "identifier2_2", "identifier3_2",
                                          "identType1_2", "identType2_2",
                                          "identType3_2", "mspFacilityNumber2", 
                                          "pauth_lyson", "catCode_2", "roleTypeCode_2",
                                          "hdsType_2", "hdsSubType_2", 
                                          "lysone@0001", "2024-05-27", "3000-12-31", "hdsName2",
                                          "N", "test@example.com", "www.test.com",
                                          "456", "1234567", "ext456", 
                                          "789", "0123456", "fax456", "1234567", 
                                          "pcdServiceDeliveryType_2",
                                          "clinicType2",
                                          "Y", 
                                          "sourceStatus_2",
                                          "clinicStatus_Active", 
                                          "2024-050-28", 
                                          "9999-12-31",
                                          "addressUnit_2",
                                          "Building Name2",
                                          "civic_addr_id_2",
                                          "civic_addr_2306 Selwyn Rd",
                                          "83.2232", "-78.99343",
                                          "West", "w", "typeprefix",
                                          "2306", "Selwyn", "Road",
                                          "localityName","BC","siteId_121","score_100",
                                          "precision_100", "100",
                                          "hsdaName", "databc_result",
                                          "pcnCode", "pcnName", "chsaStatus", "pcnStatus",
                                          "chsaCode", "chsaName", "lhaName", "haName",
                                          "relnType", "facTypeCode",
                                          "Address 5", "Address 6",
                                          "Address 7", "Address 8", "City2",
                                          "Province2", "Pcode2", "CANADA", "PRPS", "Valid",
                                          "Mailing 5", "Mailing 6", "Mailing 7", "Mailing 8",
                                          "Mail City2", "BC2", "Mailing Pcode2", "CANADA",
                                          "PRPS2","Invalid", "2000000", "INVALID",
                                          createDate(2024, Calendar.FEBRUARY, 1), "Admin2",
                                          createDate(2024, Calendar.FEBRUARY, 2), "Admin3"));
        return processData;

    }

    // Create and add three Control objects to the list


    private static ProcessData createProcessData(Long id, Long controlTableId, String doNotLoad, String stakeholder, String stakeholderId,
                                                 String hdsIpcId, String hdsCpnId, 
                                                 String hdsProviderIdentifier1,
                                                 String hdsProviderIdentifier2, String hdsProviderIdentifier3,
                                                 String hdsProviderIdentifierType1, String hdsProviderIdentifierType2, 
                                                 String hdsProviderIdentifierType3, String hdsMspFacilityNumber,
                                                 String hdsPauthId, String hdsCategoryCode, String hdsRoleTypeCode,
                                                 String hdsType, String hdsSubType, 
                                                 String hdsUserChid, String hdsCreatedDts, String hdsInvalidatedDts, String hdsName,
                                                 String hdsPreferredNameFlag, String hdsEmail, String hdsWebsite,
                                                 String hdsBusTelAreaCode, String hdsBusTelNumber, String hdsTelExtension,
                                                 String hdsCellAreaCode, String hdsCellNumber, String hdsFaxAreaCode, String hdsFaxNumber, 
                                                 String pcnServiceDeliveryType,
                                                 String pcnClinicType,
                                                 String pcnPciFlag, 
                                                 String sourceStatus, 
                                                 String pcnClinicStatus,
                                                 String hdsEffectiveStartDate, 
                                                 String hdsEffectiveEndDate,
                                                 String facAddressUnit,
                                                 String facBuildingName,
                                                 String facCivicAddrId, String facCivicAddr,
                                                 String facLatitude, String facLongitude,
                                                 String facStreetDirection, String streetDirectionPrefix, String streetTypePrefix,
                                                 String facCivicNumber, String facStreetName, String facStreetType,
                                                 String facLocalityName, String facProvinceCode, String facSiteId, String facScore,
                                                 String facMatchPrecision, String facPrecisionPoints,
                                                 String facHsdaName, String facDatabcResults,
                                                 String facPcnCode, String facPcnName, String facChsaStatus, String facPcnStatus,
                                                 String facChsaCode, String facChsaName, String facLhaName, String facHaName,
                                                 String facRelnType, String facTypeCode,
                                                 String physicalAddr1, String physicalAddr2, String physicalAddr3,
                                                 String physicalAddr4,
                                                 String physicalCity, String physicalProvince, String physicalPcode,
                                                 String physicalCountry,
                                                 String physicalAddrPrpsTypeCd, String physicalAddrValidationStatus,
                                                 String mailAddr1, String mailAddr2,
                                                 String mailAddr3, String mailAddr4, String mailCity, String mailBc,
                                                 String mailPcode, String mailCountry, 
                                                 String mailAddrPrpsTypeCd, String mailAddrValidationStatus,
                                                 String plrFacilityId, String rowstatusCode,
                                                 Date createdAt, String createdBy, Date updatedAt, String updatedBy) {

        return ProcessData.builder()
                          .id(id)
                          .controlTableId(controlTableId)
                          .doNotLoadFlag(doNotLoad)
                          .stakeholder(stakeholder)
                          .stakeholderId(stakeholderId)
                          .hdsIpcId(hdsIpcId)
                          .hdsCpnId(hdsCpnId)
                          .hdsProviderIdentifier1(hdsProviderIdentifier1)
                          .hdsProviderIdentifier2(hdsProviderIdentifier2)
                          .hdsProviderIdentifier3(hdsProviderIdentifier3)
                          .hdsProviderIdentifierType1(hdsProviderIdentifierType1)
                          .hdsProviderIdentifierType2(hdsProviderIdentifierType2)
                          .hdsProviderIdentifierType3(hdsProviderIdentifierType3)
                          .hdsMspFacilityNumber(hdsMspFacilityNumber)
                          .hdsPauthId(hdsPauthId)
                          .hdsCategoryCode(hdsCategoryCode)
                          .hdsRoleTypeCode(hdsRoleTypeCode)
                          .hdsType(hdsType)
                          .hdsSubType(hdsSubType)
                          .hdsUserChid(hdsUserChid)
                          .hdsCreatedDts(hdsCreatedDts)
                          .hdsInvalidatedDts(hdsInvalidatedDts)
                          .hdsName(hdsName)
                          .hdsPreferredNameFlag(hdsPreferredNameFlag)
                          .hdsEmail(hdsEmail)
                          .hdsWebsite(hdsWebsite)
                          .hdsBusTelAreaCode(hdsBusTelAreaCode)
                          .hdsBusTelNumber(hdsBusTelNumber)
                          .hdsTelExtension(hdsTelExtension)
                          .hdsCellAreaCode(hdsCellAreaCode)
                          .hdsCellNumber(hdsCellNumber)
                          .hdsFaxAreaCode(hdsFaxAreaCode)
                          .hdsFaxNumber(hdsFaxNumber)
                          .pcnServiceDeliveryType(pcnServiceDeliveryType)
                          .pcnClinicType(pcnClinicType)
                          .pcnPciFlag(pcnPciFlag)
                          .sourceStatus(sourceStatus)
                          .pcnClinicStatus(pcnClinicStatus)
                          .hdsEffectiveStartDate(hdsEffectiveStartDate)
                          .hdsEffectiveEndDate(hdsEffectiveEndDate)
                          .facAddressUnit(facAddressUnit)
                          .facBuildingName(facBuildingName)
                          .facCivicAddrId(facCivicAddrId)
                          .facCivicAddr(facCivicAddr)
                          .facLatitude(facLatitude)
                          .facLongitude(facLongitude)
                          .facStreetDirection(facStreetDirection)
                          .streetDirectionPrefix(streetDirectionPrefix)
                          .streetTypePrefix(streetTypePrefix)
                          .facCivicNumber(facCivicNumber)
                          .facStreetName(facStreetName)
                          .facStreetType(facStreetType)
                          .facLocalityName(facLocalityName)
                          .facProvinceCode(facProvinceCode)
                          .facSiteId(facSiteId)
                          .facScore(facScore)
                          .facMatchPrecision(facMatchPrecision)
                          .facPrecisionPoints(facPrecisionPoints)
                          .facHsdaName(facHsdaName)
                          .facDatabcResults(facDatabcResults)
                          .facPcnCode(facPcnCode)
                          .facPcnName(facPcnName)
                          .facChsaStatus(facChsaStatus)
                          .facPcnStatus(facPcnStatus)
                          .facChsaCode(facChsaCode)
                          .facChsaName(facChsaName)
                          .facLhaName(facLhaName)
                          .facHaName(facHaName)
                          .facRelnType(facRelnType)
                          .facTypeCode(facTypeCode)
                          .physicalAddr1(physicalAddr1)
                          .physicalAddr2(physicalAddr2)
                          .physicalAddr3(physicalAddr3)
                          .physicalAddr4(physicalAddr4)
                          .physicalCity(physicalCity)
                          .physicalProvince(physicalProvince)
                          .physicalPcode(physicalPcode)
                          .physicalCountry(physicalCountry)
                          .physicalAddrPrpsTypeCd(physicalAddrPrpsTypeCd)
                          .physicalAddrValidationStatus((physicalAddrValidationStatus))
                          .mailAddr1(mailAddr1)
                          .mailAddr2(mailAddr2)
                          .mailAddr3(mailAddr3)
                          .mailAddr4(mailAddr4)
                          .mailCity(mailCity)
                          .mailBc(mailBc)
                          .mailPcode(mailPcode)
                          .mailCountry(mailCountry)
                          .mailAddrPrpsTypeCd(mailAddrPrpsTypeCd)
                          .mailAddrValidationStatus(mailAddrValidationStatus)
                          .plrFacilityId(plrFacilityId)
                          .rowstatusCode(rowstatusCode)
                          .createdAt(createdAt)
                          .createdBy(createdBy)
                          .updatedAt(updatedAt)
                          .updatedBy(updatedBy)
                          .build();
    }
}
