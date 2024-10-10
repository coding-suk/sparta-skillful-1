package com.web.startspring.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TodoListResponseDto {

    private List<TodoResponseDto> todoResponse;

    public static TodoListResponseDto of(List<TodoResponseDto> todoResponse) {
        return new TodoListResponseDto(todoResponse);
    }

}
