package com.example.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */
@Entity
@Table(name = "moves")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String moveNumber;

    @NotNull
    private String gameState;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    public Move() {
    }

    public Move(long id, String moveNumber, String gameState, Game game) {
        this.id = id;
        this.moveNumber = moveNumber;
        this.gameState = gameState;
        this.game = game;
    }

    public Move(String moveNumber, String gameState, Game game) {
        this.moveNumber = moveNumber;
        this.gameState = gameState;
        this.game = game;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(String moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
