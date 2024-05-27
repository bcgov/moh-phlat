package com.moh.phlat.backend.testsupport.factories;

import com.moh.phlat.backend.model.ProcessData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProcessDataFactory extends BaseFactory {


    public static List<ProcessData> createProcessDataListWithAllAttributes() {
        List<ProcessData> processData = new ArrayList<>();

        processData.add(createProcessData(1L, 2L, "N", "John Doe", "123",
                                          "456", "789", "101", "112",
                                          "type1", "type2", "type3",
                                          "hibc123", "type", "Name", "Alias", "Y",
                                          "example@example.com", "www.example.com", "123", "4567890",
                                          "ext123", "456", "7890123", "fax123",
                                          "4567890", "deliveryType", "clinicType",
                                          "pciFlag", "9am-5pm", "Jane Smith",
                                          "Y", "Active", "parent123", "bus123",
                                          "cpn123", "Business Name", "Legal Name", "payee123",
                                          "Owner Name", "Owner Type", "Other",
                                          "Building Name", "Additional Info",
                                          "Address 1", "Address 2", "Address 3", "Address 4",
                                          "City", "Province", "Pcode", "Country",
                                          "Y", "Mailing 1", "Mailing 2", "Mailing 3",
                                          "Mailing 4", "Mail City", "BC", "Mailing Pcode",
                                          "Mailing Country", "Y", 100L, "ACTIVE",
                                           createDate(2024, Calendar.JANUARY, 1), "Admin1",
                                          createDate(2024, Calendar.JANUARY, 2), "Admin2"));


        processData.add(createProcessData(2L, 3L, "Y", "Jane Smith", "321",
                                          "654", "987", "210", "121",
                                          "type4", "type5", "type6",
                                          "hibc456", "type2", "Name2", "Alias2",
                                          "N", "test@example.com", "www.test.com",
                                          "456", "1234567", "ext456", "789",
                                          "0123456", "fax456", "1234567", "deliveryType2",
                                          "clinicType2", "pciFlag2", "8am-4pm",
                                          "John Doe", "N", "Inactive", "parent456",
                                          "bus321", "cpn321", "Business Name2",
                                          "Legal Name2", "payee321", "Owner Name2",
                                          "Owner Type2", "Other2", "Building Name2",
                                          "Additional Info2", "Address 5", "Address 6",
                                          "Address 7", "Address 8", "City2",
                                          "Province2", "Pcode2", "Country2", "N",
                                          "Mailing 5", "Mailing 6", "Mailing 7", "Mailing 8",
                                          "Mail City2", "BC2", "Mailing Pcode2", "Mailing Country2",
                                          "N", 200L, "INACTIVE",
                                          createDate(2024, Calendar.FEBRUARY, 1), "Admin2",
                                          createDate(2024, Calendar.FEBRUARY, 2), "Admin3"));
        return processData;

    }

    // Create and add three Control objects to the list


    private static ProcessData createProcessData(Long id, Long controlTableId, String doNotLoad, String stakeholder,
                                                 String hdsIpcId, String hdsCpnId, String hdsProviderIdentifier1,
                                                 String hdsProviderIdentifier2, String hdsProviderIdentifier3,
                                                 String hdsProviderIdentifierType1, String hdsProviderIdentifierType2,
                                                 String hdsProviderIdentifierType3, String hdsHibcFacilityId,
                                                 String hdsType, String hdsName, String hdsNameAlias,
                                                 String hdsPreferredNameFlag, String hdsEmail, String hdsWebsite,
                                                 String hdsBusTelAreaCode, String hdsBusTelNumber,
                                                 String hdsTelExtension,
                                                 String hdsCellAreaCode, String hdsCellNumber, String hdsFaxAreaCode,
                                                 String hdsFaxNumber, String hdsServiceDeliveryType,
                                                 String pcnClinicType,
                                                 String pcnPciFlag, String hdsHoursOfOperation, String hdsContactName,
                                                 String hdsIsForProfitFlag, String sourceStatus, String hdsParentIpcId,
                                                 String busIpcId, String busCpnId, String busName, String busLegalName,
                                                 String busPayeeNumber, String busOwnerName, String busOwnerType,
                                                 String busOwnerTypeOther, String facBuildingName,
                                                 String facilityHdsDetailsAdditionalInfo,
                                                 String physicalAddr1, String physicalAddr2, String physicalAddr3,
                                                 String physicalAddr4,
                                                 String physicalCity, String physicalProvince, String physicalPcode,
                                                 String physicalCountry,
                                                 String physAddrIsPrivate, String mailAddr1, String mailAddr2,
                                                 String mailAddr3, String mailAddr4, String mailCity, String mailBc,
                                                 String mailPcode, String mailCountry, String mailAddrIsPrivate,
                                                 Long facility_id, String rowstatusCode,
                                                 Date createdAt, String createdBy, Date updatedAt, String updatedBy) {

        return ProcessData.builder()
                          .id(id)
                          .controlTableId(controlTableId)
                          .doNotLoadFlag(doNotLoad)
                          .stakeholder(stakeholder)
                          .hdsIpcId(hdsIpcId)
                          .hdsCpnId(hdsCpnId)
                          .hdsProviderIdentifier1(hdsProviderIdentifier1)
                          .hdsProviderIdentifier2(hdsProviderIdentifier2)
                          .hdsProviderIdentifier3(hdsProviderIdentifier3)
                          .hdsProviderIdentifierType1(hdsProviderIdentifierType1)
                          .hdsProviderIdentifierType2(hdsProviderIdentifierType2)
                          .hdsProviderIdentifierType3(hdsProviderIdentifierType3)
//                          .hdsHibcFacilityId(hdsHibcFacilityId)
                          .hdsType(hdsType)
                          .hdsName(hdsName)
//                          .hdsNameAlias(hdsNameAlias)
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
//                          .hdsServiceDeliveryType(hdsServiceDeliveryType)
                          .pcnClinicType(pcnClinicType)
                          .pcnPciFlag(pcnPciFlag)
//                          .hdsHoursOfOperation(hdsHoursOfOperation)
//                          .hdsContactName(hdsContactName)
//                          .hdsIsForProfitFlag(hdsIsForProfitFlag)
                          .sourceStatus(sourceStatus)
//                          .hdsParentIpcId(hdsParentIpcId)
//                          .busIpcId(busIpcId)
//                          .busCpnId(busCpnId)
//                          .busName(busName)
//                          .busLegalName(busLegalName)
//                          .busPayeeNumber(busPayeeNumber)
//                          .busOwnerName(busOwnerName)
//                          .busOwnerType(busOwnerType)
//                          .busOwnerTypeOther(busOwnerTypeOther)
                          .facBuildingName(facBuildingName)
//                          .facilityHdsDetailsAdditionalInfo(facilityHdsDetailsAdditionalInfo)
                          .physicalAddr1(physicalAddr1)
                          .physicalAddr2(physicalAddr2)
                          .physicalAddr3(physicalAddr3)
                          .physicalAddr4(physicalAddr4)
                          .physicalCity(physicalCity)
                          .physicalProvince(physicalProvince)
                          .physicalPcode(physicalPcode)
                          .physicalCountry(physicalCountry)
//                          .physAddrIsPrivate(physAddrIsPrivate)
                          .mailAddr1(mailAddr1)
                          .mailAddr2(mailAddr2)
                          .mailAddr3(mailAddr3)
                          .mailAddr4(mailAddr4)
                          .mailCity(mailCity)
                          .mailBc(mailBc)
                          .mailPcode(mailPcode)
                          .mailCountry(mailCountry)
//                          .mailAddrIsPrivate(mailAddrIsPrivate)
//                          .facility_id(facility_id)
                          .rowstatusCode(rowstatusCode)
                          .createdAt(createdAt)
                          .createdBy(createdBy)
                          .updatedAt(updatedAt)
                          .updatedBy(updatedBy)
                          .build();


    }
}
