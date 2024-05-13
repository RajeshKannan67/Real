package com.learning.all.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.all.entity.UserDetails;
import com.mysql.cj.LicenseConfiguration;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long> {

	
   public UserDetails findByUserId(Long userId);

	//@Query("SELECT u FROM UserDetails u WHERE u.user_id = :userId and u.password= :password")
	UserDetails findByUserIdAndPassword(Long userId, String password);
	
	//Optional<UserDetails> findByserid(Long id);

}