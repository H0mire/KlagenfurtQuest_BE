package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.*;


//Participant is a user that is in a room and is playing the tour
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long participantId;

    @ManyToOne
    @JoinColumn(name="roomId")
    private Room room;
    //nickname of the participant he chooses
    private String nickname;
    private String state;
    private int rank;
    //how long did the participant need to solve the tour
    private long time;

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
