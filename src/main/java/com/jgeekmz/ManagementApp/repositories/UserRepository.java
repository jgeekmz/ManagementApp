package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByPassword(String password);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    User findById(String id);

    Boolean findByEnabled(String username);

    @Query(value = "select * from user e where e.enabled = false", nativeQuery = true)
    List<User> findByEnabledFalse(Boolean enabled);

    @Modifying
    @Query(value="update User u set u.enabled = :enabled where u.id = :id")
    void activateUser (@Param(value = "id") Integer id, @Param(value = "enabled") Boolean enabled);


}