package com.learning.all.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.all.entity.TeacherEntity;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {

}
