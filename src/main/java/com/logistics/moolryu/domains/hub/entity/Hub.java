package com.logistics.moolryu.domains.hub.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Hub {

    @Id
    @Tsid
    private Long id;

    @Column(nullable = false)
    private String hubName;

    @Column(nullable = false)
    private String hubAddress;
}