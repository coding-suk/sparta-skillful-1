package com.web.startspring.dto.todo;

import com.web.startspring.entity.Manager;
import com.web.startspring.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class TodoResponseDto {

    private Long tid;
    private String todo;
    private Long manager_id;
    private String createdAt;
    private String modifiedAt;

    public static TodoResponseDto from(Todo todo) {
        return new TodoResponseDto(
                todo.getTid(),
                todo.getTodo(),
                todo.getManager_id(),
                todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                todo.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))
        );
    }

}
