package com.ruslanshakirov.crm.entity.document;

import com.ruslanshakirov.crm.entity.AbstractNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "document_details")
@Getter
@Setter
public class DocDetails extends AbstractNamedEntity {
    private Integer number;
    private String measureType;
    private Integer price;
    private Integer vatPrice;
    private Integer totalSum;
    private Integer vatSum;
    private Integer noVatSum;
    @ManyToOne
    private Doc doc;
}
