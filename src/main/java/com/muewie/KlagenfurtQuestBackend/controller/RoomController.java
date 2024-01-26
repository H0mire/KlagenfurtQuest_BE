package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController{

    @PostMapping("/rooms")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO room) {
        return new ResponseEntity<String>("Raum mit " + room.getRoomNr() +" erstellt!", HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomDTO> getRoom(@RequestParam("rmnr") int rmnr){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomNr(rmnr);
        roomDTO.setTeacherId(1);
        roomDTO.setTourId(1);
        roomDTO.setRoomId(1);

        return new ResponseEntity<RoomDTO>(roomDTO, HttpStatus.OK);
    }
    @PostMapping("/joinroom")
    public ResponseEntity<String> joinRoom(@RequestParam("rmnr") int rmnr, @RequestParam("nickname") String nickname ){
        return new ResponseEntity<String>(nickname + "joined room "+rmnr+" SocketServer: 192.168.0.1:3000",HttpStatus.OK);
    }

}
