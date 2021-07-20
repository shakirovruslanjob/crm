package com.ruslanshakirov.crm.entity.document;

import com.ruslanshakirov.crm.entity.AbstractBaseEntity;
import com.ruslanshakirov.crm.entity.profile.Address;
import com.ruslanshakirov.crm.entity.profile.BankAccount;
import com.ruslanshakirov.crm.entity.profile.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "documents")
@Getter
@Setter
public class Doc extends AbstractBaseEntity {
    private DocStatus status = DocStatus.ACTIVE;
    private Integer invoiceNum;
    private Integer aktNum;
    private Integer receiptNum;
    private LocalDate invoiceDate;
    private LocalDate aktDate;
    private LocalDate receiptDate;
    private String bin;
    private String agentName;
    @Embedded
    private BankAccount bankAccount;
    @Embedded
    private Address address;
    private String paymentCondition;
    private String reason;
    private String poDover;
    private String wayBill;
    @ManyToOne
    private Profile profile;
    @OneToMany(mappedBy = "doc", fetch = FetchType.EAGER)
    private List<DocDetails> docDetails;
}
