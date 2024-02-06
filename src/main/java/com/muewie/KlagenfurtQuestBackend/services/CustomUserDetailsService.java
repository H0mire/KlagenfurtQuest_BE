package com.muewie.KlagenfurtQuestBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import com.muewie.KlagenfurtQuestBackend.models.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUsername(username);
        if (optionalTeacher.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Teacher teacher = optionalTeacher.get();

        return new User(username, teacher.getHashedPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER")));
    }
}
