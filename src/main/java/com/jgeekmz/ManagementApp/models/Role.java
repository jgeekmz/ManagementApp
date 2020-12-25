package com.jgeekmz.ManagementApp.models;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Role(String name, Integer id) {
        this.name = name;
        this.id=id;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return name.toUpperCase(Locale.ROOT).toString();
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

}