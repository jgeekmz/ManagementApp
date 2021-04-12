package com.jgeekmz.ManagementApp.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends Person {

    @ManyToOne
    @JoinColumn(name="employeetypeid", insertable=false, updatable=false)
    private EmployeeType employeeType;
    private Integer employeetypeid;
    private String photo;
    private String username;

    @ManyToOne
    @JoinColumn(name="jobtitleid", insertable=false, updatable=false)
    private JobTitle jobTitle;
    private Integer jobtitleid;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String hireDate;

    public Employee () {
        super();
    }

    public Employee(Integer id, String firstname, String lastname, String othername, String title, String initials, String gender, String maritalStatus, Country country, Integer countryid, State state, Integer stateid, String dateOfBirth, String city, String address, String phone, String mobile, String email, String photo, EmployeeType employeeType, Integer employeetypeid, String photo1, String username, JobTitle jobTitle, Integer jobtitleid, String hireDate) {
        //super(id, firstname, lastname, othername, title, initials, gender, maritalStatus, country, countryid, state, stateid, dateOfBirth, city, address, phone, mobile, email, photo);
        super();
        this.employeeType = employeeType;
        this.employeetypeid = employeetypeid;
        this.photo = photo1;
        this.username = username;
        this.jobTitle = jobTitle;
        this.jobtitleid = jobtitleid;
        this.hireDate = hireDate;
        this.firstname=firstname;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeType=" + employeeType +
                ", employeetypeid=" + employeetypeid +
                ", photo='" + photo + '\'' +
                ", username='" + username + '\'' +
                ", jobTitle=" + jobTitle +
                ", jobtitleid=" + jobtitleid +
                ", hireDate='" + hireDate + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country=" + country +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
    public Integer getEmployeetypeid() {
        return employeetypeid;
    }
    public void setEmployeetypeid(Integer employeetypeid) {
        this.employeetypeid = employeetypeid;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public JobTitle getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
    public Integer getJobtitleId() {
        return jobtitleid;
    }
    public void setJobtitleId(Integer jobtitleid) {
        this.jobtitleid = jobtitleid;
    }
    public String getHireDate() {
        return hireDate;
    }
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}