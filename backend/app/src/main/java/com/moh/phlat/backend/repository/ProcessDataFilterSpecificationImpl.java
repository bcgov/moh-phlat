package com.moh.phlat.backend.repository;

import java.util.List;

import com.moh.phlat.backend.model.Message;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.moh.phlat.backend.model.ProcessData;
import lombok.NoArgsConstructor;

import static com.moh.phlat.backend.databc.util.Constants.COLON;

@NoArgsConstructor
@Component
public class ProcessDataFilterSpecificationImpl implements ProcessDataFilterSpecification {
    @Override
    public Specification<ProcessData> getDataWithMessages(Long controlId) {
        return (root, query, builder) -> {
            return builder.equal(root.get("controlTableId"), controlId);
        };
    }

    /**
     *  Modifies combined Specification<ProcessData> with messages criteria
     *
     * @param combinedSpecification - combined Specification
     * @param messageCriteria - Message Criteria in String format separated by colon
     * @return - Modified combined Specification<ProcessData> with messages criteria
     */
    @Override
    public Specification<ProcessData> getProcessDataWithFilterMessages(Specification<ProcessData> combinedSpecification, String messageCriteria) {
            if (StringUtils.hasText(messageCriteria)) {
                String[] arrOfStr = messageCriteria.split(COLON);
                if (arrOfStr.length == 3) {
                    if (StringUtils.hasText(arrOfStr[0])) {
                        combinedSpecification = combinedSpecification.and(addMessageCriteria("messageType", arrOfStr[0]));
                    }
                    if (StringUtils.hasText(arrOfStr[1])) {
                        combinedSpecification = combinedSpecification.and(addMessageCriteria("messageCode", arrOfStr[1]));
                    }
                    if (StringUtils.hasText(arrOfStr[2])) {
                        combinedSpecification = combinedSpecification.and(addMessageCriteria("messageDesc", arrOfStr[2]));
                    }
                }
            }
        return combinedSpecification;
    }

    /**
     *  Combines Specification<ProcessData> with messages criteria
     *
     * @param key - table column
     * @param value - value
     * @return - Combines Specification<ProcessData> with messages criteria
     */
    public static Specification<ProcessData> addMessageCriteria(String key, String value) {
        return (root, query, criteriaBuilder) -> {
            Join<ProcessData, Message> messagesJoin = root.join("messages");
            return criteriaBuilder.equal(messagesJoin.<String>get(key), value);
        };
    }

    @Override
    public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, String value) {
        if (StringUtils.hasText(value)) {
            spec = spec.and((root, query, builder) -> root.get(columnKey).in(value));
        }
        return spec;
    }

    @Override
    public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, List<String> values) {
        if (values != null) {
            spec = spec.and((root, query, builder) -> root.get(columnKey).in(values));
        }
        return spec;
    }

}