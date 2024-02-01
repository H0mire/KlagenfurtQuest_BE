package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.AuthDTO;
import com.muewie.KlagenfurtQuestBackend.DTO.RegisterDTO;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.muewie.KlagenfurtQuestBackend.models.Teacher;

@RestController
public class AuthController {

    @Autowired
    private TeacherRepository tRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO authDTO) {
        String mail = authDTO.getMail();
        String username = authDTO.getUsername();
        Teacher t;

        if (mail != null) {
            t = tRepo.findByMail(mail);
            if (t == null) {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
            username = t.getUsername();
        } else if (username != null) {
            t = tRepo.findByUsername(username);
            if (t == null) {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        String hashedPassword = authDTO.getHashedPassword();
        String hashedPasswordSaved = t.getHashedPassword();

        //check if password provided
        if(hashedPassword == null){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        if (!hashedPassword.equals(hashedPasswordSaved)) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(username + " successfully logged in", HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterDTO registerDTO){
        String firstname;
        String lastname;
        String username;
        String mail;
        String hashedPassword;

        firstname = registerDTO.getFirstname();
        lastname = registerDTO.getLastname();
        username = registerDTO.getUsername();
        mail = registerDTO.getMail();
        hashedPassword = registerDTO.getHashedPassword();

        //require mandatory parameters:
        if(firstname == null || lastname == null || username == null || mail == null || hashedPassword == null){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        Teacher t = new Teacher();
        t.setFirstname(firstname);
        t.setLastname(lastname);
        t.setMail(mail);
        t.setHashedPassword(hashedPassword);
        t.setUsername(username);

        tRepo.save(t);

        return new ResponseEntity<String>(username + "signed up", HttpStatus.CREATED);
    }
}
