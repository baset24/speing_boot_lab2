package com.example.daaw.lab2.speing_boot_lab2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.address WHERE u.id = :userId")
    public User findUserWithAddressById(Long userId);

    public List<User> findByBirthdateBetween(Date startDate, Date endDate);

    public List<User> findByAgeGreaterThanEqual(Integer age);

    // i added yhis methode

    public void deleteById(Long userId);

    public Page<User> findByEmailEndingWith(String domain, Pageable pageable);

    public Page<User> findByFirstNameOrLastNameOrEmailIgnoreCaseContaining(String firstName, String lastName,
            String email, Pageable pageable);

            @Query("SELECT u\r\n" + //
                    "FROM User u\r\n" + //
                    "JOIN u.address a\r\n" + //
                    "WHERE LOWER(a.city) LIKE LOWER(CONCAT('%', :city, '%'))\r\n" + //
                    "   OR LOWER(a.country) LIKE LOWER(CONCAT('%', :country, '%'))\r\n" )
    public Page<User> findByCityOrCountry(@Param("city") String city, @Param("country") String country, Pageable pageable);

    
    // @Query("SELECT u FROM User u WHERE LOWER(u.address.city) LIKE LOWER(CONCAT('%', :city, '%')) OR LOWER(u.address.country) LIKE LOWER(CONCAT('%', :country, '%'))")
    // List<User> findByCityOrCountry(@Param("city") String city, @Param("country") String country);


}
