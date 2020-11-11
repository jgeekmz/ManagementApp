package com.jgeekmz.ManagementApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
@Data
@Table(name="user")
//NoArgsConstructor
//@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User<userRole> {

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

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name="roles")
    private String roles;

    @NonNull
    public User() {
    }

    public User(int id, @NotEmpty(message = "Firstname should not be empty") String firstname, String lastname, String username, String password, String email, boolean enabled, boolean banned, String confirmationToken, String userRole) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.banned = banned;
        this.confirmationToken = confirmationToken;
        this.roles = roles;
    }

    @Override
    public String toString() {
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
                ", roles='" + roles + '\'' +
                '}';
    }

    //Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getRoles() { return roles; }

    public void setRoles(String roles) { this.roles = roles; }

}
