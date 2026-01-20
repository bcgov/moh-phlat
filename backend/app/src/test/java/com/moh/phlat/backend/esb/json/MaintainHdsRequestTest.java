package com.moh.phlat.backend.esb.json;

import ca.bc.gov.health.plr.dto.esb.MaintainProviderRequest;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

import static com.moh.phlat.backend.databc.util.Constants.CEASE;
import static com.moh.phlat.backend.databc.util.Constants.ZERO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author CGI
 */
class MaintainHdsRequestTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCreateHDSAddUnitNumber() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException, ParseException {
        Control control = createControlData();
        control.setLoadTypeHds(true);
        ProcessData processData = createProcessData();
        processData.setPlrFacilityId("22222222");
        processData.setFacIfcId("IFC.AAAAA.BC.PRS");
        processData.setFacCivicAddr("110-12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("110-12680 HARRISON AVE");
        processData.setRecordAction("ADD");
        // populate facility unit number which is fetched from AD in DbUtilityService.validateProcessData(control, processData, "userID");
        processData.setFacAddressUnit("110");

        MaintainHdsRequest underTest = new MaintainHdsRequest(processData, false);

        Method m = MaintainHdsRequest.class.getDeclaredMethod("createMaintainProviderRequest");
        m.setAccessible(true);
        MaintainProviderRequest maintainProviderRequest = (MaintainProviderRequest) m.invoke(underTest);
        assertNotNull(maintainProviderRequest);
        ProviderDetails providerDetails = maintainProviderRequest.getProviderDetails();
        assertNotNull(providerDetails);
        assertEquals("P", providerDetails.getAddresses().get(0).getTypeCode());
        assertEquals(processData.getPhysicalAddr1(), providerDetails.getAddresses().get(0).getAddressLineOne());
        assertEquals(processData.getFacAddressUnit(), providerDetails.getProperties().get(0).getPropertyValue());
        assertEquals("ADD", processData.getRecordAction());
        assertEquals(processData.getFacAddressUnitPropertyChid(), providerDetails.getProperties().get(0).getPropertyChid());
    }

