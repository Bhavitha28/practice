package com.practice.Repo;

import com.practice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findByUserType(String normal);

    User findByIdAndIsActive(Long id, boolean b);

    @Query("SELECT t FROM User t where is_active=1")
    List<User> find();

            @Query(
            value = "SELECT * FROM User where is_active=1",
            nativeQuery = true)
    List<User> findActive();

    User findByUserNameAndPassword(String username, String password);
}
