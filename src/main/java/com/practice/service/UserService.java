package com.practice.service;

import com.practice.Entity.User;

import javax.naming.AuthenticationException;
import java.util.List;

public interface UserService {
List<User> findAllUsers();
  List<User> findAllUsersJpql();
  User save(User user);

  User findUser(Long id);
User findUserDel(Long id);
  User updateUser(User user);

  List<User> findAllUsersNative();

  User login(String userName, String password) throws AuthenticationException;

   boolean deleteUserById(Long id);

  boolean undoDeleteById(Long id);
}
