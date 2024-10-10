package com.web.startspring.dto.manager;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerResponseDto {

    private Long mid;

    private String name;

    private String email;

    private String uuid;

    LocalDate createdAt;

    LocalDate modifiedAt;

    @Builder
    public ManagerResponseDto(Long mid, String name, String email, String uuid, LocalDate createdAt, LocalDate modifiedAt) {
        this.mid = mid;
        this.name = name;
        this.email = email;
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}