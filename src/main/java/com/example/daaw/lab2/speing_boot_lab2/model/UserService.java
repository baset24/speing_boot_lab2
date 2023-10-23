package com.example.daaw.lab2.speing_boot_lab2.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User myUser) {
        return userRepository.save(myUser);
    }

    public User getUserById(Long id) {
        return userRepository.findUserWithAddressById(id);
    }

    public List<User> findUsersByBirthdates(Date d1, Date d2) {
        return userRepository.findByBirthdateBetween(d1, d2);
    }

    public List<User> findUsersByMinAge(int age) {
        return userRepository.findByAgeGreaterThanEqual(age);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<User> getUserByEmailDoamin(String domain){
        return userRepository.findByEmailEndingWith(domain);
    }



}
