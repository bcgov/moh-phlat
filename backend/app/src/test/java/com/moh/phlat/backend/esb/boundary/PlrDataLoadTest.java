package com.moh.phlat.backend.esb.boundary;

import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * @author CGI
 */
class PlrDataLoadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCreateFacility() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PlrDataLoad underTest = new PlrDataLoad();
        PlrEsbBoundary mockEsbBoundary = mock(PlrEsbBoundary.class);
        Control control = createControlData();
        ProcessData processData = createProcessData();

        FieldUtils.writeField(underTest, "plrEsbBoundary", mockEsbBoundary, true);
        doNothing().when(mockEsbBoundary).maintainProvider(Mockito.any(), Mockito.any(), Mockito.any());

        Method m = PlrDataLoad.class
                .getDeclaredMethod("createFacility", Control.class, ProcessData.class);
        m.setAccessible(true);
        MaintainFacilityResponse facilityResponse = (MaintainFacilityResponse) m.invoke(underTest, control, processData);
        assertNotNull(facilityResponse);
    }

    private Control createControlData(){
        Control control = new Control();
        control.setId(5L);
        control.setFileName("Test Facility");
        control.setUserId("XXX@idir");
        control.setBatchLabelName("Test Facility");
        control.setLoadTypeFacility(true);
        control.setLoadTypeHds(true);
        control.setLoadTypeOrg(false);
        control.setLoadTypeOFRelationship(false);
        control.setLoadTypeOORelationship(false);
        control.setLoadTypeIORelationship(false);
        control.setStatusCode("PLR_LOAD_IN_PROGRESS");
        control.setCreatedBy("XXX@idir");
        control.setUpdatedBy("XXX@idir");
        return control;
    }

    private ProcessData createProcessData(){
        ProcessData processData = new ProcessData();
        processData.setId(1664L);
        processData.setControlTableId(5L);
        processData.setStakeholder("HOSPITAL");
        processData.setHdsType("HOSPITAL");
        processData.setHdsName("Northern Haida Gwaii Hospital and Health Centre");
        processData.setFacRelnType("LOCATION OF");
        processData.setFacTypeCode("BUILDING");
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

        return processData;
    }
}