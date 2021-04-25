package com.jgeekmz.ManagementApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jgeekmz.ManagementApp.controllers.LoginController;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Transient;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Entity
@ComponentScan(basePackages = { "com.jgeekmz.ManagementApp.models" })
@Data
@Table(name="user")
//NoArgsConstructor
//@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Please provide your firstname")
    @Size(min = 3, message = "Ops! Min symbols 3!")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Please provide your lastname")
    @Size(min = 4, max = 16, message = "Username should be between 4 and 16 symbols")
    private String lastname;

    @NotBlank(message = "Name is mandatory")
    private String username;

    @Transient
    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;

    @Column(name = "banned")
    @JsonIgnore
    private boolean banned;

    private String test;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")})
    private Collection<Role> roles;

    private Date regDate;

    @NonNull
    public User() { }

    public User(int id, @NotEmpty(message = "Please provide your firstname") @Size(min = 3, message = "Ops! Min symbols 3!") String firstname, @NotEmpty(message = "Please provide your lastname") @Size(min = 4, max = 16, message = "Username should be between 4 and 16 symbols") String lastname, @NotBlank(message = "Name is mandatory") String username, String password, @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email, boolean enabled, boolean banned, String confirmationToken, String resetPasswordToken, List<Role> roles, Date regDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.banned = banned;
        this.confirmationToken = confirmationToken;
        this.resetPasswordToken = resetPasswordToken;
        this.roles =  roles;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        String output = roles.toString().replace("[","");

        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", banned=" + banned +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", resetPasswordToken='" + resetPasswordToken + '\'' +
                ", roles=" + roles.toString().replace("[","") +
                ", regDate=" + regDate +
                '}';
    }

    //Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled() { this.enabled = enabled; }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public boolean isPresent() { return true; }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }

}
