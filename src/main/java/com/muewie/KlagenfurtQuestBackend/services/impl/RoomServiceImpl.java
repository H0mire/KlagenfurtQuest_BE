package com.muewie.KlagenfurtQuestBackend.services.impl;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import com.muewie.KlagenfurtQuestBackend.models.Room;
import com.muewie.KlagenfurtQuestBackend.repositories.RoomRepository;
import com.muewie.KlagenfurtQuestBackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    //autowire the RoomRepository
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        //create Room
        Room r = new Room();

        //set available fields
        r.setTeacherId(roomDTO.getTeacherId());
        r.setTourId(roomDTO.getTourId());

        //generate roomNr with 4 digits
        int roomNr = (int) (Math.random() * 9000) + 1000;
        //check if roomNr is already in use
        while (roomRepository.existsByRoomNr(roomNr)) {
            roomNr = (int) (Math.random() * 9000) + 1000;
        }
        //set roomNr
        roomDTO.setRoomNr(roomNr);
        r.setRoomNr(roomNr);
        //save room
        roomRepository.save(r);

        return roomDTO;
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDetails) {
        //check if room exists
        Optional<Room> op = roomRepository.findById(id);
        if (op.isEmpty()) {
            return null;
        }
        //get room
        Room r = op.get();

        //update room
        r.setRoomNr(roomDetails.getRoomNr());
        r.setTourId(roomDetails.getTourId());
        r.setTeacherId(roomDetails.getTeacherId());

        //save room
        roomRepository.save(r);
        return roomDetails;
    }

    @Override
    public void deleteRoom(Long id) {

    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return null;
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        //check if room exists
        Optional<Room> op = roomRepository.findById(id);
        if (op.isEmpty()) {
            return null;
        }
        //get room
        Room r = op.get();
        //create RoomDTO
        RoomDTO roomDTO = new RoomDTO();
        //set available fields
        roomDTO.setRoomId(r.getRoomId());
        roomDTO.setRoomNr(r.getRoomNr());
        roomDTO.setTourId(r.getTourId());
        roomDTO.setTeacherId(r.getTeacherId());

        return roomDTO;
    }

    @Override
    public RoomDTO getRoomByRoomNr(long rmnr) {
        //check if room exists
        Optional<Room> op = roomRepository.findByRoomNr(rmnr);
        if (op.isEmpty()) {
            return null;
        }
        //get room
        Room r = op.get();
        //create RoomDTO
        RoomDTO roomDTO = new RoomDTO();
        //set available fields
        roomDTO.setRoomId(r.getRoomId());
        roomDTO.setRoomNr(r.getRoomNr());
        roomDTO.setTourId(r.getTourId());
        roomDTO.setTeacherId(r.getTeacherId());

        return roomDTO;
    }
}
