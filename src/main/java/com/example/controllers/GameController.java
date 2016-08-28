package com.example.controllers;

import com.example.models.Game;
import com.example.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/games", method = RequestMethod.GET)
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
    @RequestMapping(value = "games/update", method = RequestMethod.GET)
    @ResponseBody
    public String updateGame(@RequestParam long id, @RequestParam String name) {
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
