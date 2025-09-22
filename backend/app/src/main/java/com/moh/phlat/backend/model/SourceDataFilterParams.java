package com.moh.phlat.backend.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SourceDataFilterParams {
	private List<String> id;
	private List<String> doNotLoadFlag;
	private List<String> stakeholder;
	private List<String> stakeholderId;
	private List<String> hdsIpcId;
	private List<String> hdsCpnId;
	private List<String> hdsProviderIdentifier1;
	private List<String> hdsProviderIdentifier2;
	private List<String> hdsProviderIdentifier3;
	private List<String> hdsProviderIdentifierType1;
	private List<String> hdsProviderIdentifierType2;
	private List<String> hdsProviderIdentifierType3;
	private List<String> hdsMspFacilityNumber;
	private List<String> hdsHibcFacId;
	private List<String> hdsType;
	private List<String> hdsSubType;
	private List<String> hdsName;
	private List<String> hdsPreferredNameFlag;
	private List<String> hdsEmail;
	private List<String> hdsWebsite;
	private List<String> hdsBusTelAreaCode;
	private List<String> hdsBusTelNumber;
	private List<String> hdsTelExtension;
	private List<String> hdsCellAreaCode;
	private List<String> hdsCellNumber;
	private List<String> hdsFaxAreaCode;
	private List<String> hdsFaxNumber;
	private List<String> pcnServiceDeliveryType;
	private List<String> pcnClinicType;
	private List<String> pcnClinicStatus;
	private List<String> pcnPciFlag;
	private List<String> hdsHoursOfOp;
	private List<String> hdsContactName;
	private List<String> hdsIsForProfitFlag;
	private List<String> sourceStatus;
	private List<String> hdsEffectiveStartDate;
	private List<String> hdsParentIpcId;
	private List<String> busIpcId;
	private List<String> busCpnId;
	private List<String> busName;
	private List<String> busLegalName;
	private List<String> busPayeeNum;
	private List<String> busOwnerName;
	private List<String> busOwnerType;
	private List<String> busOwnerTypeOther;
	private List<String> facAddressUnit;
	private List<String> facBuildingName;
	private List<String> facHdsDetailAddInfo;
	private List<String> physicalAddr1;
	private List<String> physicalAddr2;
	private List<String> physicalAddr3;
	private List<String> physicalAddr4;
	private List<String> physicalCity;
	private List<String> physicalProv;
	private List<String> physicalPcode;
	private List<String> physicalCountry;
	private List<String> physicalAddrPrpsTypeCd;
	private List<String> mailAddr1;
	private List<String> mailAddr2;
	private List<String> mailAddr3;
	private List<String> mailAddr4;
	private List<String> mailCity;
	private List<String> mailProvince;
	private List<String> mailPcode;
	private List<String> mailCountry;
	private List<String> mailAddrIsPriv;
	private Map<String,String> sort;
}
