package com.ruslanshakirov.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@SequenceGenerator(name = "seq_gen", sequenceName = "hibernate_seq",initialValue = 1, allocationSize = 1)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "seq_gen")
    protected Long id;
    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
