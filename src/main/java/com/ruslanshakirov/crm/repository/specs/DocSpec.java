package com.ruslanshakirov.crm.repository.specs;

import com.ruslanshakirov.crm.entity.document.Doc;
import com.ruslanshakirov.crm.entity.document.DocStatus;
import com.ruslanshakirov.crm.entity.document.Doc_;
import com.ruslanshakirov.crm.entity.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.Objects;

public class DocSpec {
    public static Specification<Doc> filter(DocStatus status, LocalDate startDate, LocalDate endDate, String agentName, Profile profile) {
        return (root, criteriaQuery, cb) -> {
            Predicate statusPred = Objects.nonNull(status) ? cb.equal(root.get(Doc_.status), status) : cb.conjunction();
            Predicate startDatePred = Objects.nonNull(status) ? cb.greaterThanOrEqualTo(root.get(Doc_.receiptDate), startDate) : cb.conjunction();
            Predicate endDatePred = Objects.nonNull(status) ? cb.lessThanOrEqualTo(root.get(Doc_.receiptDate), endDate) : cb.conjunction();
            Predicate agentNamePred = Objects.nonNull(status) ? cb.like(root.get(Doc_.agentName), agentName + "%") : cb.conjunction();
            Predicate profilePred = cb.equal(root.get(Doc_.profile), profile);
            return cb.and(statusPred, startDatePred, endDatePred, agentNamePred, profilePred);
        };
    }
}
