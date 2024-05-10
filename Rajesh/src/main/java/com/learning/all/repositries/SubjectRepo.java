package com.learning.all.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.all.entity.SubjectEntity;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {

}
