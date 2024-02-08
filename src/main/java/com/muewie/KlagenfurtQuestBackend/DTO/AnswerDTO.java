package com.muewie.KlagenfurtQuestBackend.DTO;

import com.muewie.KlagenfurtQuestBackend.models.Question;
import jakarta.persistence.*;

public class AnswerDTO {

    private long answerId;
    private int answerNr;
    private long questionId;

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

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
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
}
