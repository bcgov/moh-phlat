package com.moh.phlat.backend.model;

import java.util.List;

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
public class ParamProcess{
	 private List<String> id;
	 private List<String> actions;
	 private List<String> rowStatusCode;
	 private List<String> doNotLoad;
	 private List<String> stakeholder;
	 private List<String> hdsLpcId;
	 private List<String> hdsCpnId;
	 private List<String> hdsProviderId1;
	 private List<String> hdsProviderId2;
	 private List<String> hdsProviderId3;
	 private List<String> hdsProviderIdType1;
	 private List<String> hdsProviderIdType2;
	 private List<String> hdsProviderIdType3;
	 private List<String> hdsHibcFacId;
	 private List<String> hdsType;
	 private List<String> hdsName;
	 private List<String> hdsNameAlias;
	 private List<String> hdsPrefNameFlag;
	 private List<String> hdsEmail;
	 private List<String> hdsWebsite;
	 private List<String> hdsBusTelAreaCode;
	 private List<String> hdsBusTelNum;
	 private List<String> hdsTelExt;
	 private List<String> hdsCellAreaCode;
	 private List<String> hdsCellNum;
	 private List<String> hdsFaxAreaCode;
	 private List<String> hdsFaxNum;
	 private List<String> hdsServiceDelType;
	 private List<String> pcnClinicType;
	 private List<String> pcnPciFlag;
	 private List<String> hdsHoursOfOp;
	 private List<String> hdsContactName;
	 private List<String> hdsIsForProfitFlag;
	 private List<String> sourceStatus;
	 private List<String> hdsParentIpcId;
	 private List<String> busIpcId;
	 private List<String> busCpnId;
	 private List<String> busName;
	 private List<String> busLegalName;
	 private List<String> busPayeeNum;
	 private List<String> busOwnerName;
	 private List<String> busOwnerType;
	 private List<String> busOwnerTypeOther;
	 private List<String> facBuildingName;
	 private List<String> facHdsDetailAddInfo;
	 private List<String> physAddr1;
	 private List<String> physAddr2;
	 private List<String> physAddr3;
	 private List<String> physAddr4;
	 private List<String> physCity;
	 private List<String> physProv;
	 private List<String> physPCode;
	 private List<String> physCountry;
	 private List<String> physAddrIsPrivate;
	 private List<String> mailAddr1;
	 private List<String> mailAddr2;
	 private List<String> mailAddr3;
	 private List<String> mailAddr4;
	 private List<String> mailCity;
	 private List<String> mailBc;
	 private List<String> mailPcode;
	 private List<String> mailCountry;
	 private List<String> mailAddrIsPriv;
	 private List<String> messages;
}