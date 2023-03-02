package com.practice.service;

import com.practice.Entity.User;
import com.practice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepo userRepo;
    @Override

    public List<User> findAllUsersJpql() {
        return  userRepo.find();

    }

    public List<User> findAllUsersNative(){
        return userRepo.findActive();
    }
    public List<User> findAllUsers() {
        return  userRepo.findByUserType("Normal");

    }
    @Override
    public User login(String username, String password) throws AuthenticationException {
        User user =  userRepo.findByUserNameAndPassword(username,password);
        if(user==null){
            throw new AuthenticationException();
        }
        return user;
    }

    public User save(User user) {

        return userRepo.save(user);
    }
    public User findUser(Long id) {


        return userRepo.findByIdAndIsActive(id,true);
    }

    public User findUserDel(Long id) {


        return userRepo.findByIdAndIsActive(id,false);
    }
   public  User updateUser(User user){

       return userRepo.save(user);
   }

    public boolean deleteUserById(Long id) {
        User user = this.findUser(id);
        if(user!=null){
            user.setIsActive(false);
            this.userRepo.save(user);
            return true;
        }

        return false;

    }

    public boolean undoDeleteById(Long id) {
        User user = this.findUserDel(id);
        if(user!=null){
            user.setIsActive(true);
            this.userRepo.save(user);
            return true;
        }

        return false;
    }

}
