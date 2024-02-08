package com.muewie.KlagenfurtQuestBackend.DTO;

import com.muewie.KlagenfurtQuestBackend.models.Room;

public class ParticipantDTO {
    private long participantId;
    private long roomId;
    private String nickname;
    private String state;
    private int rank;
    //how long did the participant need to solve the tour
    private long time;

}
