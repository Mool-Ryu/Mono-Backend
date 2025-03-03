package com.logistics.moolryu.domains.hub.service;

import com.logistics.moolryu.domains.hub.controller.dto.request.HubCreateRequest;
import com.logistics.moolryu.domains.hub.controller.dto.response.HubCreateResponse;
import com.logistics.moolryu.domains.hub.entity.Hub;
import com.logistics.moolryu.domains.hub.repository.HubRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

@Service
public class HubService {

    private final GeometryFactory geometryFactory;
    private final HubRepository hubRepository;

    public HubService(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
        this.geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    }

    public HubCreateResponse createHub(HubCreateRequest dto) {

        double latitude = dto.getLatitude();
        double longitude = dto.getLongitude();

        Point point = geometryFactory
                .createPoint(new Coordinate(longitude, latitude));

        Hub hub = Hub.builder()
                .hubName(dto.getHubName())
                .hubAddress(dto.getHubAddress())
                .hubLocation(point)
                .build();

        hubRepository.save(hub);

        return HubCreateResponse.of(hub);
    }
}
