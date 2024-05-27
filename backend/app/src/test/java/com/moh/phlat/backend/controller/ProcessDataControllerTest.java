package com.moh.phlat.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.ProcessDataService;
import com.moh.phlat.backend.testsupport.factories.ControlTableFactory;
import com.moh.phlat.backend.testsupport.factories.ProcessDataFactory;
import com.moh.phlat.backend.testsupport.factories.UserRoles;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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


    List<ProcessData> processDataList = Collections.unmodifiableList(ProcessDataFactory
                                                                             .createProcessDataListWithAllAttributes());
    List<Control> controls = Collections.unmodifiableList(ControlTableFactory.createControlList());


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
        resultActions.andExpect(jsonPath("$.data[0].createdAt").value("2024-01-01T00:00:00.000+00:00"));
        // assert the json elements of a single record
        checkProcessDataJsonResult(resultActions, "$.data[0].", processDataList.get(0));

        // Verify that the method processDataRepository.findAll() was called
        verify(processDataRepository, times(1)).findAll();
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetAllProcessDataByControlTableId() throws Exception {

        when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));
        when(processDataService.getProcessDataWithMessages(anyLong(),nullable(String.class))).thenReturn(processDataList);

        // Perform GET request and validate response
        ResultActions resultActions = mockMvc.perform(get("/processdata/controltable/1")
                                                              .with(csrf()).contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     //check basic stuff
                     .andExpect(jsonPath("$.status").value("success"))
                     .andExpect(jsonPath("$.message").isEmpty())
                     .andExpect(jsonPath("$.data").isArray())
                     .andExpect(jsonPath("$.data.length()").value(processDataList.size()));

        //checking date here and rest of the elements in method below.
        resultActions.andExpect(jsonPath("$.data[0].createdAt").value("2024-01-01T00:00:00.000+00:00"));
        // assert the json elements of a single record, pick any of the two records.
        checkProcessDataJsonResult(resultActions, "$.data[0].", processDataList.get(0));

        //check if mocked methods were called
        verify(controlRepository, times(1)).findById(anyLong());
        verify(processDataService, times(1)).getProcessDataWithMessages(anyLong(),nullable(String.class));

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
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-01-01T00:00:00.000+00:00"));
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
        resultActions.andExpect(jsonPath("$.data.createdAt").value("2024-01-01T00:00:00.000+00:00"));
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

        when(dbUtilityService.getVariablesByTableNameSortedById(anyString())).thenReturn("Test Headers");
        // Perform Put request and validate response
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/processdata/getformfields/header")
                                                                            .with(csrf())
                                                                            .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());

        //check if mocked methods were called
        verify(dbUtilityService, times(1)).getVariablesByTableNameSortedById(anyString());

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
 //                    .andExpect(jsonPath(baseJasonPath + "hdsHibcFacilityId").value(processData.getHdsHibcFacilityId()))
                     .andExpect(jsonPath(baseJasonPath + "hdsType").value(processData.getHdsType()))
                     .andExpect(jsonPath(baseJasonPath + "hdsName").value(processData.getHdsName()))
//                     .andExpect(jsonPath(baseJasonPath + "hdsNameAlias").value(processData.getHdsNameAlias()))
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
//                     .andExpect(jsonPath(baseJasonPath + "hdsServiceDeliveryType").value(
//                             processData.getHdsServiceDeliveryType()))
                     .andExpect(jsonPath(baseJasonPath + "pcnClinicType").value(processData.getPcnClinicType()))
                     .andExpect(jsonPath(baseJasonPath + "pcnPciFlag").value(processData.getPcnPciFlag()))
//                     .andExpect(jsonPath(baseJasonPath + "hdsHoursOfOperation").value(
//                             processData.getHdsHoursOfOperation()))
//                     .andExpect(jsonPath(baseJasonPath + "hdsContactName").value(processData.getHdsContactName()))
//                     .andExpect(
//                             jsonPath(baseJasonPath + "hdsIsForProfitFlag").value(processData.getHdsIsForProfitFlag()))
                     .andExpect(jsonPath(baseJasonPath + "sourceStatus").value(processData.getSourceStatus()))
//                     .andExpect(jsonPath(baseJasonPath + "hdsParentIpcId").value(processData.getHdsParentIpcId()))
//                     .andExpect(jsonPath(baseJasonPath + "busIpcId").value(processData.getBusIpcId()))
//                     .andExpect(jsonPath(baseJasonPath + "busCpnId").value(processData.getBusCpnId()))
//                     .andExpect(jsonPath(baseJasonPath + "busName").value(processData.getBusName()))
//                     .andExpect(jsonPath(baseJasonPath + "busLegalName").value(processData.getBusLegalName()))
//                     .andExpect(jsonPath(baseJasonPath + "busPayeeNumber").value(processData.getBusPayeeNumber()))
//                     .andExpect(jsonPath(baseJasonPath + "busOwnerName").value(processData.getBusOwnerName()))
//                     .andExpect(jsonPath(baseJasonPath + "busOwnerType").value(processData.getBusOwnerType()))
//                     .andExpect(jsonPath(baseJasonPath + "busOwnerTypeOther").value(processData.getBusOwnerTypeOther()))
                     .andExpect(jsonPath(baseJasonPath + "facBuildingName").value(processData.getFacBuildingName()))
//                     .andExpect(jsonPath(baseJasonPath + "facilityHdsDetailsAdditionalInfo").value(
//                             processData.getFacilityHdsDetailsAdditionalInfo()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr1").value(processData.getPhysicalAddr1()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr2").value(processData.getPhysicalAddr2()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr3").value(processData.getPhysicalAddr3()))
                     .andExpect(jsonPath(baseJasonPath + "physicalAddr4").value(processData.getPhysicalAddr4()))
                     .andExpect(jsonPath(baseJasonPath + "physicalCity").value(processData.getPhysicalCity()))
                     .andExpect(jsonPath(baseJasonPath + "physicalProvince").value(processData.getPhysicalProvince()))
                     .andExpect(jsonPath(baseJasonPath + "physicalPcode").value(processData.getPhysicalPcode()))
                     .andExpect(jsonPath(baseJasonPath + "physicalCountry").value(processData.getPhysicalCountry()))
//                     .andExpect(jsonPath(baseJasonPath + "physAddrIsPrivate").value(processData.getPhysAddrIsPrivate()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr1").value(processData.getMailAddr1()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr2").value(processData.getMailAddr2()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr3").value(processData.getMailAddr3()))
                     .andExpect(jsonPath(baseJasonPath + "mailAddr4").value(processData.getMailAddr4()))
                     .andExpect(jsonPath(baseJasonPath + "mailCity").value(processData.getMailCity()))
                     .andExpect(jsonPath(baseJasonPath + "mailBc").value(processData.getMailBc()))
                     .andExpect(jsonPath(baseJasonPath + "mailPcode").value(processData.getMailPcode()))
                     .andExpect(jsonPath(baseJasonPath + "mailCountry").value(processData.getMailCountry()))
//                     .andExpect(jsonPath(baseJasonPath + "mailAddrIsPrivate").value(processData.getMailAddrIsPrivate()))
//                     .andExpect(jsonPath(baseJasonPath + "facility_id").value(processData.getFacility_id().intValue()))
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




