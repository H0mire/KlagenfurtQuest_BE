package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="answer")
public class Answer {
    @EmbeddedId
    private AnswerId answerId;
    private String answer;
    private boolean isCorrect;
}

@Embeddable
class AnswerId implements Serializable {
    private long questionNr;
    private long answerNr;

}

