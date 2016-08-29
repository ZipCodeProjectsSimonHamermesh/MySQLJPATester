package com.example.controllers;

import com.example.models.Game;
import com.example.models.Move;
import com.example.models.User;
import com.example.repositories.GameRepository;
import com.example.repositories.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@RestController
public class GameController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MoveRepository moveRepository;

    /**** CREATE ****/
    @RequestMapping(value = "games/create", method = RequestMethod.GET)
    @ResponseBody
    public String create(@RequestParam String name) {
        String gameName = "";
        try {
            Game game = new Game(name);
            gameRepository.save(game);
            gameName=game.getName();
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Game succesfully created with name = " + gameName;
    }

    @RequestMapping(value = "games/newChildGame", method = RequestMethod.GET)
    public String createNewChildGame(@RequestParam long gameId, @RequestParam String gameName){

        try{

        Game childGame = new Game(gameName);
        List<Move> moves = new ArrayList<>();
        List<User> users = new ArrayList<>();
        gameRepository.save(childGame);

        Game parentGame = gameRepository.findById(gameId);

        for(Move m: parentGame.getMoves()){
            Move newMove = new Move(m.getMoveNumber(),m.getGameState(),parentGame);
            moveRepository.save(newMove);
            moves.add(m);
        }
        for(User u: parentGame.getUsers()){
            users.add(u);
        }

        childGame.setUsers(users);
        childGame.setMoves(moves);
        childGame.setParentGame(parentGame);
        gameRepository.save(childGame);

        }catch (Exception e) {
        return e.toString();
        }

        return "success?";
    }

    /**** READ ****/
    @RequestMapping(value = "games/get-by-name", method = RequestMethod.GET)
    @ResponseBody
    public String getByEmail(@RequestParam String name) {
        long gameId;
        try {
           Game game = gameRepository.findByName(name);
           gameId=game.getId();
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The game ID is: " + gameId;
    }

    @RequestMapping(value = "games/getAllGames", method = RequestMethod.GET)
    @ResponseBody
    public String getAllGames(){
        return gameRepository.findAll().toString();
    }



    /**** UPDATE ****/
    @RequestMapping(value = "games/updateGameName", method = RequestMethod.GET)
    @ResponseBody
    public String updateGameName(@RequestParam long id, @RequestParam String name) {
        try {
            Game game = gameRepository.findOne(id);
            game.setName(name);
            gameRepository.save(game);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "Game succesfully updated!";
    }

    @RequestMapping(value = "games/setParentGame", method = RequestMethod.GET)
    public String setParentGame(@RequestParam long childGameId, @RequestParam long parentGameId){
        Game childGame;
        Game parentGame;
        try{
            childGame = gameRepository.findById(childGameId);
            parentGame = gameRepository.findById(parentGameId);
            childGame.setParentGame(parentGame);
            gameRepository.save(childGame);
        }catch (Exception e){
            return e.toString();}
        return "Success";
    }

    /** This only works because the Move is saved before the Game is saved. **/
    @RequestMapping(value = "games/addNewMoveToGame", method = RequestMethod.GET)
    @ResponseBody
    public String addNewMoveToGame(@RequestParam long gameId, @RequestParam String moveNumber, @RequestParam String gameState) {
        Move move;
        Game game;
        try {
            game = gameRepository.findOne(gameId);
            move = new Move(moveNumber,gameState,game);
            moveRepository.save(move);
            game.getMoves().add(move);
            gameRepository.save(game);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "Game succesfully updated!" + game.getMoves().toString();
    }


    /**** DELETE ****/
    @RequestMapping(value = "games/delete" , method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam long id) {
        try {
            gameRepository.delete(id);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

}
