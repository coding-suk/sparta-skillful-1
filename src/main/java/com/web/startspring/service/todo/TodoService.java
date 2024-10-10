package com.web.startspring.service.todo;


import com.web.startspring.dto.todo.TodoRequestDto;
import com.web.startspring.dto.todo.TodoResponseDto;
import com.web.startspring.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {

    // 할일 등록
    Todo createTodo(TodoRequestDto todoRequestDto);

    // 단건 조회
    TodoResponseDto getTodo(Long tid);

    // 전체 조회
    Page<TodoResponseDto> getAllTodos(String name, LocalDate modifiedAt, Pageable pageable);

    // 수정
    Todo updateTodo(Long tid,TodoRequestDto todoRequestDto);

    // 삭제
    Todo deleteTodo(Long tid);

}
