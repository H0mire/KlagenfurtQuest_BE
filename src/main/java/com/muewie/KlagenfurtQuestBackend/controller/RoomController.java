package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import com.muewie.KlagenfurtQuestBackend.repositories.RoomRepository;
import com.muewie.KlagenfurtQuestBackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.muewie.KlagenfurtQuestBackend.models.Room;

@RestController
public class RoomController{

    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO roomDTO) {

        RoomDTO room = roomService.createRoom(roomDTO);

        return new ResponseEntity<String>("Raum mit " + roomDTO.getRoomNr() +" erstellt!", HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomDTO> getRoomByRoomNr(@RequestParam("rmnr") long rmnr){
        RoomDTO r = roomService.getRoomByRoomNr((long) rmnr);
        if(r == null) {
            return new ResponseEntity<RoomDTO>(r, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RoomDTO>(r, HttpStatus.OK);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") long id){
        RoomDTO r = roomService.getRoomById((long) id);

        if(r == null) {
            return new ResponseEntity<RoomDTO>(r, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RoomDTO>(r, HttpStatus.OK);
    }
    @PostMapping("/joinroom")
    public ResponseEntity<String> joinRoom(@RequestParam("rmnr") int rmnr, @RequestParam("nickname") String nickname ){
        return new ResponseEntity<String>(nickname + " joined room "+rmnr+" SocketServer: 192.168.0.1:3000",HttpStatus.OK);
    }

}
