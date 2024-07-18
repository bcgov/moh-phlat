package com.moh.phlat.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.ProcessDataService;
import com.moh.phlat.backend.service.TableColumnInfoService;
import com.moh.phlat.backend.service.dto.ColumnDisplayName;
import com.moh.phlat.backend.testsupport.factories.ControlTableFactory;
import com.moh.phlat.backend.testsupport.factories.ProcessDataFactory;
import com.moh.phlat.backend.testsupport.factories.TableColumnInfoFactory;
import com.moh.phlat.backend.testsupport.factories.UserRoles;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The role names are arbitrary. They are provided solely to meet the requirements of the Spring Security framework,
 * and it shouldn't be assumed that they are used for authorization checks
 */
@WebMvcTest(ProcessDataController.class)
public class ProcessDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessDataRepository processDataRepository;

    @InjectMocks
    private ProcessDataController processDataController;

    @MockBean
    private ControlRepository controlRepository;

    @MockBean
    private ProcessDataService processDataService;

    @MockBean
    private DbUtilityService dbUtilityService;

    @MockBean
    private TableColumnInfoService tableColumnInfoService;	

    List<ProcessData> processDataList = Collections.unmodifiableList(ProcessDataFactory
                                                                             .createProcessDataListWithAllAttributes());
    List<Control> controls = Collections.unmodifiableList(ControlTableFactory.createControlList());

    List<ColumnDisplayName> uiColumnNameList = Collections.unmodifiableList(TableColumnInfoFactory.getColumnDisplayNameList());
                                                                
    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetAllProcessData() throws Exception {

        when(processDataRepository.findAll()).thenReturn(processDataList);

        // Perform GET request and validate response
        ResultActions resultActions = mockMvc.perform(get("/processdata/view/all")
                                                              .with(csrf()).contentType(MediaType.APPLICATION_JSON))
                                             .andExpect(status().isOk())
                                             .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                                             //check basic stuff
                                             .andExpect(jsonPath("$.status").value("success"))
                                             .andExpect(jsonPath("$.message").isEmpty())
                                             .andExpect(jsonPath("$.data").isArray())
                                             .andExpect(jsonPath("$.data.length()").value(processDataList.size()));


        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data[0].createdAt").value("2024-02-01T00:00:00.000+00:00"));
        // assert the json elements of a single record
        checkProcessDataJsonResult(resultActions, "$.data[0].", processDataList.get(0));

        // Verify that the method processDataRepository.findAll() was called
        verify(processDataRepository, times(1)).findAll();
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetAllProcessDataByControlTableId() throws Exception {
    	
    	Pageable page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));
        when(processDataService.getProcessDataWithMessages(anyLong(),nullable(String.class),nullable(ProcessDataFilterParams.class),page)).thenReturn(processDataList);

        /*
        Generate an empty JSON to satisfy the request. Since the controller method doesn't process filterParams
        and the service method is mocked, we can pass an empty JSON. Otherwise, if the logic depends on the filterParams
        JSON, some values might need to be set.
         */
        String filterParamsJson = getFilterParamsJsonContent();

        ResultActions resultActions = mockMvc.perform(post("/processdata/controltable/1")
        		.param("rowStatus",  "VALID")
                .content(filterParamsJson)
                .with(csrf()).contentType(MediaType.APPLICATION_JSON));
        
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").isEmpty())
                     .andExpect(jsonPath("$.data").isArray())
                     .andExpect(jsonPath("$.data.length()").value(processDataList.size()));

        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data[0].createdAt").value("2024-02-01T00:00:00.000+00:00"));
        // assert the json elements of a single record, pick any of the two records.
        checkProcessDataJsonResult(resultActions, "$.data[0].", processDataList.get(0));

        //check if mocked methods were called
        verify(controlRepository, times(1)).findById(anyLong());
        verify(processDataService, times(1)).getProcessDataWithMessages(anyLong(),nullable(String.class),nullable(ProcessDataFilterParams.class),page);

    }

    private String getFilterParamsJsonContent() throws JsonProcessingException {

        ProcessDataFilterParams filterParams = new ProcessDataFilterParams();
        // Convert filterParams to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(filterParams);
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetProcessDataById() throws Exception {
        when(processDataRepository.findById(anyLong())).thenReturn(Optional.of(processDataList.get(0)));

        // Perform GET request and validate response
        ResultActions resultActions = mockMvc.perform(get("/processdata/view/1")
                                                              .with(csrf()).contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").isEmpty());


        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-02-01T00:00:00.000+00:00"));
        // assert the json elements of a single record, pick any of the two records.
        checkProcessDataJsonResult(resultActions, "$.data.", processDataList.get(0));

        //check if mocked methods were called
        verify(processDataRepository, times(2)).findById(anyLong());

    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void updateProcessDataById() throws Exception {
        when(processDataRepository.findById(anyLong())).thenReturn(Optional.of(processDataList.get(0)));
        when(processDataRepository.save(Mockito.any(ProcessData.class))).thenReturn(processDataList.get(0));

        ObjectMapper objectMapper = new ObjectMapper();

        // convert input to json.
        String jsonContent = objectMapper.writeValueAsString(processDataList.get(1));

        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/processdata/update/1")
                                                                            .with(csrf())
                                                                            .content(jsonContent)
                                                                            .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").value("Record updated sucessfully."))
                     .andExpect(jsonPath("$.data.updatedAt").isNotEmpty());


        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-02-01T00:00:00.000+00:00"));
        resultActions.andExpect(jsonPath("$.data.updatedAt").isNotEmpty());
        // assert the json elements of a single record, pick any of the two records.
        checkProcessDataJsonResult(resultActions, "$.data.", processDataList.get(0));

        //check if mocked methods were called
        verify(processDataRepository, times(1)).findById(anyLong());
        verify(processDataRepository, times(1)).save(Mockito.any(ProcessData.class));

    }

 
    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void getAllHeader() throws Exception {

        when(tableColumnInfoService.getColumnDisplayNames(anyString())).thenReturn(uiColumnNameList);

        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/processdata/column-display-names")
                                                                            .with(csrf())
                                                                            .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
        //check if mocked methods were called
        verify(tableColumnInfoService, times(1)).getColumnDisplayNames(anyString());

    }
 
    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testValidateProcessDataById() throws Exception {

        when(processDataRepository.findById(anyLong())).thenReturn(Optional.of(processDataList.get(0)));
        when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));
        doNothing().when(dbUtilityService).setControlStatus(anyLong(), anyString(), anyString());
        doNothing().when(dbUtilityService)
                   .validateProcessData(Mockito.any(Control.class), Mockito.any(ProcessData.class), anyString());


        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/processdata/validate/1")
                                                                            .with(csrf())
                                                                            .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").isEmpty());


        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-02-01T00:00:00.000+00:00"));

        // assert the json elements of a single record, pick any of the two records.
        checkControlJasonResponse(resultActions, "$.data.", controls.get(0));

        //check if mocked methods were called
        verify(processDataRepository, times(1)).findById(anyLong());
        verify(controlRepository, times(2)).findById(anyLong());
        verify(dbUtilityService, times(2)).setControlStatus(anyLong(), anyString(), anyString());
        verify(dbUtilityService, times(1)).validateProcessData(Mockito.any(Control.class),
                                                               Mockito.any(ProcessData.class), anyString());

    }

    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testValidateAllProcessData() throws Exception {

        when(processDataRepository.getAllProcessDataByControlTableId(anyLong())).thenReturn(processDataList);
        doNothing().when(dbUtilityService).setControlStatus(anyLong(), anyString(), anyString());
        doNothing().when(dbUtilityService).validateProcessDataByControlTableId(anyLong(), anyString());
        when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));

        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/processdata/validateallbycontroltableid/1")
                                      .with(csrf())
                                      .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").value("Validation process started!"));

        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-02-01T00:00:00.000+00:00"));

        // assert the json elements of a single record, pick any of the two records.
        checkControlJasonResponse(resultActions, "$.data.", controls.get(0));

        //check if mocked methods were called
        verify(processDataRepository, times(1)).getAllProcessDataByControlTableId(anyLong());
        verify(controlRepository, times(1)).findById(anyLong());
        verify(dbUtilityService, times(1)).setControlStatus(anyLong(), anyString(), anyString());
        verify(dbUtilityService, times(1)).validateProcessDataByControlTableId(anyLong(), anyString());
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testPlrLoad() throws Exception {

        when(processDataRepository.getAllProcessDataByControlTableId(anyLong())).thenReturn(processDataList);
        doNothing().when(dbUtilityService).setControlStatus(anyLong(), anyString(), anyString());
        doNothing().when(dbUtilityService).loadProcessDataToPlr(anyLong(), anyString());
        Control control = controls.get(0);
        //logic in the controller needs approved control
        control.setStatusCode("APPROVED");
        when(controlRepository.findById(anyLong())).thenReturn(Optional.of(control));

        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/processdata/plrload/1")
                                      .with(csrf())
                                      .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").value("PLR load process started!"));

        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-02-01T00:00:00.000+00:00"));

        // assert the json elements of a single record, pick any of the two records.
        checkControlJasonResponse(resultActions, "$.data.", controls.get(0));

        //check if mocked methods were called
        verify(processDataRepository, times(1)).getAllProcessDataByControlTableId(anyLong());
        verify(controlRepository, times(1)).findById(anyLong());
        verify(dbUtilityService, times(1)).setControlStatus(anyLong(), anyString(), anyString());
        verify(dbUtilityService, times(1)).loadProcessDataToPlr(anyLong(), anyString());
    }


    private void checkProcessDataJsonResult(ResultActions resultActions, String baseJasonPath,
                                            ProcessData processData) throws Exception {

        resultActions.andExpect(jsonPath(baseJasonPath + "id").value(processData.getId().intValue()))
                     .andExpect(jsonPath(baseJasonPath + "controlTableId").value(processData.getControlTableId()
                                                                                            .intValue()))
                     .andExpect(jsonPath(baseJasonPath + "doNotLoadFlag").value(processData.getDoNotLoadFlag()))
                     .andExpect(jsonPath(baseJasonPath + "stakeholder").value(processData.getStakeholder()))
                     .andExpect(jsonPath(baseJasonPath + "stakeholderId").value(processData.getStakeholderId()))
                     .andExpect(jsonPath(baseJasonPath + "hdsIpcId").value(processData.getHdsIpcId()))

                     .andExpect(jsonPath(baseJasonPath + "hdsCpnId").value(processData.getHdsCpnId()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifier1")
                                        .value(processData.getHdsProviderIdentifier1()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifier2")
                                        .value(processData.getHdsProviderIdentifier2()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifier3")
                                        .value(processData.getHdsProviderIdentifier3()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifierType1")
                                        .value(processData.getHdsProviderIdentifierType1()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifierType2")
                                        .value(processData.getHdsProviderIdentifierType2()))
                     .andExpect(jsonPath(baseJasonPath + "hdsProviderIdentifierType3")
                                        .value(processData.getHdsProviderIdentifierType3()))
                     .andExpect(jsonPath(baseJasonPath + "hdsMspFacilityNumber").value(processData.getHdsMspFacilityNumber()))
                     .andExpect(jsonPath(baseJasonPath + "hdsPauthId").value(processData.getHdsPauthId()))
                     .andExpect(jsonPath(baseJasonPath + "hdsCategoryCode").value(processData.getHdsCategoryCode()))
                     .andExpect(jsonPath(baseJasonPath + "hdsRoleTypeCode").value(processData.getHdsRoleTypeCode()))
                     .andExpect(jsonPath(baseJasonPath + "hdsType").value(processData.getHdsType()))
                     .andExpect(jsonPath(baseJasonPath + "hdsSubType").value(processData.getHdsSubType()))
                     .andExpect(jsonPath(baseJasonPath + "hdsUserChid").value(processData.getHdsUserChid()))
                     .andExpect(jsonPath(baseJasonPath + "hdsCreatedDts").value(processData.getHdsCreatedDts()))
                     .andExpect(jsonPath(baseJasonPath + "hdsInvalidatedDts").value(processData.getHdsInvalidatedDts()))
                     .andExpect(jsonPath(baseJasonPath + "hdsName").value(processData.getHdsName()))
                     .andExpect(jsonPath(baseJasonPath + "hdsPreferredNameFlag")
                                        .value(processData.getHdsPreferredNameFlag()))
                     .andExpect(jsonPath(baseJasonPath + "hdsEmail").value(processData.getHdsEmail()))
                     .andExpect(jsonPath(baseJasonPath + "hdsWebsite").value(processData.getHdsWebsite()))
                     .andExpect(jsonPath(baseJasonPath + "hdsBusTelAreaCode").value(processData.getHdsBusTelAreaCode()))
                     .andExpect(jsonPath(baseJasonPath + "hdsBusTelNumber").value(processData.getHdsBusTelNumber()))
                     .andExpect(jsonPath(baseJasonPath + "hdsTelExtension").value(processData.getHdsTelExtension()))
                     .andExpect(jsonPath(baseJasonPath + "hdsCellAreaCode").value(processData.getHdsCellAreaCode()))
                     .andExpect(jsonPath(baseJasonPath + "hdsCellNumber").value(processData.getHdsCellNumber()))
                     .andExpect(jsonPath(baseJasonPath + "hdsFaxAreaCode").value(processData.getHdsFaxAreaCode()))
                     .andExpect(jsonPath(baseJasonPath + "hdsFaxNumber").value(processData.getHdsFaxNumber()))
                     .andExpect(jsonPath(baseJasonPath + "pcnServiceDeliveryType").value(
                             processData.getPcnServiceDeliveryType()))
                     .andExpect(jsonPath(baseJasonPath + "pcnClinicType").value(processData.getPcnClinicType()))
                     .andExpect(jsonPath(baseJasonPath + "pcnPciFlag").value(processData.getPcnPciFlag()))
                     .andExpect(jsonPath(baseJasonPath + "sourceStatus").value(processData.getSourceStatus()))
                     .andExpect(jsonPath(baseJasonPath + "pcnClinicStatus").value(processData.getPcnClinicStatus()))
                     .andExpect(jsonPath(baseJasonPath + "hdsEffectiveStartDate").value(processData.getHdsEffectiveStartDate()))
                     .andExpect(jsonPath(baseJasonPath + "hdsEffectiveEndDate").value(processData.getHdsEffectiveEndDate()))
                     .andExpect(jsonPath(baseJasonPath + "facAddressUnit").value(processData.getFacAddressUnit()))
                     .andExpect(jsonPath(baseJasonPath + "facBuildingName").value(processData.getFacBuildingName()))
                     .andExpect(jsonPath(baseJasonPath + "facCivicAddrId").value(processData.getFacCivicAddrId()))
                     .andExpect(jsonPath(baseJasonPath + "facCivicAddr").value(processData.getFacCivicAddr()))
                     .andExpect(jsonPath(baseJasonPath + "facLatitude").value(processData.getFacLatitude()))
                     .andExpect(jsonPath(baseJasonPath + "facLongitude").value(processData.getFacLongitude()))
                     .andExpect(jsonPath(baseJasonPath + "facStreetDirection").value(processData.getFacStreetDirection()))
                     .andExpect(jsonPath(baseJasonPath + "streetDirectionPrefix").value(processData.getStreetDirectionPrefix()))
                     .andExpect(jsonPath(baseJasonPath + "streetTypePrefix").value(processData.getStreetTypePrefix()))
                     .andExpect(jsonPath(baseJasonPath + "facCivicNumber").value(processData.getFacCivicNumber()))
                     .andExpect(jsonPath(baseJasonPath + "facStreetName").value(processData.getFacStreetName()))
                     .andExpect(jsonPath(baseJasonPath + "facStreetType").value(processData.getFacStreetType()))
                     .andExpect(jsonPath(baseJasonPath + "facLocalityName").value(processData.getFacLocalityName()))
                     .andExpect(jsonPath(baseJasonPath + "facProvinceCode").value(processData.getFacProvinceCode()))
                     .andExpect(jsonPath(baseJasonPath + "facSiteId").value(processData.getFacSiteId()))
                     .andExpect(jsonPath(baseJasonPath + "facScore").value(processData.getFacScore()))
                     .andExpect(jsonPath(baseJasonPath + "facMatchPrecision").value(processData.getFacMatchPrecision()))
                     .andExpect(jsonPath(baseJasonPath + "facHsdaName").value(processData.getFacHsdaName()))
                     .andExpect(jsonPath(baseJasonPath + "facDatabcResults").value(processData.getFacDatabcResults()))
                     .andExpect(jsonPath(baseJasonPath + "facPcnCode").value(processData.getFacPcnCode()))                     
                     .andExpect(jsonPath(baseJasonPath + "facPcnName").value(processData.getFacPcnName()))   
                     .andExpect(jsonPath(baseJasonPath + "facChsaStatus").value(processData.getFacChsaStatus()))
                     .andExpect(jsonPath(baseJasonPath + "facPcnStatus").value(processData.getFacPcnStatus()))
                     .andExpect(jsonPath(baseJasonPath + "facChsaCode").value(processData.getFacChsaCode()))
                     .andExpect(jsonPath(baseJasonPath + "facChsaName").value(processData.getFacChsaName()))
                     .andExpect(jsonPath(baseJasonPath + "facLhaName").value(processData.getFacLhaName()))
                     .andExpect(jsonPath(baseJasonPath + "facRelnType").value(processData.getFacRelnType()))
                     .andExpect(jsonPath(baseJasonPath + "facTypeCode").value(processData.getFacTypeCode()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr1").value(processData.getPhysicalAddr1()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr2").value(processData.getPhysicalAddr2()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr3").value(processData.getPhysicalAddr3()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr4").value(processData.getPhysicalAddr4()))
                     .andExpect(jsonPath(baseJasonPath + "physicalCity").value(processData.getPhysicalCity()))
                     .andExpect(jsonPath(baseJasonPath + "physicalProvince").value(processData.getPhysicalProvince()))
                     .andExpect(jsonPath(baseJasonPath + "physicalPcode").value(processData.getPhysicalPcode()))
                     .andExpect(jsonPath(baseJasonPath + "physicalCountry").value(processData.getPhysicalCountry()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddrPrpsTypeCd").value(processData.getPhysicalAddrPrpsTypeCd()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddrValidationStatus").value(processData.getPhysicalAddrValidationStatus()))                    
                      .andExpect(jsonPath(baseJasonPath + "mailAddr1").value(processData.getMailAddr1()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr2").value(processData.getMailAddr2()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr3").value(processData.getMailAddr3()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr4").value(processData.getMailAddr4()))
                     .andExpect(jsonPath(baseJasonPath + "mailCity").value(processData.getMailCity()))
                     .andExpect(jsonPath(baseJasonPath + "mailBc").value(processData.getMailBc()))
                     .andExpect(jsonPath(baseJasonPath + "mailPcode").value(processData.getMailPcode()))
                     .andExpect(jsonPath(baseJasonPath + "mailCountry").value(processData.getMailCountry()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddrPrpsTypeCd").value(processData.getMailAddrPrpsTypeCd()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddrValidationStatus").value(processData.getMailAddrValidationStatus()))                    
                     .andExpect(jsonPath(baseJasonPath + "plrFacilityId").value(processData.getPlrFacilityId()))
                     .andExpect(jsonPath(baseJasonPath + "rowstatusCode").value(processData.getRowstatusCode()))
                     .andExpect(jsonPath(baseJasonPath + "createdBy").value(processData.getCreatedBy()))
                     .andExpect(jsonPath(baseJasonPath + "updatedBy").value(processData.getUpdatedBy()));
    }


    private void checkControlJasonResponse(ResultActions resultActions, String baseJasonPath,
                                           Control control) throws Exception {
        //checking main elements
        resultActions.andExpect(jsonPath(baseJasonPath + "id").value(control.getId()))
                     //.andExpect(jsonPath(baseJasonPath + "fileExtractedDate").value("2024-01-01T00:00:00.000+00:00"))
                     .andExpect(jsonPath(baseJasonPath + "userId").value(control.getUserId()))
                     .andExpect(jsonPath(baseJasonPath + "fileName").value(control.getFileName()))
                     .andExpect(jsonPath(baseJasonPath + "batchLabelName").value(control.getBatchLabelName()))
                     .andExpect(jsonPath(baseJasonPath + "loadTypeFacility").value(control.getLoadTypeFacility()))
                     .andExpect(jsonPath(baseJasonPath + "loadTypeHds").value(control.getLoadTypeHds()))
                     .andExpect(jsonPath(baseJasonPath + "loadTypeOrg").value(control.getLoadTypeOrg()))
                     .andExpect(jsonPath(baseJasonPath + "createdBy").value(control.getCreatedBy()));


    }

}




