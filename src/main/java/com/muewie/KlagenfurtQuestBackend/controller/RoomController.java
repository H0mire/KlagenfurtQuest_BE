package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import com.muewie.KlagenfurtQuestBackend.models.Teacher;
import com.muewie.KlagenfurtQuestBackend.repositories.RoomRepository;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import com.muewie.KlagenfurtQuestBackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.muewie.KlagenfurtQuestBackend.models.Room;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

//The RoomController is responsible for handling requests to the /rooms endpoint
@RestController
public class RoomController{

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private RoomService roomService;

    //when a teacher creates a room, the room will be created and the roomNr will be returned
    @PostMapping("/rooms")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO roomDTO, Principal principal){

        //get Teacher by Principal
        Teacher t = teacherRepository.findByUsername(principal.getName()).orElseThrow();
        roomDTO.setTeacherId(t.getId());
        RoomDTO room = roomService.createRoom(roomDTO);
        return new ResponseEntity<String>("Raum mit " + roomDTO.getRoomNr() +" erstellt!", HttpStatus.CREATED);
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomByRoomNr(@RequestParam(value="rmnr", required = false) Long rmnr){
        if (rmnr == null) {
            List<RoomDTO> r = roomService.getAllRooms();
            return new ResponseEntity<List<RoomDTO>>(r, HttpStatus.OK);
        }
        RoomDTO r = roomService.getRoomByRoomNr((long) rmnr);
        if(r == null) {
            return new ResponseEntity<List<RoomDTO>>(HttpStatus.NOT_FOUND);
        }

        List<RoomDTO> rooms = Arrays.asList(r);

        return new ResponseEntity<List<RoomDTO>>(rooms,HttpStatus.OK);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") long id){
        RoomDTO r = roomService.getRoomById((long) id);

        if(r == null) {
            return new ResponseEntity<RoomDTO>(r, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RoomDTO>(r, HttpStatus.OK);
    }
    //This is an optional method to join a room. It will be a requirement in the future, if there are multiple socket servers.
    //It returns the socket server and the tourID and teacherID of the room
    @GetMapping("/joinroom")
    public ResponseEntity<String> joinRoom(@RequestParam("rmnr") int rmnr, @RequestParam("nickname") String nickname ){
        //get tourID of room
        RoomDTO r = roomService.getRoomByRoomNr(rmnr);
        long tourID = r.getTourId();
        long teacherID = r.getTeacherId();

        return new ResponseEntity<String>(nickname + " joined room "+rmnr+" SocketServer: 192.168.0.1:3000 TourID: " +tourID + " TeacherID: " + teacherID,HttpStatus.OK);
    }

}
