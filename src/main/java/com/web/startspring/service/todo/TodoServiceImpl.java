package com.web.startspring.service.todo;

import com.web.startspring.dto.todo.TodoRequestDto;
import com.web.startspring.dto.todo.TodoResponseDto;
import com.web.startspring.entity.Todo;
import com.web.startspring.exception.IdNotFoundException;
import com.web.startspring.exception.TodoNotFoundException;
import com.web.startspring.repository.ManagerRepository;
import com.web.startspring.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final ManagerRepository managerRepository;

    LocalDate currentDate = LocalDate.now();

    @Override
    @Transactional
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = Todo.builder()
                .todo(todoRequestDto.getTodo())
                .password(todoRequestDto.getPassword())
                .manager_id(todoRequestDto.getManager_id())
                .build();
        return todoRepository.save(todo);
    }

    @Override
    public TodoResponseDto getTodo(Long tid) {
        Todo todo = todoRepository.findById(tid).orElseThrow(()-> new TodoNotFoundException("조회할 할일이 없습니다"));
        return TodoResponseDto.from(todo);
    }

    @Override
    public Page<TodoResponseDto> getAllTodos(String name, LocalDate modifiedAt, Pageable pageable) {

        Page<Todo> todos = todoRepository.findByTodo(name, modifiedAt, pageable);

        return todos.map(todo -> TodoResponseDto.from(todo,managerRepository.findById(todo.getManager_id())));

//         for (Todo todo : todos) {
////             Manager manager = managerRepository.findBYId(todo.getManager_id()).orElse(null);
////             String managerName = (manager != null) ? manager.getName() : "Unknown";
//             TodoResponseDto dto = new TodoResponseDto(
//                     todo.getTid(),
//                     todo.getTodo(),
//                     todo.getManager_id(),
//                     todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
//                     todo.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))
//             );
//             dtoList.add(dto);
//         }
//        return dtoList;
    }

    @Override
    @Transactional
    public Todo updateTodo(Long tid, TodoRequestDto todoRequestDto) {
        Todo todo = todoRepository.findById(tid).orElseThrow(()-> new IdNotFoundException("조회된 아이디가 없습니다"));

        Todo update = Todo.builder()
                .tid(tid)
                .todo(todoRequestDto.getTodo())
                .manager_id(todoRequestDto.getManager_id())
                .modifiedAt(Timestamp.valueOf(todo.getModifiedAt().toLocalTime()))
                .build();
        return todoRepository.update(update);
    }

    @Override
    @Transactional
    public Todo deleteTodo(Long tid) {
        Todo todo = todoRepository.findById(tid).orElseThrow(()-> new IdNotFoundException("조회된 아이디가 없습니다"));
        if(todo == null) {
            throw new TodoNotFoundException("할일이 존재 하지 않습니다");
        }
        todoRepository.deleteById(tid);
        return todo;
    }


}
