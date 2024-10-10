package com.web.startspring.repository;

import com.web.startspring.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findByTodo(String name, LocalDate modifiedAt, Pageable pageable);

    Todo update(Todo todo);
}
