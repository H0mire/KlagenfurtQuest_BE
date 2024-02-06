package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="question")
public class Question {
    @EmbeddedId
    private QuestionId questionId;
    private String question;
}

@Embeddable
class QuestionId implements Serializable {
    private long questionNr;
    private long stationId;
}

