package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.AuthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO){

        return authDTO.getUsername() + "logged in";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthDTO authDTO){
        return new ResponseEntity<String>(authDTO.getUsername() + "signed up", HttpStatus.CREATED);
    }
}
