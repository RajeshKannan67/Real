package com.learning.all.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.all.entity.PrincipalEntity;


@Repository
public interface PrincipalRepo extends JpaRepository<PrincipalEntity, Long> {

}
