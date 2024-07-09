package com.moh.phlat.backend.esb.json;

import ca.bc.gov.health.plr.dto.esb.MaintainProviderRequest;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDto;
import ca.bc.gov.health.plr.dto.provider.esb.AddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.ElectronicAddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.HdsTypeDto;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.util.StringUtils;

public class MaintainHdsRequest implements PlrRequest {
	
	public MaintainHdsRequest(Control control) {
		
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
		emd.put("isLRA", false);
		return emd;
	}
	
	private ProviderDetails createProviderDetails(ProcessData input) {
		ProviderDetails pd = new ProviderDetails();
		pd.setProviderType(input.getHdsType());
		pd.setHdsType(createHdsTypeDto(input));
		pd.setAddresses(createAddressDtos(input));
		pd.setTelecommunication(createTelecomunicationDtos(input));
		pd.setElectronicAddresses(createElectronicAddressDtos(input));
		return pd;
	}
	
	private FacilityDto createFacilityDto(ProcessData input) {
		FacilityDto fd = new FacilityDto();
		return fd;
	}
	
	private HdsTypeDto createHdsTypeDto(ProcessData input) {
		HdsTypeDto htd = new HdsTypeDto();
		
		return htd;
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
	
	private List<ElectronicAddressDto> createElectronicAddressDtos(ProcessData input) {
		if (!StringUtils.hasText(input.getHdsEmail() + input.getHdsWebsite())) {
			return null;
		}
		
		List<ElectronicAddressDto> output = new ArrayList<ElectronicAddressDto>();
		SimpleDateFormat startDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		SimpleDateFormat endDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		if (StringUtils.hasText(input.getHdsEmail())) {
			ElectronicAddressDto ead = new ElectronicAddressDto();
			ead.setAddress(input.getHdsEmail());
			ead.setCreatedDate(input.getCreatedAt());
			try {
				ead.setEffectiveStartDate(startDateFormat.parse(input.getHdsEffectiveStartDate()));
			} catch (ParseException ex) {
				ead.setEffectiveStartDate(input.getCreatedAt());
			}
			try {
				ead.setEffectiveEndDate(endDateFormat.parse(input.getHdsEffectiveEndDate()));
			} catch (ParseException ex) {
			}
			output.add(ead);
		}
		
		if (StringUtils.hasText(input.getHdsWebsite())) {
			ElectronicAddressDto ead = new ElectronicAddressDto();
			ead.setAddress(input.getHdsWebsite());
			ead.setCreatedDate(input.getCreatedAt());
			try {
				ead.setEffectiveStartDate(startDateFormat.parse(input.getHdsEffectiveStartDate()));
			} catch (ParseException ex) {
				ead.setEffectiveStartDate(input.getCreatedAt());
			}
			try {
				ead.setEffectiveEndDate(endDateFormat.parse(input.getHdsEffectiveEndDate()));
			} catch (ParseException ex) {
			}
			output.add(ead);
		}
		
		return output;
	}
}
