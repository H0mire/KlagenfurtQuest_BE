package com.muewie.KlagenfurtQuestBackend.models;

import com.muewie.KlagenfurtQuestBackend.DTO.TeacherDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstname;
    private String lastname;

    private String username;
    private String mail;

    private String hashedPassword;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public TeacherDTO toDTO() {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(this.id);
        teacherDTO.setFirstname(this.firstname);
        teacherDTO.setLastname(this.lastname);
        teacherDTO.setUsername(this.username);
        teacherDTO.setMail(this.mail);
        return teacherDTO;
    }
}
