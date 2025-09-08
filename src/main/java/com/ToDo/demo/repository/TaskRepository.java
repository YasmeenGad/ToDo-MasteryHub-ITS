package com.ToDo.demo.repository;

import com.ToDo.demo.model.entity.TaskEntity;
import com.ToDo.demo.model.entity.UserEntity;
import com.ToDo.demo.utils.enums.TaskLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser(UserEntity user);
    List<TaskEntity> findByUserAndLabel(UserEntity user, TaskLabel label);
}
