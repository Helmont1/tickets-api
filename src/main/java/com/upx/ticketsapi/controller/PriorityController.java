package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Priority;
import com.upx.ticketsapi.payload.PriorityDTO;
import com.upx.ticketsapi.service.PriorityService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {
    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    @RolesAllowed("get-priorities")
    public ResponseEntity<SuccessResponse<List<Priority>>> getAll() {
        return SuccessResponseUtil.okResponse(priorityService.getAll());
    }

    @GetMapping("/{priorityId}")
    @RolesAllowed("get-priorities")
    public ResponseEntity<SuccessResponse<Priority>> getById(@PathVariable Integer priorityId) {
        return SuccessResponseUtil.okResponse(priorityService.getById(priorityId));
    }

    @PostMapping
    @RolesAllowed("create-priorities")
    public ResponseEntity<SuccessResponse<Priority>> create(@RequestBody PriorityDTO priority) {
        return SuccessResponseUtil.createdResponse(priorityService.create(priority));
    }
}
