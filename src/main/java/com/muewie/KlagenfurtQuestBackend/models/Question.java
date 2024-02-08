package com.muewie.KlagenfurtQuestBackend.models;

import com.muewie.KlagenfurtQuestBackend.DTO.AnswerDTO;
import com.muewie.KlagenfurtQuestBackend.DTO.QuestionDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    private int questionNr;

    @ManyToOne
    @JoinColumn(name="stationId")
    private Station station;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

    private String content;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getQuestionNr() {
        return questionNr;
    }

    public void setQuestionNr(int questionNr) {
        this.questionNr = questionNr;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionDTO toDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(this.questionId);
        questionDTO.setQuestionNr(this.questionNr);
        questionDTO.setStationId(this.station.getStationId());
        questionDTO.setContent(this.content);
        List<AnswerDTO> answerDTOs = new ArrayList<>();
        for (Answer answer : this.answers) {
            answerDTOs.add(answer.toDTO());
        }
        questionDTO.setAnswers(answerDTOs);
        return questionDTO;
    }
}
