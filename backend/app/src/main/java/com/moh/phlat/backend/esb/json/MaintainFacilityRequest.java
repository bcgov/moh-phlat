package com.moh.phlat.backend.esb.json;

import ca.bc.gov.health.plr.dto.esb.MaintainProviderRequest;
import ca.bc.gov.health.plr.dto.facility.esb.CivicAddressDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDetailsDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityIdentifierDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityNameDto;
import ca.bc.gov.health.plr.dto.provider.esb.AddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.NoteDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;
import ca.bc.gov.health.plr.dto.provider.esb.TelecommunicationDto;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.util.StringUtils;

public class MaintainFacilityRequest implements PlrRequest {
	
	public MaintainFacilityRequest(Control control) {
		
	}
	
	@Override
	public String processDataToPlrJson(ProcessData data) {
		try {
			DateFormat df = JSON_DATE_FORMAT_OJDK11;
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(df);
			om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			om.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
			om.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
			om.setSerializationInclusion(Include.NON_NULL);
			
			return om.writeValueAsString(createMaintainProviderRequest(data));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	private MaintainProviderRequest createMaintainProviderRequest(ProcessData data) {
		MaintainProviderRequest mpr = new MaintainProviderRequest();
		mpr.setUniqueMessageID(UUID.randomUUID().toString());
		mpr.setMessageCreationTime(new Date());
		mpr.setProcessingCode("T");
		mpr.setProcessModeCode("T");
		mpr.setAcceptAckCode("AA");
		mpr.setRegistryUserOrgId("PHLAT");
		mpr.setEsbMetadata(createEsbMetadata(data));
		mpr.setProviderDetails(createProviderDetails(data));
		mpr.setFacility(createFacilityDto(data));
		return mpr;
	}

	private Map<String,Object> createEsbMetadata(ProcessData input) {
		Map<String,Object> emd = new HashMap<String,Object>();
		emd.put("Role", "REG_ADMIN");
		emd.put("OrganizationID", "00002855");
		emd.put("isLRA", true);
		return emd;
	}
	
	private ProviderDetails createProviderDetails(ProcessData input) {
		ProviderDetails pd = new ProviderDetails();
		return pd;
	}
	
	private FacilityDto createFacilityDto(ProcessData input) {
		FacilityDto fd = new FacilityDto();
		fd.setFacilityDetails(createFacilityDetailsDtos(input));
		fd.setFacilityNames(createFacilityNameDtos(input));
		//fd.setFacilityIdentifiers(createFacilityIdentifierDtos(input));
		//fd.setNotes(createNoteDtos(input));
		fd.setAddresses(createAddressDtos(input));
		fd.setCivicAddresses(createCivicAddressDtos(input));
		fd.setTelecommunication(createTelecomunicationDtos(input));
		return fd;
	}
	
	private List<FacilityDetailsDto> createFacilityDetailsDtos(ProcessData input) {
		
		FacilityDetailsDto fdd = new FacilityDetailsDto();
		fdd.setCreatedDate(input.getCreatedAt());
		fdd.setEffectiveStartDate(input.getCreatedAt());
		fdd.setType("BUILDING");
		fdd.setTypeCode("BUILDING");
		if (StringUtils.hasText(input.getFacilityHdsDetailsAdditionalInfo())) {
			fdd.setAdditionalInfo(input.getFacilityHdsDetailsAdditionalInfo());
		}
		
		List<FacilityDetailsDto> output = new ArrayList<FacilityDetailsDto>();
		output.add(fdd);
		return output;
	}
	
	private List<FacilityNameDto> createFacilityNameDtos(ProcessData input) {
		
		if (!StringUtils.hasText(input.getFacBuildingName() + input.getHdsName())) {
			return null;
		}
		
		FacilityNameDto fnd = new FacilityNameDto();
		if (StringUtils.hasText(input.getHdsName())) {
			fnd.setName(input.getHdsName());
		} else if (StringUtils.hasText(input.getFacBuildingName())) {
			fnd.setName(input.getFacBuildingName());
		}
		fnd.setCreatedDate(input.getCreatedAt());
		fnd.setEffectiveStartDate(input.getCreatedAt());
		
		List<FacilityNameDto> output = new ArrayList<FacilityNameDto>();
		output.add(fnd);
		return output;
	}
	
	private List<FacilityIdentifierDto> createFacilityIdentifierDtos(ProcessData input) {
		FacilityIdentifierDto fid = new FacilityIdentifierDto();
		fid.setCreatedDate(input.getCreatedAt());
		fid.setEffectiveStartDate(input.getCreatedAt());
		
		List<FacilityIdentifierDto> output = new ArrayList<FacilityIdentifierDto>();
		output.add(fid);
		return output;
	}
	
	private List<NoteDto> createNoteDtos(ProcessData input) {
		NoteDto nd = new NoteDto();
		nd.setCreatedDate(input.getCreatedAt());
		nd.setEffectiveStartDate(input.getCreatedAt());
		
		List<NoteDto> output = new ArrayList<NoteDto>();
		output.add(nd);
		return output;
	}
	
	private List<AddressDto> createAddressDtos(ProcessData input) {
		List<AddressDto> output = new ArrayList<AddressDto>();
		output.add(createPhysicalAddressDto(input));
		if (StringUtils.hasText(input.getMailAddr1()) && StringUtils.hasText(input.getMailBc())) {
			output.add(createMailingAddressDto(input));
		}
		return output;
	}
	
	private AddressDto createPhysicalAddressDto(ProcessData input) {
		AddressDto ad = new AddressDto();
		ad.setActive(true);
		ad.setAddressLineOne(input.getPhysicalAddr1());
		if (StringUtils.hasText(input.getPhysicalAddr2())) {
			ad.setAddressLineTwo(input.getPhysicalAddr2());
		}
		if (StringUtils.hasText(input.getPhysicalAddr3())) {
			ad.setAddressLineThree(input.getPhysicalAddr3());
		}
		if (StringUtils.hasText(input.getPhysicalAddr4())) {
			ad.setAddressLineFour(input.getPhysicalAddr4());
		}
		if (StringUtils.hasText(input.getPhysicalCity())) {
			ad.setCity(input.getPhysicalCity());
		}
		ad.setTypeCode("P");
		//ad.setCommunicationPurpose("");
		ad.setCommunicationPurposeCode("FC");
		//ad.setCommunicationPurposeId(0L);
		ad.setCountry(input.getPhysicalCountry());
		ad.setCountryCode("CA");
		//ad.setCountryId(0L);
		ad.setCreatedDate(input.getCreatedAt());
		//ad.setDataOwnerCode("");
		//ad.setDataOwnerName("");
		ad.setDisplayActive(true);
		ad.setEffectiveStartDate(input.getCreatedAt());
		//ad.setFacilityId(0L);
		//ad.setFullAddress("");
		//ad.setGenerationNum(0L);
		//ad.setId(0L);
		ad.setNoChangeOnUpdate(true);
		//ad.setPauthId(0L);
		if (StringUtils.hasText(input.getPhysicalPcode())) {
			ad.setPostalCode(input.getPhysicalPcode());
		}
		ad.setProvinceOrStateTxt(input.getPhysicalProvince());
		//ad.setRuRuID(0L);
		//ad.setType("");
		//ad.setTypeCode("");
		//ad.setTypeId(0L);
		ad.setUpdatable(true);
		//ad.setUserChid("");
		ad.setValidCpc(true);
		//ad.setValidationStatus("");
		//ad.setValidationStatusDesc("");
		//ad.setWlWlId(0L);
		
		return ad;
	}
	
	private AddressDto createMailingAddressDto(ProcessData input) {
		AddressDto ad = new AddressDto();
		ad.setActive(true);
		ad.setAddressLineOne(input.getMailAddr1());
		if (StringUtils.hasText(input.getMailAddr2())) {
			ad.setAddressLineTwo(input.getMailAddr2());
		}
		if (StringUtils.hasText(input.getMailAddr3())) {
			ad.setAddressLineThree(input.getMailAddr3());
		}
		if (StringUtils.hasText(input.getMailAddr4())) {
			ad.setAddressLineFour(input.getMailAddr4());
		}
		if (StringUtils.hasText(input.getMailCity())) {
			ad.setCity(input.getMailCity());
		}
		ad.setTypeCode("M");
		//ad.setCommunicationPurpose("");
		ad.setCommunicationPurposeCode("FC");
		//ad.setCommunicationPurposeId(0L);
		ad.setCountry(input.getMailCountry());
		ad.setCountryCode("CA");
		//ad.setCountryId(0L);
		ad.setCreatedDate(input.getCreatedAt());
		//ad.setDataOwnerCode("");
		//ad.setDataOwnerName("");
		ad.setDisplayActive(true);
		ad.setEffectiveStartDate(input.getCreatedAt());
		//ad.setFacilityId(0L);
		//ad.setFullAddress("");
		//ad.setGenerationNum(0L);
		//ad.setId(0L);
		ad.setNoChangeOnUpdate(true);
		//ad.setPauthId(0L);
		if (StringUtils.hasText(input.getMailPcode())) {
			ad.setPostalCode(input.getMailPcode());
		}
		ad.setProvinceOrStateTxt(input.getMailBc());
		//ad.setRuRuID(0L);
		//ad.setType("");
		//ad.setTypeCode("");
		//ad.setTypeId(0L);
		ad.setUpdatable(true);
		//ad.setUserChid("");
		ad.setValidCpc(true);
		//ad.setValidationStatus("");
		//ad.setValidationStatusDesc("");
		//ad.setWlWlId(0L);
		
		return ad;
	}
	
	private List<CivicAddressDto> createCivicAddressDtos(ProcessData input) {
		CivicAddressDto cad = new CivicAddressDto();
		cad.setActive(true);
		if (StringUtils.hasText(input.getPhysicalAddr1())) {
			cad.setAddressLineOne(input.getPhysicalAddr1());
		}
		if (StringUtils.hasText(input.getPhysicalAddr2())) {
			cad.setAddressLineTwo(input.getPhysicalAddr2());
		}
		if (StringUtils.hasText(input.getPhysicalAddr3())) {
			cad.setAddressLineThree(input.getPhysicalAddr3());
		}
		if (StringUtils.hasText(input.getPhysicalCity())) {
			cad.setCity(input.getPhysicalCity());
		}
		if (StringUtils.hasText(input.getPhysicalProvince())) {
			cad.setProvinceOrStateTxt(input.getPhysicalProvince());
		}
		cad.setCountryCode("CA");
		cad.setCreatedDate(input.getCreatedAt());
		//cad.setDataOwnerCode("");
		//cad.setDataOwnerName("");
		cad.setDisplayActive(true);
		cad.setEffectiveStartDate(input.getCreatedAt());
		//cad.setFacilityId(0L);
		//cad.setFullAddress("");
		//cad.setGenerationNum(0L);
		//cad.setId(0L);
		cad.setNoChangeOnUpdate(true);
		//cad.setPauthId(0L);
		//cad.setType("");
		//cad.setTypeCode("");
		//cad.setTypeId(0L);
		cad.setUpdatable(true);
		//cad.setUserChid("");
		//cad.setChsaDescTxt("");
		//cad.setChsaHealthAreaCode(0L);
		//cad.setChsaNameCode("");
		//cad.setChsaStatus("");
		//cad.setDataBCResult("");
		//cad.setHaDescTxt("");
		//cad.setHsdaDescTxt("");
		//cad.setLatitude(BigDecimal.ONE);
		//cad.setLongitude(BigDecimal.ONE);
		//cad.setLatitudeCHSA(BigDecimal.ONE);
		//cad.setLongitudeCHSA(BigDecimal.ONE);
		//cad.setLhaDescTxt("");
		//cad.setMatchPrecision("");
		//cad.setNumber(0L);
		//cad.setPcnDescTxt("");
		//cad.setPcnNameCode("");
		//cad.setPcnStatus("");
		//cad.setPrecisionPoints(0);
		//cad.setScore(0);
		//cad.setSiteID("");
		//cad.setStreetDirection("");
		//cad.setStreetName("");
		//cad.setStreetType("");
		//cad.setUnit(0L);
		cad.setIsDirectionPrefix(true);
		cad.setIsTypePrefix(true);
		
		List<CivicAddressDto> output = new ArrayList<CivicAddressDto>();
		output.add(cad);
		return output;
	}
	
	private List<TelecommunicationDto> createTelecomunicationDtos(ProcessData input) {
		if (!StringUtils.hasText(input.getHdsBusTelNumber() + input.getHdsCellNumber() + input.getHdsFaxNumber())) {
			return null;
		}
		
		List<TelecommunicationDto> output = new ArrayList<TelecommunicationDto>();
		
		if (StringUtils.hasText(input.getHdsBusTelNumber())) {
			TelecommunicationDto td = new TelecommunicationDto();
			td.setNumber(input.getHdsBusTelNumber());
			if (StringUtils.hasText(input.getHdsTelExtension())) {
				td.setExtension(input.getHdsTelExtension());
			}
			if (StringUtils.hasText(input.getHdsBusTelAreaCode())) {
				td.setAreaCode(input.getHdsBusTelAreaCode());
			}
			td.setCreatedDate(input.getCreatedAt());
			td.setEffectiveStartDate(input.getCreatedAt());
			output.add(td);
		}
		
		if (StringUtils.hasText(input.getHdsCellNumber())) {
			TelecommunicationDto td = new TelecommunicationDto();
			td.setNumber(input.getHdsCellNumber());
			if (StringUtils.hasText(input.getHdsCellAreaCode())) {
				td.setAreaCode(input.getHdsCellAreaCode());
			}
			td.setCreatedDate(input.getCreatedAt());
			td.setEffectiveStartDate(input.getCreatedAt());
			output.add(td);
		}
		
		if (StringUtils.hasText(input.getHdsFaxNumber())) {
			TelecommunicationDto td = new TelecommunicationDto();
			td.setNumber(input.getHdsFaxNumber());
			if (StringUtils.hasText(input.getHdsFaxAreaCode())) {
				td.setAreaCode(input.getHdsFaxAreaCode());
			}
			td.setCreatedDate(input.getCreatedAt());
			td.setEffectiveStartDate(input.getCreatedAt());
			output.add(td);
		}
		return output;
	}
}
