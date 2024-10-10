package com.web.startspring.entity;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Timestamped {

    @Timestamp
    private LocalDateTime createdAt;

    @Timestamp
    private LocalDateTime modifiedAt;

}
