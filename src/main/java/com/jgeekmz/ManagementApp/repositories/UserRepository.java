package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    <Optional> User findByUsername(String username);

    User findByPassword(String password);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    User findById(String id);

    Boolean findByEnabled(String username);

    @Query(value = "select * from user e where e.enabled = false", nativeQuery = true)
    List<User> findByEnabledFalse(Boolean enabled);

    final String countByID = "SELECT COUNT(ra) FROM User ra WHERE ra.enabled=false";

    @Query(value = countByID)
    Integer countByID(Long id);

    @Modifying
    @Query(value = "update User u set u.enabled = :enabled where u.id = :id")
    void activateUser(@Param(value = "id") Integer id, @Param(value = "enabled") Boolean enabled);

    //Reset Password by email
    User findByResetPasswordToken(String token);

    List<User> findByFirstnameAndLastname(String firstname, String lastname);

    @Query(value = "SELECT e.username FROM User e WHERE e.firstname = ?1 AND e.lastname = ?2", nativeQuery = true)
    String findUsernameByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);

}