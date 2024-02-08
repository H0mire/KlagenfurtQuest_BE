package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.*;
import com.muewie.KlagenfurtQuestBackend.DTO.AnswerDTO;

import java.io.Serializable;

@Entity
@Table(name="answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answerId;
    private int answerNr;

    @ManyToOne
    @JoinColumn(name="questionId")
    private Question question;

    private String content;
    private boolean isCorrect;

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public AnswerDTO toDTO() {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerId(this.answerId);
        answerDTO.setAnswerNr(this.answerNr);
        answerDTO.setQuestionId(this.question.getQuestionId());
        answerDTO.setContent(this.content);
        answerDTO.setCorrect(this.isCorrect);
        return answerDTO;
    }
}



