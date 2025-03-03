package com.logistics.moolryu.domains.hub.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hub {

    @Id
    @Tsid
    private Long id;

    @Column(nullable = false)
    private String hubName;

    @Column(nullable = false)
    private String hubAddress;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point hubLocation;


    @Builder
    public Hub(String hubName, String hubAddress, Point hubLocation) {
        this.hubName = hubName;
        this.hubAddress = hubAddress;
        this.hubLocation = hubLocation;
    }

    public static Hub createHub(String hubName, String hubAddress, Point hubLocation) {
        return Hub.builder()
                .hubName(hubName)
                .hubAddress(hubAddress)
                .hubLocation(hubLocation)
                .build();
    }
}