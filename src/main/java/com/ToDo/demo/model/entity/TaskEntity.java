package com.ToDo.demo.model.entity;

import com.ToDo.demo.utils.enums.TaskLabel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskLabel label;

    @Column(nullable = false)
    private LocalDateTime date;
}
