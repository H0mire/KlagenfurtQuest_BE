package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.models.Room;
import com.muewie.KlagenfurtQuestBackend.models.Participant;
import com.muewie.KlagenfurtQuestBackend.services.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

//The WebSocketController is responsible for handling requests to the websocket endpoint
@Controller
public class WebSocketController {
    @Autowired
    private RoomServiceImpl roomService;

    //when a participant sends a location, the teacher will be notified
    @MessageMapping("/room/{roomNr}/location")
    @SendTo("/topic/room/{roomNr}")
    public String sendLocation(@DestinationVariable int roomNr, String message) {

        return message;
    }

    //For now the joining functionality is applied directly by connecting to the socket server
    @MessageMapping("/room/{roomNr}/join")
    @SendTo("/topic/room/{roomNr}/joined")
    public String sendJoinMessage(@DestinationVariable int roomNr, String message) {
        String nickname = message;
        roomService.addParticipant(nickname, roomNr);
        return message;
    }

    //when teacher starts the room, the participants will be notified
    @MessageMapping("/room/{roomNr}/start")
    @SendTo("/topic/room/{roomNr}/started")
    public String sendStartPing(@DestinationVariable int roomNr, String message) {
        return message;
    }

    //when participant finishes the pool, the teacher and the other finished participants will be notified
    @MessageMapping("/room/{roomNr}/finishedPool")
    @SendTo("/topic/room/{roomNr}/finishedPool")
    public String sendStopPing(@DestinationVariable int roomNr, String message) {
        String nickname = message;
        roomService.setParticipantState(nickname, roomNr, "finished");
        return message;
    }
}

