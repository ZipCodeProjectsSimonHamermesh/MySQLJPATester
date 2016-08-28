package com.example.controllers;

import com.example.models.Game;
import com.example.models.GameRepository;
import com.example.models.User;
import com.example.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@RestController
public class GameController {


    @Autowired
    private GameRepository gameRepository;

    /**** CREATE ****/
    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("games/create")
    @ResponseBody
    public String create(String name) {
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




    /**** READ ****/


    @RequestMapping("games/get-by-name")
    @ResponseBody
    public String getByEmail(String name) {
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

    @RequestMapping("/games")
    @ResponseBody
    public String getAllUsers(){

        String retStr = "";
        Iterable<Game> allGames = gameRepository.findAll();
        for(Game g: allGames){
            retStr += g.toString();
        }
        return retStr;
    }



    /**** UPDATE ****/
    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("games/update")
    @ResponseBody
    public String updateGame(long id, String name) {
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


    /**** DELETE ****/
    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("games/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            gameRepository.delete(id);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

}
