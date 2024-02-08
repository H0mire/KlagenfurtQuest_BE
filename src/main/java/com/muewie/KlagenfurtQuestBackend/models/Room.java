package com.muewie.KlagenfurtQuestBackend.models;

import com.muewie.KlagenfurtQuestBackend.DTO.RoomDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;
    private int roomNr;

    @ManyToOne
    @JoinColumn(name="teacherId")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name="tourId")
    private Tour tour;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participant> participants;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public RoomDTO toDTO(){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(this.roomId);
        roomDTO.setRoomNr(this.roomNr);
        roomDTO.setTeacherId(this.teacher.getId());
        roomDTO.setTourId(this.tour.getTourId());
        return roomDTO;
    }
}
