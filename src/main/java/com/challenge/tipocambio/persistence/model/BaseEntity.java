package com.challenge.tipocambio.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dpena
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;
    @Column(name = "modifiedBy")
    private String modifiedBy;
    @Column(name = "lastDateModified")
    private LocalDateTime lastDateModified;

    @PrePersist
    public void onPersist() {
        this.createdBy = "backend";
        this.dateCreated = LocalDateTime.now();
        this.enabled = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedBy = "backend";
        this.lastDateModified = LocalDateTime.now();
    }
}