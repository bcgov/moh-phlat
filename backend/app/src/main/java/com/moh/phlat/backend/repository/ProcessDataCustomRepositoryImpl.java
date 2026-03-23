package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.moh.phlat.backend.databc.util.Constants.COLON;

@Repository
public class ProcessDataCustomRepositoryImpl implements ProcessDataCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Find all unique column Values based on control ID and column key
     *
     * @param controlTableId - controlTableId
     * @param columnKey      - columnKey
     * @return - returns all unique column Values based on control ID and column key
     */
    @Override
    public List<String> getUniqueColumnValues(Long controlTableId, String columnKey) {
        List<String> result;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        if (columnKey.equals("messages")) {
            // Fetch messages from DB and convert them to String criteria
            CriteriaQuery<Message> query = cb.createQuery(Message.class);
            Root<ProcessData> root = query.from(ProcessData.class);

            query.select(root.get(columnKey)).distinct(true);
            query.where(cb.equal(root.get("controlTableId"), controlTableId));
            query.orderBy(cb.asc(root.get("id")));
            List<Message> message = entityManager.createQuery(query).getResultList();
            result = !CollectionUtils.isEmpty(message)
                    ? message.stream()
                    .filter(msg -> StringUtils.hasText(msg.getMessageType())
                            && StringUtils.hasText(msg.getMessageCode()) && StringUtils.hasText(msg.getMessageDesc()))
                    .sorted(Comparator.comparing(Message::getMessageType))
                    .map(msg -> msg.getMessageType() + COLON +
                            msg.getMessageCode() + COLON + msg.getMessageDesc())
                    .distinct()
                    .collect(Collectors.toList())
                    : new ArrayList<>();
        } else {
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<ProcessData> root = query.from(ProcessData.class);

            query.select(root.get(columnKey)).distinct(true);
            query.where(cb.equal(root.get("controlTableId"), controlTableId));
            query.orderBy(cb.asc(root.get(columnKey)));
            result = entityManager.createQuery(query).getResultList();
        }
        return result;
    }

}