package com.web.startspring.controller;

import com.web.startspring.dto.todo.TodoListResponseDto;
import com.web.startspring.dto.todo.TodoRequestDto;
import com.web.startspring.dto.todo.TodoResponseDto;
import com.web.startspring.entity.Todo;
import com.web.startspring.exception.InvalidPasswordException;
import com.web.startspring.exception.TodoNotFoundException;
import com.web.startspring.repository.TodoRepository;
import com.web.startspring.service.todo.TodoService;
import com.web.startspring.service.todo.TodoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
@Slf4j
public class TodoController {

    private final TodoServiceImpl todoService;
    private final TodoRepository todoRepository;

    // 할일 등록
    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoRequestDto todoRequestDto) {
        log.info("creating todo: {}", todoRequestDto);
        return ResponseEntity.ok(todoService.createTodo(todoRequestDto));
    }

    // 단건 조회
    @GetMapping("/{tid}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long tid) {
        if(todoService.getTodo(tid) == null) {
            throw new TodoNotFoundException("일정이 존재하지 않습니다");
        }
        log.info("Getting Todo: {}" , tid);
        return ResponseEntity.ok(todoService.getTodo(tid));
    }

    // 전체 조회
    @GetMapping("/list")
    public ResponseEntity<TodoListResponseDto> getAllTodos(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) LocalDate modifiedAt,
                                                           @PageableDefault(page = 0, size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        TodoListResponseDto todoListResponse = TodoListResponseDto.of(todoService.getAllTodos(name, modifiedAt, pageable));
        log.info("Getting Todo: {}", todoListResponse);
        return ResponseEntity.ok(todoListResponse);
    }

    // 수정
    @PostMapping("/update/{tid}")
    public ResponseEntity<Todo> updateTodo(@Valid @RequestParam Long tid,
                                                      @RequestBody TodoRequestDto todoRequestDto) {
        if(todoRequestDto.getPassword() == null || todoRequestDto.getPassword().isEmpty()) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다");
        }

        Todo update = todoService.updateTodo(tid, todoRequestDto);
        log.info("updating todo: {}", update);
        return ResponseEntity.ok(update);
    }

    // 삭제
    @DeleteMapping("/delete/{tid}")
    public ResponseEntity<List<Todo>> deleteTodo(@RequestParam Long tid) {
        if(todoService.getTodo(tid) == null) {
            throw new TodoNotFoundException("이미 삭제된 일정입니다");
        }
        todoService.deleteTodo(tid);
        List<Todo> remainingTodo = todoRepository.findAll();
        log.info("Delete by info: {}", remainingTodo);
        return new ResponseEntity<>(remainingTodo, HttpStatus.OK);

    }

}
