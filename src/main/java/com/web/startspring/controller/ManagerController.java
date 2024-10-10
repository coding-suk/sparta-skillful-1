package com.web.startspring.controller;

import com.web.startspring.dto.manager.ManagerRequestDto;
import com.web.startspring.entity.Manager;
import com.web.startspring.service.manager.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/create")
    public ResponseEntity<Manager> createManager(@Valid @RequestBody ManagerRequestDto managerRequestDto) {
        return ResponseEntity.ok(managerService.createManager(managerRequestDto));
    }
}
