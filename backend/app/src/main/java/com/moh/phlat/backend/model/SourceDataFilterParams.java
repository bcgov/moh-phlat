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
public class SourceDataFilterParams {
	private List<String> id;
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
	private List<String> pcnCLinicType;
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
	
	public List<String> getId() {
		return id;
	}
	public void setId(List<String> id) {
		this.id = id;
	}
	public List<String> getDoNotLoad() {
		return doNotLoad;
	}
	public void setDoNotLoad(List<String> doNotLoad) {
		this.doNotLoad = doNotLoad;
	}
	public List<String> getStakeholder() {
		return stakeholder;
	}
	public void setStakeholder(List<String> stakeholder) {
		this.stakeholder = stakeholder;
	}
	public List<String> getHdsLpcId() {
		return hdsLpcId;
	}
	public void setHdsLpcId(List<String> hdsLpcId) {
		this.hdsLpcId = hdsLpcId;
	}
	public List<String> getHdsCpnId() {
		return hdsCpnId;
	}
	public void setHdsCpnId(List<String> hdsCpnId) {
		this.hdsCpnId = hdsCpnId;
	}
	public List<String> getHdsProviderId1() {
		return hdsProviderId1;
	}
	public void setHdsProviderId1(List<String> hdsProviderId1) {
		this.hdsProviderId1 = hdsProviderId1;
	}
	public List<String> getHdsProviderId2() {
		return hdsProviderId2;
	}
	public void setHdsProviderId2(List<String> hdsProviderId2) {
		this.hdsProviderId2 = hdsProviderId2;
	}
	public List<String> getHdsProviderId3() {
		return hdsProviderId3;
	}
	public void setHdsProviderId3(List<String> hdsProviderId3) {
		this.hdsProviderId3 = hdsProviderId3;
	}
	public List<String> getHdsProviderIdType1() {
		return hdsProviderIdType1;
	}
	public void setHdsProviderIdType1(List<String> hdsProviderIdType1) {
		this.hdsProviderIdType1 = hdsProviderIdType1;
	}
	public List<String> getHdsProviderIdType2() {
		return hdsProviderIdType2;
	}
	public void setHdsProviderIdType2(List<String> hdsProviderIdType2) {
		this.hdsProviderIdType2 = hdsProviderIdType2;
	}
	public List<String> getHdsProviderIdType3() {
		return hdsProviderIdType3;
	}
	public void setHdsProviderIdType3(List<String> hdsProviderIdType3) {
		this.hdsProviderIdType3 = hdsProviderIdType3;
	}
	public List<String> getHdsHibcFacId() {
		return hdsHibcFacId;
	}
	public void setHdsHibcFacId(List<String> hdsHibcFacId) {
		this.hdsHibcFacId = hdsHibcFacId;
	}
	public List<String> getHdsType() {
		return hdsType;
	}
	public void setHdsType(List<String> hdsType) {
		this.hdsType = hdsType;
	}
	public List<String> getHdsName() {
		return hdsName;
	}
	public void setHdsName(List<String> hdsName) {
		this.hdsName = hdsName;
	}
	public List<String> getHdsNameAlias() {
		return hdsNameAlias;
	}
	public void setHdsNameAlias(List<String> hdsNameAlias) {
		this.hdsNameAlias = hdsNameAlias;
	}
	public List<String> getHdsPrefNameFlag() {
		return hdsPrefNameFlag;
	}
	public void setHdsPrefNameFlag(List<String> hdsPrefNameFlag) {
		this.hdsPrefNameFlag = hdsPrefNameFlag;
	}
	public List<String> getHdsEmail() {
		return hdsEmail;
	}
	public void setHdsEmail(List<String> hdsEmail) {
		this.hdsEmail = hdsEmail;
	}
	public List<String> getHdsWebsite() {
		return hdsWebsite;
	}
	public void setHdsWebsite(List<String> hdsWebsite) {
		this.hdsWebsite = hdsWebsite;
	}
	public List<String> getHdsBusTelAreaCode() {
		return hdsBusTelAreaCode;
	}
	public void setHdsBusTelAreaCode(List<String> hdsBusTelAreaCode) {
		this.hdsBusTelAreaCode = hdsBusTelAreaCode;
	}
	public List<String> getHdsBusTelNum() {
		return hdsBusTelNum;
	}
	public void setHdsBusTelNum(List<String> hdsBusTelNum) {
		this.hdsBusTelNum = hdsBusTelNum;
	}
	public List<String> getHdsTelExt() {
		return hdsTelExt;
	}
	public void setHdsTelExt(List<String> hdsTelExt) {
		this.hdsTelExt = hdsTelExt;
	}
	public List<String> getHdsCellAreaCode() {
		return hdsCellAreaCode;
	}
	public void setHdsCellAreaCode(List<String> hdsCellAreaCode) {
		this.hdsCellAreaCode = hdsCellAreaCode;
	}
	public List<String> getHdsCellNum() {
		return hdsCellNum;
	}
	public void setHdsCellNum(List<String> hdsCellNum) {
		this.hdsCellNum = hdsCellNum;
	}
	public List<String> getHdsFaxAreaCode() {
		return hdsFaxAreaCode;
	}
	public void setHdsFaxAreaCode(List<String> hdsFaxAreaCode) {
		this.hdsFaxAreaCode = hdsFaxAreaCode;
	}
	public List<String> getHdsFaxNum() {
		return hdsFaxNum;
	}
	public void setHdsFaxNum(List<String> hdsFaxNum) {
		this.hdsFaxNum = hdsFaxNum;
	}
	public List<String> getHdsServiceDelType() {
		return hdsServiceDelType;
	}
	public void setHdsServiceDelType(List<String> hdsServiceDelType) {
		this.hdsServiceDelType = hdsServiceDelType;
	}
	public List<String> getPcnCLinicType() {
		return pcnCLinicType;
	}
	public void setPcnCLinicType(List<String> pcnCLinicType) {
		this.pcnCLinicType = pcnCLinicType;
	}
	public List<String> getPcnPciFlag() {
		return pcnPciFlag;
	}
	public void setPcnPciFlag(List<String> pcnPciFlag) {
		this.pcnPciFlag = pcnPciFlag;
	}
	public List<String> getHdsHoursOfOp() {
		return hdsHoursOfOp;
	}
	public void setHdsHoursOfOp(List<String> hdsHoursOfOp) {
		this.hdsHoursOfOp = hdsHoursOfOp;
	}
	public List<String> getHdsContactName() {
		return hdsContactName;
	}
	public void setHdsContactName(List<String> hdsContactName) {
		this.hdsContactName = hdsContactName;
	}
	public List<String> getHdsIsForProfitFlag() {
		return hdsIsForProfitFlag;
	}
	public void setHdsIsForProfitFlag(List<String> hdsIsForProfitFlag) {
		this.hdsIsForProfitFlag = hdsIsForProfitFlag;
	}
	public List<String> getSourceStatus() {
		return sourceStatus;
	}
	public void setSourceStatus(List<String> sourceStatus) {
		this.sourceStatus = sourceStatus;
	}
	public List<String> getHdsParentIpcId() {
		return hdsParentIpcId;
	}
	public void setHdsParentIpcId(List<String> hdsParentIpcId) {
		this.hdsParentIpcId = hdsParentIpcId;
	}
	public List<String> getBusIpcId() {
		return busIpcId;
	}
	public void setBusIpcId(List<String> busIpcId) {
		this.busIpcId = busIpcId;
	}
	public List<String> getBusCpnId() {
		return busCpnId;
	}
	public void setBusCpnId(List<String> busCpnId) {
		this.busCpnId = busCpnId;
	}
	public List<String> getBusName() {
		return busName;
	}
	public void setBusName(List<String> busName) {
		this.busName = busName;
	}
	public List<String> getBusLegalName() {
		return busLegalName;
	}
	public void setBusLegalName(List<String> busLegalName) {
		this.busLegalName = busLegalName;
	}
	public List<String> getBusPayeeNum() {
		return busPayeeNum;
	}
	public void setBusPayeeNum(List<String> busPayeeNum) {
		this.busPayeeNum = busPayeeNum;
	}
	public List<String> getBusOwnerName() {
		return busOwnerName;
	}
	public void setBusOwnerName(List<String> busOwnerName) {
		this.busOwnerName = busOwnerName;
	}
	public List<String> getBusOwnerType() {
		return busOwnerType;
	}
	public void setBusOwnerType(List<String> busOwnerType) {
		this.busOwnerType = busOwnerType;
	}
	public List<String> getBusOwnerTypeOther() {
		return busOwnerTypeOther;
	}
	public void setBusOwnerTypeOther(List<String> busOwnerTypeOther) {
		this.busOwnerTypeOther = busOwnerTypeOther;
	}
	public List<String> getFacBuildingName() {
		return facBuildingName;
	}
	public void setFacBuildingName(List<String> facBuildingName) {
		this.facBuildingName = facBuildingName;
	}
	public List<String> getFacHdsDetailAddInfo() {
		return facHdsDetailAddInfo;
	}
	public void setFacHdsDetailAddInfo(List<String> facHdsDetailAddInfo) {
		this.facHdsDetailAddInfo = facHdsDetailAddInfo;
	}
	public List<String> getPhysAddr1() {
		return physAddr1;
	}
	public void setPhysAddr1(List<String> physAddr1) {
		this.physAddr1 = physAddr1;
	}
	public List<String> getPhysAddr2() {
		return physAddr2;
	}
	public void setPhysAddr2(List<String> physAddr2) {
		this.physAddr2 = physAddr2;
	}
	public List<String> getPhysAddr3() {
		return physAddr3;
	}
	public void setPhysAddr3(List<String> physAddr3) {
		this.physAddr3 = physAddr3;
	}
	public List<String> getPhysAddr4() {
		return physAddr4;
	}
	public void setPhysAddr4(List<String> physAddr4) {
		this.physAddr4 = physAddr4;
	}
	public List<String> getPhysCity() {
		return physCity;
	}
	public void setPhysCity(List<String> physCity) {
		this.physCity = physCity;
	}
	public List<String> getPhysProv() {
		return physProv;
	}
	public void setPhysProv(List<String> physProv) {
		this.physProv = physProv;
	}
	public List<String> getPhysPCode() {
		return physPCode;
	}
	public void setPhysPCode(List<String> physPCode) {
		this.physPCode = physPCode;
	}
	public List<String> getPhysCountry() {
		return physCountry;
	}
	public void setPhysCountry(List<String> physCountry) {
		this.physCountry = physCountry;
	}
	public List<String> getPhysAddrIsPrivate() {
		return physAddrIsPrivate;
	}
	public void setPhysAddrIsPrivate(List<String> physAddrIsPrivate) {
		this.physAddrIsPrivate = physAddrIsPrivate;
	}
	public List<String> getMailAddr1() {
		return mailAddr1;
	}
	public void setMailAddr1(List<String> mailAddr1) {
		this.mailAddr1 = mailAddr1;
	}
	public List<String> getMailAddr2() {
		return mailAddr2;
	}
	public void setMailAddr2(List<String> mailAddr2) {
		this.mailAddr2 = mailAddr2;
	}
	public List<String> getMailAddr3() {
		return mailAddr3;
	}
	public void setMailAddr3(List<String> mailAddr3) {
		this.mailAddr3 = mailAddr3;
	}
	public List<String> getMailAddr4() {
		return mailAddr4;
	}
	public void setMailAddr4(List<String> mailAddr4) {
		this.mailAddr4 = mailAddr4;
	}
	public List<String> getMailCity() {
		return mailCity;
	}
	public void setMailCity(List<String> mailCity) {
		this.mailCity = mailCity;
	}
	public List<String> getMailBc() {
		return mailBc;
	}
	public void setMailBc(List<String> mailBc) {
		this.mailBc = mailBc;
	}
	public List<String> getMailPcode() {
		return mailPcode;
	}
	public void setMailPcode(List<String> mailPcode) {
		this.mailPcode = mailPcode;
	}
	public List<String> getMailCountry() {
		return mailCountry;
	}
	public void setMailCountry(List<String> mailCountry) {
		this.mailCountry = mailCountry;
	}
	public List<String> getMailAddrIsPriv() {
		return mailAddrIsPriv;
	}
	public void setMailAddrIsPriv(List<String> mailAddrIsPriv) {
		this.mailAddrIsPriv = mailAddrIsPriv;
	}
}
