package com.logistics.moolryu.domains.hub.controller.dto.request;

import lombok.Getter;

@Getter
public class HubCreateRequest {

    private String hubName;

    private String hubAddress;

    private double latitude;

    private double longitude;
}
