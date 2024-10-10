package com.web.startspring.dto.todo;

import lombok.Builder;
import lombok.Data;

@Data
public class TodoRequestDto {

    private String todo;

    private String password;

    private Long manager_id;

    @Builder
    public TodoRequestDto(String todo, String password, Long manager_id) {
        this.todo = todo;
        this.password = password;
        this.manager_id = manager_id;
    }

}
