package com.example.daaw.lab2.speing_boot_lab2.control;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.daaw.lab2.speing_boot_lab2.model.Address;
import com.example.daaw.lab2.speing_boot_lab2.model.User;
import com.example.daaw.lab2.speing_boot_lab2.model.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // http://localhost:8080/addUser
    @GetMapping(value = "/addUser")
    public String addUser() {

        // create an object Address
        Address myAddress = new Address(null, "Setif", "Algeria", null);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1979, 0, 1);
        Date date = calendar.getTime();

        // create and object User
        User myUser = new User(null, "zakaria", "lakhdara", date, 24, "zakaria@gmail.com", null);

        // bind the user with his address
        myUser.setAddress(myAddress);
        myAddress.setUser(myUser);

        // save the user in the database
        userService.addUser(myUser);

        return " <h1>User added successfully ... </h1>";
    }

    // http://localhost:8080/getUser/94
    @GetMapping(value = "/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // http://localhost:8080/addUsers
    @GetMapping(value = "/addUsers")
    public void addUsers() {
        String[][] Names = {
                { "Rachida", "Zaaf" }, { "Hala", "Halima" }, { "Ben Mbarek", "Mohammed" }
        };

        // String[] Names = {
        // "Mohammed", "Fatima", "Ali", "Amina", "Omar", "Layla", "Ahmed", "Sara",
        // "Hassan", "Nour", "Abdullah", "Ayesha", "Khalid", "Jasmine", "Mustafa",
        // "Leila",
        // "Ibrahim", "Zainab", "Youssef", "Rania", "Sami", "Hala", "Amir", "Lina",
        // "Zakaria", "Amina", "Tariq", "Farida", "Nasser", "Dina"
        // };

        // String[] Cities = {
        // "Algiers", "Oran", "Constantine", "Annaba", "Blida", "Batna", "Djelfa",
        // "Sétif", "Biskra", "Tébessa", "Tlemcen", "Béjaïa", "Tiaret", "Ech-Chleff",
        // "Guelma", "Sidi Bel Abbès", "Khenchela", "Souk Ahras", "Oum El Bouaghi",
        // "El Oued", "M'sila", "El Eulma", "Tizi Ouzou", "Béchar", "Mostaganem",
        // "Bordj Bou Arreridj", "Chlef", "Skikda", "El Harrach", "Ghardaïa"
        // };

        // try {

        for (int i = 0; i < Names.length; i++) {

            Address myAddress = new Address(null, "Adrar", "Algeria", null);

            Calendar calendar = Calendar.getInstance();
            calendar.set(1990 + i, 0, 1);
            Date date = calendar.getTime();

            User myUser = new User(null, Names[i][0], Names[i][1], date, 30 - i,
                    Names[i][0] + "." + Names[i][1] + "@univ-adrar.dz", myAddress);

            myUser.setAddress(myAddress);
            myAddress.setUser(myUser);

            userService.addUser(myUser);
        }

        // System.out.println(" the users was added successfully");
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // System.out.println("<h1> error </h1>");
        // }

    }

    // http://localhost:8080/birthdate
    @GetMapping(value = "/birthdade")
    public List<User> birthdate() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 0, 1);
        Date date1 = calendar.getTime();
        calendar.set(2015, 0, 1);
        Date date2 = calendar.getTime();

        return userService.findUsersByBirthdates(date1, date2);

    }

    // http://localhost:8080/minAge
    @GetMapping(value = "/minAge")
    public List<User> age() {
        return userService.findUsersByMinAge(15);
    }

    // http://localhost:8080/getUsers/0
    @GetMapping(value = "/getUsers/{pageNumber}")
    public List<User> getUsersByPages(@PathVariable int pageNumber) {

        int pageSize = 5;

        // Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> userPage = userService.getAllUsers(pageable);
        List<User> userList = userPage.getContent();

        return userList;
    }

    // http://localhost:8080/deleteUser/?
    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("<h1> User with ID " + id + " was deleted.</h1>");
    }

    // http://localhost:8080/getByEmailDomain/@univ-adrar.dz
    @GetMapping("/getByEmailDomain/{domain}")
    public List<User> findUsersByEmailDomain(@PathVariable String domain) {
        return userService.getUserByEmailDoamin(domain);
    }

}
