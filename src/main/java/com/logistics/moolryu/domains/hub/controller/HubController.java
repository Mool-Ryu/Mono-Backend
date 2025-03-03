package com.logistics.moolryu.domains.hub.controller;

import com.logistics.moolryu.domains.hub.controller.dto.request.HubCreateRequest;
import com.logistics.moolryu.domains.hub.controller.dto.response.HubCreateResponse;
import com.logistics.moolryu.domains.hub.service.HubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class HubController {

    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    @PostMapping
    public ResponseEntity<HubCreateResponse> createHub(@RequestBody HubCreateRequest dto) {
        return ResponseEntity.ok(hubService.createHub(dto));
    }
}
