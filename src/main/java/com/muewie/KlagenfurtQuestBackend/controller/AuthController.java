package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.AuthDTO;
import com.muewie.KlagenfurtQuestBackend.DTO.RegisterDTO;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import com.muewie.KlagenfurtQuestBackend.services.CustomUserDetailsService;
import com.muewie.KlagenfurtQuestBackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.muewie.KlagenfurtQuestBackend.models.Teacher;

import java.util.Date;

//The AuthController is responsible for handling requests to the /login and /signup endpoint
@RestController
public class AuthController {

    @Autowired
    private TeacherRepository tRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    //The login method is responsible for handling POST requests to the /login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO authDTO) throws Exception {
        try{
            authenticate(authDTO); //authenticate the user
        } catch (Exception e) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername()); //generate Token so user can log in

        return ResponseEntity.ok(jwt);
    }


    //The signup method is responsible for handling POST requests to the /signup endpoint
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
        t.setHashedPassword(passwordEncoder.encode(hashedPassword));
        t.setUsername(username);

        tRepo.save(t); //save the teacher to the database

        final String jwt = jwtUtil.generateToken(t.getUsername()); //generate Token for the user

        return new ResponseEntity<String>(jwt, HttpStatus.CREATED);
    }
    //Authenticate by using the authenticationManager
    private void authenticate(AuthDTO authDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getHashedPassword())
        );
    }
}
