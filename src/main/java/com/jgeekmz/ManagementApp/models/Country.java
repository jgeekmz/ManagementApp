package com.jgeekmz.ManagementApp.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "country")
public class Country extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String capital;
    @Column(name = "countryName")
    private String countryName;
    private String nationality;
    private String continent;

    @OneToMany(mappedBy = "country")
    private List<State> states;

    public Country() {
    }

    public Country(Integer id, String code, String capital, String countryName, String nationality, String continent, List<State> states) {
        this.id = id;
        this.code = code;
        this.capital = capital;
        this.countryName = countryName;
        this.nationality = nationality;
        this.continent = continent;
        this.states = states;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", capital='" + capital + '\'' +
                ", countryName='" + countryName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", continent='" + continent + '\'' +
                ", states=" + states +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
