package com.moh.phlat.backend.addressdoctor.service;

import com.moh.phlat.backend.addressdoctor.soap.*;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.MessageSourceSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author CGI
 */
@ExtendWith(MockitoExtension.class)
class AddressDoctorValidationTest {

    @InjectMocks
    private AddressDoctorValidation underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidatePhysicalAddressWithAddrLine2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SOAPEnvelopeOutput sOAPEnvelopeOutput = createSOAPEnvelopeOutput();
        ProcessData processData = new ProcessData();
        processData.setPhysicalAddr1("601 WEST BROADWAY");
        processData.setPhysicalAddr2("11TH FL");
        processData.setPhysicalAddr3("");
        processData.setPhysicalAddr4("");
        processData.setPhysicalCity("VANCOUVER");
        processData.setPhysicalProvince("BC");
        processData.setPhysicalCountry("CANADA");
        processData.setPhysicalPcode("V5Z 4C2");

        Method method = AddressDoctorValidation.class.getDeclaredMethod("processPhysicalAddressResult", SOAPEnvelopeOutput.class, ProcessData.class);
        method.setAccessible(true);
        method.invoke(underTest, sOAPEnvelopeOutput, processData);
        assertNotNull(processData);
        assertEquals("601 WEST BROADWAY 11TH FL", processData.getPhysicalAddr1());
        assertEquals("", processData.getPhysicalAddr2());
        assertEquals("", processData.getPhysicalAddr3());
        assertEquals("", processData.getPhysicalAddr4());
        assertEquals("VANCOUVER", processData.getPhysicalCity());
        assertEquals("CANADA", processData.getPhysicalCountry());
        assertEquals("BC", processData.getPhysicalProvince());
        assertEquals("V5Z 4C2", processData.getPhysicalPcode());
    }

    @Test
    public void testValidatePhysicalAddressWithEmptyADAddressLines() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SOAPEnvelopeOutput sOAPEnvelopeOutput = createSOAPEnvelopeEmptyOutput();
        ProcessData processData = new ProcessData();
        processData.setPhysicalAddr1("601 WEST BROADWAY");
        processData.setPhysicalAddr2("11TH FL");
        processData.setPhysicalAddr3("");
        processData.setPhysicalAddr4("");
        processData.setPhysicalCity("VANCOUVER");
        processData.setPhysicalProvince("BC");
        processData.setPhysicalCountry("CANADA");
        processData.setPhysicalPcode("V5Z 4C2");
        processData.setMessages(new ArrayList<>());

        Method method = AddressDoctorValidation.class.getDeclaredMethod("processPhysicalAddressResult", SOAPEnvelopeOutput.class, ProcessData.class);
        method.setAccessible(true);
        method.invoke(underTest, sOAPEnvelopeOutput, processData);
        assertNotNull(processData);
        assertEquals(1, processData.getMessages().size());
        assertEquals("ERROR", processData.getMessages().get(0).getMessageType());
        assertEquals("100", processData.getMessages().get(0).getMessageCode());
        assertEquals("Physical address line 1 is mandatory.", processData.getMessages().get(0).getMessageDesc());
    }

    private SOAPEnvelopeOutput createSOAPEnvelopeOutput() {
        SOAPEnvelopeOutput sOAPEnvelopeOutput = new SOAPEnvelopeOutput();
        SOAPBodyOutput sOAPBodyOutput = new SOAPBodyOutput();
        ProcessResponse processResponse = new ProcessResponse();
        Response response = new Response();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        List<ResultData> resultsData = new ArrayList<>();
        ResultData resultData = new ResultData();
        Address address = new Address();
        List<String> deliveryAddressLines = new ArrayList<>();
        List<String> country = new ArrayList<>();
        List<String> locality = new ArrayList<>();
        List<String> province = new ArrayList<>();
        List<String> houseNumber = new ArrayList<>();
        List<String> postalCode = new ArrayList<>();
        List<String> subBuilding = new ArrayList<>();
        List<String> street = new ArrayList<>();
        ArrayOfString deliveryAddressLinesArr = new ArrayOfString();
        ArrayOfString postalCodeArr = new ArrayOfString();
        ArrayOfString countryArr = new ArrayOfString();
        ArrayOfString localityArr = new ArrayOfString();
        ArrayOfString provinceArr = new ArrayOfString();
        ArrayOfString houseNumberArr = new ArrayOfString();
        ArrayOfString subBuildingArr = new ArrayOfString();
        ArrayOfString streetArr = new ArrayOfString();
        ArrayOfResult resultsArr = new ArrayOfResult();
        ArrayOfResultData resultsDataArr = new ArrayOfResultData();

        response.setStatusCode(100);
        response.setStatusMessage("OK");

        result.setProcessStatus("V2");
        postalCode.add("V5Z 4C2");
        ;
        postalCodeArr.getString().addAll(postalCode);
        address.setPostalCode(postalCodeArr);
        street.add("WEST BROADWAY");
        streetArr.getString().addAll(street);
        address.setStreet(streetArr);
        subBuilding.add("11TH FL");
        subBuildingArr.getString().addAll(subBuilding);
        address.setSubBuilding(subBuildingArr);
        houseNumber.add("601");
        houseNumberArr.getString().addAll(houseNumber);
        address.setHouseNumber(houseNumberArr);
        locality.add("VANCOUVER");
        localityArr.getString().addAll(locality);
        address.setLocality(localityArr);
        province.add("BC");
        provinceArr.getString().addAll(province);
        address.setProvince(provinceArr);
        country.add("CANADA");
        countryArr.getString().addAll(country);
        address.setCountry(countryArr);
        deliveryAddressLines.add("601 WEST BROADWAY 11TH FL");
        deliveryAddressLinesArr.getString().addAll(deliveryAddressLines);
        address.setDeliveryAddressLines(deliveryAddressLinesArr);

        resultData.setAddress(address);
        resultsData.add(resultData);
        resultsDataArr.getResultData().addAll(resultsData);
        result.setResultDataSet(resultsDataArr);
        results.add(result);
        resultsArr.getResult().addAll(results);
        response.setResults(resultsArr);
        processResponse.setProcessResult(response);
        sOAPBodyOutput.setProcessResponse(processResponse);
        sOAPEnvelopeOutput.setSoapBody(sOAPBodyOutput);

        return sOAPEnvelopeOutput;
    }

    private SOAPEnvelopeOutput createSOAPEnvelopeEmptyOutput() {
        SOAPEnvelopeOutput sOAPEnvelopeOutput = new SOAPEnvelopeOutput();
        SOAPBodyOutput sOAPBodyOutput = new SOAPBodyOutput();
        ProcessResponse processResponse = new ProcessResponse();
        Response response = new Response();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        List<ResultData> resultsData = new ArrayList<>();
        ResultData resultData = new ResultData();
        Address address = new Address();
        List<String> deliveryAddressLines = new ArrayList<>();
        List<String> country = new ArrayList<>();
        List<String> locality = new ArrayList<>();
        List<String> province = new ArrayList<>();
        List<String> houseNumber = new ArrayList<>();
        List<String> postalCode = new ArrayList<>();
        List<String> subBuilding = new ArrayList<>();
        List<String> street = new ArrayList<>();
        ArrayOfString deliveryAddressLinesArr = new ArrayOfString();
        ArrayOfString postalCodeArr = new ArrayOfString();
        ArrayOfString countryArr = new ArrayOfString();
        ArrayOfString localityArr = new ArrayOfString();
        ArrayOfString provinceArr = new ArrayOfString();
        ArrayOfString houseNumberArr = new ArrayOfString();
        ArrayOfString subBuildingArr = new ArrayOfString();
        ArrayOfString streetArr = new ArrayOfString();
        ArrayOfResult resultsArr = new ArrayOfResult();
        ArrayOfResultData resultsDataArr = new ArrayOfResultData();

        response.setStatusCode(100);
        response.setStatusMessage("OK");

        result.setProcessStatus("V2");
        postalCode.add("V5Z 4C2");
        ;
        postalCodeArr.getString().addAll(postalCode);
        address.setPostalCode(postalCodeArr);
        street.add("WEST BROADWAY");
        streetArr.getString().addAll(street);
        address.setStreet(streetArr);
        subBuilding.add("11TH FL");
        subBuildingArr.getString().addAll(subBuilding);
        address.setSubBuilding(subBuildingArr);
        houseNumber.add("601");
        houseNumberArr.getString().addAll(houseNumber);
        address.setHouseNumber(houseNumberArr);
        locality.add("VANCOUVER");
        localityArr.getString().addAll(locality);
        address.setLocality(localityArr);
        province.add("BC");
        provinceArr.getString().addAll(province);
        address.setProvince(provinceArr);
        country.add("CANADA");
        countryArr.getString().addAll(country);
        address.setCountry(countryArr);
        deliveryAddressLinesArr.getString().addAll(deliveryAddressLines);
        address.setDeliveryAddressLines(deliveryAddressLinesArr);

        resultData.setAddress(address);
        resultsData.add(resultData);
        resultsDataArr.getResultData().addAll(resultsData);
        result.setResultDataSet(resultsDataArr);
        results.add(result);
        resultsArr.getResult().addAll(results);
        response.setResults(resultsArr);
        processResponse.setProcessResult(response);
        sOAPBodyOutput.setProcessResponse(processResponse);
        sOAPEnvelopeOutput.setSoapBody(sOAPBodyOutput);

        return sOAPEnvelopeOutput;
    }
}