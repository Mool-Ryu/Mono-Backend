package com.logistics.moolryu.domains.hub.controller.dto.response;

import com.logistics.moolryu.domains.hub.entity.Hub;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HubCreateResponse {

    private final Long id;

    private final String hubName;

    private final String hubAddress;

    private final double latitude;

    private final double longitude;

    public static HubCreateResponse of(Hub hub) {

        double lon = hub.getHubLocation().getX();
        double lat = hub.getHubLocation().getY();

        return HubCreateResponse.builder()
                .id(hub.getId())
                .hubName(hub.getHubName())
                .hubAddress(hub.getHubAddress())
                .latitude(lat)
                .longitude(lon)
                .build();
    }

}
