package com.example.controllers;

import com.example.models.User;
import com.example.models.UserRepository;


import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by simonhamermesh on 3/7/16.
 */

    @Controller
    public class UserController {

        @Autowired
        private UserRepository userRepository;

        /**** CREATE ****/
        /**
         * GET /create  --> Create a new user and save it in the database.
         */
        @RequestMapping(value = "users/create" , method = RequestMethod.GET)
        @ResponseBody
        public String create(@RequestParam String email, @RequestParam String name) {
            String userId = "";
            try {
                User user = new User(email, name);
                userRepository.save(user);
                userId = String.valueOf(user.getId());
            }
            catch (Exception ex) {
                return "Error creating the user: " + ex.toString();
            }
            return "User succesfully created with id = " + userId;
        }




    /**** READ ****/


    @RequestMapping(value = "users/get-by-email", method = RequestMethod.GET)
    @ResponseBody
    public String getByEmail(@RequestParam String email) {
        String userId = "";
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping(value = "users/get-by-id", method = RequestMethod.GET)
    @ResponseBody
    public String getById(@RequestParam Long id) {
        String userEmail = "";
        try {
            User user = userRepository.findById(id);
            userEmail = String.valueOf(user.getEmail());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userEmail;
    }

    @RequestMapping(value = "users/get-all", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){

        String retStr = "";
        Iterable<User> allUsers = userRepository.findAll();
        for(User u: allUsers){
            retStr += u.toString();
        }
        return retStr;
    }



    /**** UPDATE ****/


    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping(value = "users/update", method = RequestMethod.GET)
    @ResponseBody
    public String updateUser(@RequestParam long id, @RequestParam String email, @RequestParam String name) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }


    /**** DELETE ****/


    /**
    * GET /delete  --> Delete the user having the passed id.
    */
    @RequestMapping(value = "users/delete" , method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam long id) {
        try {
            User user = new User(id);
            userRepository.delete(user);
        }
            catch (Exception ex) {
                return "Error deleting the user:" + ex.toString();
        }
                return "User succesfully deleted!";
    }

  }
