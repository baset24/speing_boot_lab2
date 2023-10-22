package com.example.daaw.lab2.speing_boot_lab2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long>{
    
    @Query("SELECT u FROM User u JOIN FETCH u.address WHERE u.id = :userId")
    public User findUserWithAddressById(Long userId);

    public List<User> findByBirthdateBetween(Date startDate, Date endDate);

    public List<User> findByAgeGreaterThanEqual(Integer age);

    
}
