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

@Controller
public class WebSocketController {
    @Autowired
    private RoomServiceImpl roomService;

    @MessageMapping("/room/{roomNr}/location")
    @SendTo("/topic/room/{roomNr}")
    public String sendLocation(@DestinationVariable int roomNr, String message) {

        return message;
    }

    @MessageMapping("/room/{roomNr}/join")
    @SendTo("/topic/room/{roomNr}/joined")
    public String sendJoinMessage(@DestinationVariable int roomNr, String message) {
        String nickname = message;
        roomService.addParticipant(nickname, roomNr);
        return message;
    }

    @MessageMapping("/room/{roomNr}/start")
    @SendTo("/topic/room/{roomNr}/started")
    public String sendStartPing(@DestinationVariable int roomNr, String message) {
        return message;
    }

    @MessageMapping("/room/{roomNr}/finishedPool")
    @SendTo("/topic/room/{roomNr}/finishedPool")
    public String sendStopPing(@DestinationVariable int roomNr, String message) {
        String nickname = message;
        roomService.setParticipantState(nickname, roomNr, "finished");
        return message;
    }
}

