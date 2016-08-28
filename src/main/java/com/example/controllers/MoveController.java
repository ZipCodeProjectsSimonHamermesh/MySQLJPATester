package com.example.controllers;

import com.example.models.Game;
import com.example.repositories.GameRepository;
import com.example.models.Move;
import com.example.repositories.MoveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@RestController
class MoveController {

    private Logger logger = LoggerFactory.getLogger(MoveController.class);

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(value = "moves/createMove/{gameId}", method = RequestMethod.GET)
    public String createMove(@RequestParam String moveNumber, @RequestParam String gameState, @PathVariable long gameId){

        Game game = gameRepository.findById(gameId);
        Move move = new Move(moveNumber,gameState,game);
        try{
            moveRepository.save(move);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return move.toString();
    }

    @RequestMapping(value = "moves/getMoveByGameId/{gameId}", method = RequestMethod.GET)
    public String getMoveByGameId(@PathVariable long gameId){
        return moveRepository.findByGameId(gameId).toString();
    }
}
