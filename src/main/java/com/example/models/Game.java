package com.example.models;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "games", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Move> moves;

    @OneToOne
    @JoinColumn(name = "parent_game_id", nullable = true)
    private Game parentGame;

    public Game() {
    }

    public Game(String name){
        this.name = name;
    }

    public Game(String name, Game parentGame) {
        this.name = name;
        this.parentGame = parentGame;
    }

    public Game(String name, List<User> users, List<Move> moves, Game parentGame) {
        this.name = name;
        this.users = users;
        this.moves = moves;
        this.parentGame = parentGame;
    }

    public Game(long id, String name, List<User> users, List<Move> moves, Game parentGame) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.moves = moves;
        this.parentGame = parentGame;
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

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Game getParentGame() {
        return parentGame;
    }

    public void setParentGame(Game parentGame) {
        this.parentGame = parentGame;
    }

    @Override
    public String toString(){
        return "Game name:" + this.name + " Game Id: " + this.id + " Players: " + this.users.toString();
    }
}
