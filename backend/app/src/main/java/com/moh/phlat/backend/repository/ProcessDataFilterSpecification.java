package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.ProcessData;

public interface ProcessDataFilterSpecification {
    Specification<ProcessData> getDataWithMessages(Long controlId);
    Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, String value);
    Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, List<String> values);
    /**
     *  Modifies combined Specification<ProcessData> with messages criteria
     *
     * @param combinedSpecification - combined Specification
     * @param messageCriteria - Message Criteria in String format separated by colon
     * @return - Modified combined Specification<ProcessData> with messages criteria
     */
    Specification<ProcessData> getProcessDataWithFilterMessages(Specification<ProcessData> combinedSpecification, String messageCriteria);
}