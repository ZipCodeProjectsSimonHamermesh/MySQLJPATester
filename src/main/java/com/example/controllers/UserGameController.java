package com.example.controllers;

import com.example.models.Game;
import com.example.models.GameRepository;
import com.example.models.User;
import com.example.models.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@RestController
public class UserGameController {

    private Logger logger = LoggerFactory.getLogger(UserGameController.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;


    /** THIS DOES NOT WORK **/
    @RequestMapping(value = "/addUserToGame/{gameId}/{userId}", method = RequestMethod.GET)
    public String addUserToGame(@PathVariable long gameId, @PathVariable long userId){
        Game game;
        User user;
        try{
            game = gameRepository.findOne(gameId);
            logger.error(game.toString());
            user = userRepository.findById(userId);
            logger.error(user.toString());

            logger.error(game.getUsers().toString());
            game.getUsers().add(user);
            logger.error(game.getUsers().toString());
            /** THIS DOES NOT WORK - THE .save() ONLY WORKS FOR THE PARENT **/
            gameRepository.save(game);
            logger.error(game.getUsers().toString());
        }catch(Exception e){
            return e.toString();
        }

        return game.getUsers().toString();
    }

    @RequestMapping(value = "/addGameToUser/{userId}/{gameId}", method = RequestMethod.GET)
    public String addGameToUser(@PathVariable long gameId, @PathVariable long userId){
        User user;
        Game game;
        try{
            user = userRepository.findOne(userId);
            logger.info(user.toString());
            game = gameRepository.findOne(gameId);
            logger.info(game.toString());

            logger.info(user.getGames().toString());
            user.getGames().add(game);
            logger.info(user.getGames().toString());
            userRepository.save(user);
            logger.info(user.getGames().toString());
        }catch(Exception e){
            return e.toString();
        }

        return user.getGames().toString();
    }
}
