package com.muewie.KlagenfurtQuestBackend.services;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import com.muewie.KlagenfurtQuestBackend.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO room);
    RoomDTO updateRoom(Long id, RoomDTO roomDetails);
    void deleteRoom(Long id);
    List<RoomDTO> getAllRooms();
    RoomDTO getRoomById(Long id);

    RoomDTO getRoomByRoomNr(long rmnr);
}