    @Test
    public void testCreateHDS_noUnitNumber() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException, ParseException {
        Control control = createControlData();
        control.setLoadTypeHds(true);
        ProcessData processData = createProcessData();
        processData.setPlrFacilityId("22222222");
        processData.setFacIfcId("IFC.AAAAA.BC.PRS");
        processData.setFacCivicAddr("12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("12680 HARRISON AVE");
        processData.setRecordAction("ADD");
        // populate facility unit number which is fetched from AD in DbUtilityService.validateProcessData(control, processData, "userID");
        processData.setFacAddressUnit(null);

        MaintainHdsRequest underTest = new MaintainHdsRequest(processData, false);

        Method m = MaintainHdsRequest.class.getDeclaredMethod("createMaintainProviderRequest");
        m.setAccessible(true);
        MaintainProviderRequest maintainProviderRequest = (MaintainProviderRequest) m.invoke(underTest);
        assertNotNull(maintainProviderRequest);
        ProviderDetails providerDetails = maintainProviderRequest.getProviderDetails();
        assertNotNull(providerDetails);
        assertEquals(processData.getPhysicalAddr1(), providerDetails.getAddresses().get(0).getAddressLineOne());
        assertEquals("P", providerDetails.getAddresses().get(0).getTypeCode());

        assertNull(providerDetails.getProperties());
        assertEquals("ADD", processData.getRecordAction());
    }

    @Test
    public void testUpdateHDSProcessDataUpdateUnitNumber() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException, ParseException {
        Control control = createControlData();
        control.setLoadTypeHds(true);
        ProcessData processData = createProcessData();
        processData.setPlrFacilityId("22222222");
        processData.setFacIfcId("IFC.AAAAA.BC.PRS");
        processData.setFacCivicAddr("110-12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("110-12680 HARRISON AVE");
        processData.setRecordAction("ADD");
        // populate facility unit number which is fetched from AD in DbUtilityService.validateProcessData(control, processData, "userID");
        processData.setFacAddressUnit("110");

        MaintainHdsRequest underTest = new MaintainHdsRequest(processData, false);

        Method m = MaintainHdsRequest.class.getDeclaredMethod("createMaintainProviderRequest");
        m.setAccessible(true);
        MaintainProviderRequest maintainProviderRequest = (MaintainProviderRequest) m.invoke(underTest);
        assertNotNull(maintainProviderRequest);
        ProviderDetails providerDetails = maintainProviderRequest.getProviderDetails();
        assertNotNull(providerDetails);
        assertEquals("P", providerDetails.getAddresses().get(0).getTypeCode());
        assertEquals(processData.getPhysicalAddr1(), providerDetails.getAddresses().get(0).getAddressLineOne());
        assertEquals(processData.getFacAddressUnit(), providerDetails.getProperties().get(0).getPropertyValue());
        assertEquals("ADD", processData.getRecordAction());
        assertEquals(processData.getFacAddressUnitPropertyChid(), providerDetails.getProperties().get(0).getPropertyChid());

        //Update
        Control controlUpdate = createControlData();
        controlUpdate.setLoadTypeHds(true);
        controlUpdate.setId(6L);
        controlUpdate.setFileName("Test HDS");
        controlUpdate.setUserId("XXX@idir");
        controlUpdate.setBatchLabelName("Test HDS");

        ProcessData processDataUpdate = updateProcessDataRemoveUnitNumber();
        processDataUpdate.setPhysicalAddr1("220-12680 HARRISON AVE");
        processDataUpdate.setFacAddressUnit("220");

        MaintainHdsRequest underTestUpdate = new MaintainHdsRequest(processDataUpdate, true);
        MaintainProviderRequest maintainProviderRequestUpdate = (MaintainProviderRequest) m.invoke(underTestUpdate);
        ProviderDetails providerDetailsUpdate = maintainProviderRequestUpdate.getProviderDetails();

        assertNotNull(providerDetailsUpdate);
        assertEquals("P", providerDetailsUpdate.getAddresses().get(0).getTypeCode());
        assertEquals(processDataUpdate.getPhysicalAddr1(), providerDetailsUpdate.getAddresses().get(0).getAddressLineOne());
        assertEquals(processDataUpdate.getFacAddressUnit(), providerDetailsUpdate.getProperties().get(0).getPropertyValue());
        assertEquals("CHG", processDataUpdate.getRecordAction());
        assertEquals("CHG", processDataUpdate.getPhysicalAddressGroupAction());
        assertEquals(processDataUpdate.getPhysicalAddressGroupAction(), providerDetailsUpdate.getProperties().get(0).getEndReasonCode());
        assertEquals(processDataUpdate.getFacAddressUnitPropertyChid(), providerDetailsUpdate.getProperties().get(0).getPropertyChid());
    }

    @Test
    public void testUpdateHDSProcessDataRemoveUnitNumber() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException, ParseException {
        Control control = createControlData();
        control.setLoadTypeHds(true);
        ProcessData processData = createProcessData();
        processData.setPlrFacilityId("22222222");
        processData.setFacIfcId("IFC.AAAAA.BC.PRS");
        processData.setFacCivicAddr("110-12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("110-12680 HARRISON AVE");
        processData.setRecordAction("ADD");
        // populate facility unit number which is fetched from AD in DbUtilityService.validateProcessData(control, processData, "userID");
        processData.setFacAddressUnit("110");

        MaintainHdsRequest underTest = new MaintainHdsRequest(processData, false);

        Method m = MaintainHdsRequest.class.getDeclaredMethod("createMaintainProviderRequest");
        m.setAccessible(true);
        MaintainProviderRequest maintainProviderRequest = (MaintainProviderRequest) m.invoke(underTest);
        assertNotNull(maintainProviderRequest);
        ProviderDetails providerDetails = maintainProviderRequest.getProviderDetails();
        assertNotNull(providerDetails);
        assertEquals("P", providerDetails.getAddresses().get(0).getTypeCode());
        assertEquals(processData.getPhysicalAddr1(), providerDetails.getAddresses().get(0).getAddressLineOne());
        assertEquals(processData.getFacAddressUnit(), providerDetails.getProperties().get(0).getPropertyValue());
        assertEquals("ADD", processData.getRecordAction());
        assertEquals(processData.getFacAddressUnitPropertyChid(), providerDetails.getProperties().get(0).getPropertyChid());

        //Update
        Control controlUpdate = createControlData();
        controlUpdate.setLoadTypeHds(true);
        controlUpdate.setId(6L);
        controlUpdate.setFileName("Test HDS");
        controlUpdate.setUserId("XXX@idir");
        controlUpdate.setBatchLabelName("Test HDS");

        ProcessData processDataUpdate = updateProcessDataRemoveUnitNumber();
        MaintainHdsRequest underTestUpdate = new MaintainHdsRequest(processDataUpdate, true);
        MaintainProviderRequest maintainProviderRequestUpdate = (MaintainProviderRequest) m.invoke(underTestUpdate);
        ProviderDetails providerDetailsUpdate = maintainProviderRequestUpdate.getProviderDetails();

        assertNotNull(providerDetailsUpdate);
        assertEquals("P", providerDetailsUpdate.getAddresses().get(0).getTypeCode());
        assertEquals(processDataUpdate.getPhysicalAddr1(), providerDetailsUpdate.getAddresses().get(0).getAddressLineOne());
        assertEquals(ZERO, providerDetailsUpdate.getProperties().get(0).getPropertyValue());
        assertEquals("CHG", processDataUpdate.getRecordAction());
        assertEquals("CHG", processDataUpdate.getPhysicalAddressGroupAction());
        assertEquals(CEASE, providerDetailsUpdate.getProperties().get(0).getEndReasonCode());
        assertEquals(processDataUpdate.getFacAddressUnitPropertyChid(), providerDetailsUpdate.getProperties().get(0).getPropertyChid());
    }

    private Control createControlData() {
        Control control = new Control();
        control.setId(5L);
        control.setFileName("Test Facility");
        control.setUserId("XXX@idir");
        control.setBatchLabelName("Test Facility");
        control.setLoadTypeFacility(false);
        control.setLoadTypeHds(false);
        control.setLoadTypeOrg(false);
        control.setLoadTypeOFRelationship(false);
        control.setLoadTypeOORelationship(false);
        control.setLoadTypeIORelationship(false);
        control.setStatusCode("PLR_LOAD_IN_PROGRESS");
        control.setCreatedBy("XXX@idir");
        control.setUpdatedBy("XXX@idir");
        return control;
    }

    private ProcessData createProcessData() {
        ProcessData processData = new ProcessData();
        processData.setId(1664L);
        processData.setControlTableId(5L);
        processData.setStakeholder("CP");
        processData.setHdsCategoryCode("ORGANIZATION");
        processData.setHdsType("PHARMACY");
        processData.setHdsName("Northern Haida Gwaii Hospital and Health Centre");
        processData.setHdsProviderIdentifier1("12345");
        processData.setHdsProviderIdentifierType1("PHYID");
        processData.setHdsBusTelAreaCode("999");
        processData.setHdsBusTelNumber("9999");
        processData.setHdsTelExtension("999");
        processData.setHdsCellAreaCode("999");
        processData.setHdsCellNumber("9999");
        processData.setHdsFaxAreaCode("999");
        processData.setHdsFaxNumber("9999");
        processData.setHdsEmail("XXX@email.com");
        processData.setHdsWebsite("WEBSITE");
        processData.setFacRelnType("LOCATION OF");
        processData.setFacTypeCode("BUILDING");
        processData.setFacCivicAddr("12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("12680 HARRISON AVE");
        processData.setPhysicalCity("RICHMOND");
        processData.setPhysicalProvince("BC");
        processData.setPhysicalPcode("V6V 2R7");
        processData.setPhysicalCountry("CANADA");
        processData.setMailAddr1("12680 HARRISON AVE");
        processData.setMailAddr2("PO BOX 319");
        processData.setMailCity("RICHMOND");
        processData.setMailPcode("V6V 2R7");
        processData.setMailCountry("CANADA");
        processData.setRowstatusCode("VALID");
        processData.setCreatedBy("XXX@idir");
        processData.setUpdatedBy("XXX@idir");
        processData.setHdsEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsNameGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setPhysicalAddressGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setStatusGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setMailingAddressGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setBusinessPhoneGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsCellGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsEmailGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsWebsiteGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsFaxGroupEffectiveStartDate("2025-11-13 15:06:55");
        return processData;
    }

    private ProcessData updateProcessDataRemoveUnitNumber() {
        ProcessData processData = new ProcessData();
        processData.setId(1664L);
        processData.setControlTableId(5L);
        processData.setStakeholder("CP");
        processData.setHdsCategoryCode("ORGANIZATION");
        processData.setHdsType("PHARMACY");
        processData.setHdsName("Northern Haida Gwaii Hospital and Health Centre");
        processData.setHdsProviderIdentifier1("12345");
        processData.setHdsProviderIdentifierType1("PHYID");
        processData.setHdsBusTelAreaCode("999");
        processData.setHdsBusTelNumber("9999");
        processData.setHdsTelExtension("999");
        processData.setHdsCellAreaCode("999");
        processData.setHdsCellNumber("9999");
        processData.setHdsFaxAreaCode("999");
        processData.setHdsFaxNumber("9999");
        processData.setHdsEmail("XXX@email.com");
        processData.setHdsWebsite("WEBSITE");
        processData.setFacRelnType("LOCATION OF");
        processData.setFacTypeCode("BUILDING");
        processData.setFacCivicAddr("12680 HARRISON AVE, RICHMOND, BC, V6V 2R7");
        processData.setPhysicalAddr1("12680 HARRISON AVE");
        processData.setPhysicalCity("RICHMOND");
        processData.setPhysicalProvince("BC");
        processData.setPhysicalPcode("V6V 2R7");
        processData.setPhysicalCountry("CANADA");
        processData.setMailAddr1("12680 HARRISON AVE");
        processData.setMailAddr2("PO BOX 319");
        processData.setMailCity("RICHMOND");
        processData.setMailPcode("V6V 2R7");
        processData.setMailCountry("CANADA");
        processData.setRowstatusCode("VALID");
        processData.setCreatedBy("XXX@idir");
        processData.setUpdatedBy("XXX@idir");
        processData.setHdsEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsNameGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setPhysicalAddressGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setStatusGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setMailingAddressGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setBusinessPhoneGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsCellGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsEmailGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsWebsiteGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setHdsFaxGroupEffectiveStartDate("2025-11-13 15:06:55");
        processData.setPlrFacilityId("22222222");
        processData.setFacIfcId("IFC.AAAAA.BC.PRS");
        processData.setRecordAction("CHG");
        processData.setFacAddressUnit(null);
        processData.setFacAddressUnitPropertyChid("PRPTY.111.PRS");
        processData.setPhysicalAddressGroupAction("CHG");
        return processData;
    }

}