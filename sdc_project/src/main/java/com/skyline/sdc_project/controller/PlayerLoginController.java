package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.config.JwtUtil;
import com.skyline.sdc_project.dto.PlayerDTO;
import com.skyline.sdc_project.dto.ErrorRes;
import com.skyline.sdc_project.dto.LoginReq;
import com.skyline.sdc_project.dto.LoginRes;
import com.skyline.sdc_project.exception.UserNotFoundException;
import com.skyline.sdc_project.service.PlayerService;
import com.skyline.sdc_project.service.PlayerServiceManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/player")
@CrossOrigin
public class PlayerLoginController {
    private final JwtUtil util;
    private final PlayerService service;
    @Autowired
    private PlayerServiceManagement playerServiceManagement;
    public PlayerLoginController(PlayerService service, JwtUtil util){
        this.util=util;
        this.service = service;
        System.out.println("LoginAPI");
    }

    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq req) {
        try {
            PlayerDTO search = service.search(req.getUsername(), req.getPassword());
            String token = util.createToken(search);
            return ResponseEntity.ok(new LoginRes(req.getUsername(), token, search.getType(), search.getId()));
        } catch (UserNotFoundException e) {
            //e.printStackTrace();
            //return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage()));
            PlayerDTO newPlayer = new PlayerDTO();
            newPlayer.setUsername(req.getUsername());
            newPlayer.setPassword(req.getPassword()); // You should ideally hash the password before storing
            newPlayer.setType(new ArrayList<>(Arrays.asList("USER")));
            playerServiceManagement.savePlayer(newPlayer); // Make sure to implement the save method in your service

            // Create token for the new user
            String token = util.createToken(newPlayer);
            return ResponseEntity.ok(new LoginRes(req.getUsername(), token, newPlayer.getType(), newPlayer.getId()));
        }
    }


    @GetMapping("/search")
    public String get(){
        System.out.println("OK");
        return "OK";
    }
    @GetMapping("/player_answers")
    public String view_details(){
        System.out.println("Successfully Send");
        return "Successfully Send";
    }
    @PostMapping("/register")
    public String save_register(){
        System.out.println("Successfully Send");
        return "Successfully Send";

    }


}
