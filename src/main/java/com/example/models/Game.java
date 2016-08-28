package com.example.models;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */


@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "games")
    List<User> users;

    public Game() {
    }

    public Game(String name){
        this.name = name;
    }

    public Game(long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString(){
        return "Game name:" + this.name + " Game Id: " + this.id + " Players: " + this.users.toString();
    }
}
