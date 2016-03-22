package com.example.controllers;

import com.example.models.User;
import com.example.models.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        @RequestMapping("/create")
        @ResponseBody
        public String create(String email, String name) {
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


    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
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

    @RequestMapping("/get-by-id")
    @ResponseBody
    public String getById(Long id) {
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

    @RequestMapping("/users")
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
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
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
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
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
