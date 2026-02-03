package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.repository.ProcessDataFilterSpecification;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.testsupport.factories.ProcessDataFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author CGI
 */
@ExtendWith(MockitoExtension.class)
class ProcessDataServiceImplTest {

    @Mock
    private ProcessDataRepository processDataRepository;

    @Mock
    ProcessDataFilterSpecification specificationService;

    @InjectMocks
    private ProcessDataServiceImpl processDataServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    List<ProcessData> processDataList = Collections.unmodifiableList(ProcessDataFactory.createProcessDataListWithMessages());


    @Test
    public void testFilterWithMessages() {
        List<Sort.Order> sortOrders = new ArrayList<>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        sortOrders.add(order);
        ProcessDataFilterParams filterProcess = new ProcessDataFilterParams();
        List<String> filterProcessMessages = new ArrayList<>();
        filterProcessMessages.add("WARNING::PRS.SYS.ADR.UNK.1.0.1111::An existing facility address was found, can not create duplicate facility. IFC.0000001.BC.PRS");
        filterProcess.setMessages(filterProcessMessages);

        Specification<ProcessData> processDataSpecification = new Specification<ProcessData>() {
            @Override
            public Predicate toPredicate(Root<ProcessData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
        };

        doReturn(processDataSpecification).when(specificationService).getDataWithMessages(Mockito.anyLong());
        doReturn(processDataList).when(processDataRepository).findAll(any(Specification.class));
        doReturn(processDataSpecification).when(specificationService).getProcessDataWithFilterMessages(any(Specification.class), Mockito.anyString());
        doReturn(processDataSpecification).when(specificationService).buildSpecificationAnd(Mockito.<Specification<ProcessData>>any(), Mockito.anyString(), Mockito.anyString());

        Page<ProcessData> result = processDataServiceImpl.getProcessDataWithMessages(1L, "rowStatus",
                1, 10, filterProcess, sortOrders);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(4, result.getContent().get(0).getMessages().size());
        assertEquals(4, result.getContent().get(1).getMessages().size());
        assertEquals("WARNING", result.getContent().get(0).getMessages().get(0).getMessageType());
        assertEquals("PRS.SYS.ADR.UNK.1.0.1111", result.getContent().get(0).getMessages().get(0).getMessageCode());
        assertEquals("An existing facility address was found, can not create duplicate facility. IFC.0000001.BC.PRS", result.getContent().get(0).getMessages().get(0).getMessageDesc());
    }

    @Test
    public void testFilter() {
        List<Sort.Order> sortOrders = new ArrayList<>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        sortOrders.add(order);
        ProcessDataFilterParams filterProcess = new ProcessDataFilterParams();
        List<String> filterProcessMessages = new ArrayList<>();
        filterProcessMessages.add("1");
        filterProcess.setId(filterProcessMessages);

        Specification<ProcessData> processDataSpecification = new Specification<ProcessData>() {
            @Override
            public Predicate toPredicate(Root<ProcessData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
        };
        Page<ProcessData> processData = new PageImpl<>(processDataList.subList(0, 2), PageRequest.of(0, 2, Sort.by(sortOrders)), processDataList.size());

        doReturn(processDataSpecification).when(specificationService).getDataWithMessages(Mockito.anyLong());
        doReturn(processData).when(processDataRepository).findAll(any(Specification.class), any(Pageable.class));
        doReturn(processDataSpecification).when(specificationService).buildSpecificationAnd(Mockito.<Specification<ProcessData>>any(), Mockito.anyString(), Mockito.anyString());
        doReturn(processDataSpecification).when(specificationService).buildSpecificationAnd(Mockito.<Specification<ProcessData>>any(), Mockito.anyString(), Mockito.anyList());

        Page<ProcessData> result = processDataServiceImpl.getProcessDataWithMessages(1L, "rowStatus",
                1, 2, filterProcess, sortOrders);
        assertNotNull(result);
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals(2L, result.getContent().get(1).getId());
    }
}