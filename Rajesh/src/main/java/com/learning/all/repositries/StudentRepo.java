package com.learning.all.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.all.entity.StudentEntity;


@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {

}
