package com.practice.controller;

import com.practice.Entity.User;
import com.practice.response.ResponseHandler;
import com.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController extends ResponseHandler {

    @Autowired
    private UserService userService;
// Default JPA
    @GetMapping("/getAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/getAllUsersJpql")
    public List<User> findAllUsersJpql() {
        return userService.findAllUsersJpql();
    }

    @GetMapping(value = "/getAllUsersNq")
    public List<User> findAllUsersNQ() {
        return userService.findAllUsersNative();
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginDto) throws AuthenticationException {
        User user = userService.login(loginDto.getUserName(), loginDto.getPassword());
 ;

        return user;
    }


    @PostMapping("/Signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {


            userService.save(user);

            return new ResponseEntity<String>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>( HttpStatus.EXPECTATION_FAILED);

        }
    }
//@PostMapping("/Signup")
//public ResponseEntity<Object> signup(@RequestBody User user) {
//    try {
//
//
//        User objUser = userService.save(user);
//        Map<String, Object> obj = null;
//        obj.put("User is registered successfully", objUser);
//        return new ResponseEntity<Object>(obj, HttpStatus.OK);
//    } catch (Exception e) {
//        return new ResponseEntity<Object>("user is registered failed", HttpStatus.EXPECTATION_FAILED);
//
//    }
//}

    //pathvariable & custom response
    @GetMapping("/getOneUser/{id}")
    public ResponseEntity findUser(@PathVariable("id") Long id) {
        try {
            User user = userService.findUser(id);
            System.out.println("user details"+user);
            return ResponseHandler.customResponse("Successfully fetched users!", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.customResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //request object & custom response

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {

            User usr = userService.updateUser(user);
            return ResponseHandler.customResponse("User updated sucessfully", HttpStatus.OK, usr);

        } catch (Exception e) {
            return ResponseHandler.customResponse("Unable to update", HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    //    http://localhost:8080/user/deleteUser?id=1  ->query params & response entity
    @DeleteMapping("/deleteUser")
    public ResponseEntity< String> deleteUser(@RequestParam("id") Long id) {
        return userService.deleteUserById(id) ? new ResponseEntity<String>( HttpStatus.OK) : new ResponseEntity<String>( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/undoDelete")
    public ResponseEntity<String> undoDelete(@RequestParam("id") Long id) {

        return userService.undoDeleteById(id) ? new ResponseEntity<String>( HttpStatus.OK) : new ResponseEntity<String>( HttpStatus.NOT_FOUND);

    }

}
