package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Status;
import com.upx.ticketsapi.service.StatusService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<SuccessResponse<Status>> getOne(@PathVariable Integer statusId) {
        return SuccessResponseUtil.okResponse(statusService.getById(statusId));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Status>>> getAll() {
        return SuccessResponseUtil.okResponse(statusService.findAll());
    }

}
