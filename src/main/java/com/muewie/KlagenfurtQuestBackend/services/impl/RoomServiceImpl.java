package com.muewie.KlagenfurtQuestBackend.services.impl;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import com.muewie.KlagenfurtQuestBackend.models.Participant;
import com.muewie.KlagenfurtQuestBackend.models.Room;
import com.muewie.KlagenfurtQuestBackend.models.Teacher;
import com.muewie.KlagenfurtQuestBackend.models.Tour;
import com.muewie.KlagenfurtQuestBackend.repositories.ParticipantRepository;
import com.muewie.KlagenfurtQuestBackend.repositories.RoomRepository;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import com.muewie.KlagenfurtQuestBackend.repositories.TourRepository;
import com.muewie.KlagenfurtQuestBackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//The RoomServiceImpl is responsible for handling the business logic of the Room entity
@Service
public class RoomServiceImpl implements RoomService {
    //autowire the RoomRepository
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TourRepository tourRepository;


    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        //create Room
        Room r = new Room();

        //get teacher and tour
        Teacher t = teacherRepository.findById(roomDTO.getTeacherId()).orElseThrow();
        Tour tour = tourRepository.findByTourId(roomDTO.getTourId()).orElseThrow();
        //set available fields
        r.setTeacher(t);
        r.setTour(tour);

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

        Teacher t = teacherRepository.findById(roomDetails
                .getTeacherId()).orElseThrow();
        r.setTeacher(t);
        Tour tour = tourRepository.findByTourId(roomDetails.getTourId()).orElseThrow();
        r.setTour(tour);


        //save room
        roomRepository.save(r);
        return roomDetails;
    }

    public void addParticipant(String nickname, int roomNr) {
        //create Participant
        Participant p = new Participant();
        //set available fields
        p.setNickname(nickname);
        Room r = roomRepository.findByRoomNr(roomNr).orElseThrow();
        p.setRoom(r);
        p.setState("ready");
        //save participant
        participantRepository.save(p);
    }

    //For future use
    @Override
    public void deleteRoom(Long id) {

    }

    @Override
    public List<RoomDTO> getAllRooms() {
        //get all rooms
        List<Room> rooms = (List<Room>) roomRepository.findAll();
        //create RoomDTOs
        List<RoomDTO> roomDTOs = new java.util.ArrayList<>();
        //set available fields
        Arrays.stream(rooms.toArray()).forEach(room -> roomDTOs.add(((Room) room).toDTO()));
        return roomDTOs;
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
        roomDTO.setTourId(r.getTour().getTourId());
        roomDTO.setTeacherId(r.getTeacher().getId());

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

        roomDTO.setTourId(r.getTour().getTourId());
        roomDTO.setTeacherId(r.getTeacher().getId());


        return roomDTO;
    }

    @Override
    public void setParticipantState(String nickname, int roomNr, String state) {
        //check if participant exists
        Optional<Participant> op = participantRepository.findByNicknameAndRoom_RoomNr(nickname, roomNr);
        if (op.isEmpty()) {
            return;
        }
        //get participant
        Participant p = op.get();
        //set state
        p.setState(state);
        //save participant
        participantRepository.save(p);
    }
}
