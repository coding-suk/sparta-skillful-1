package com.web.startspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;

@Getter
@ToString
public class Todo extends Timestamped{

    private Long tid;

    @Size(max = 200)
    private String todo; // 할일

    @NotBlank
    @JsonIgnore
    private String password;

    private Long manager_id;

    @Builder
    public Todo(Long tid, String todo, String password, Long manager_id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.tid = tid;
        this.todo = todo;
        this.password = password;
        this.manager_id = manager_id;
        this.setCreatedAt(createdAt);
        this.setModifiedAt(modifiedAt);
    }


}
