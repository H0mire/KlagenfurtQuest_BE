package com.muewie.KlagenfurtQuestBackend.DTO;

import com.muewie.KlagenfurtQuestBackend.models.Station;
import com.muewie.KlagenfurtQuestBackend.models.Tour;
import jakarta.persistence.*;

import java.util.List;

public class StationDTO {
    private long stationId;
    private String stationName;
    private String stationDescription;

    private byte[] stationImage;
    private int stationNr;
    private long tourId;
    private String stationLocation; // lon, lat
    private StationDTO nextStation;

    private List<QuestionDTO> questions;

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public byte[] getStationImage() {
        return stationImage;
    }

    public void setStationImage(byte[] stationImage) {
        this.stationImage = stationImage;
    }

    public int getStationNr() {
        return stationNr;
    }

    public void setStationNr(int stationNr) {
        this.stationNr = stationNr;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public StationDTO getNextStation() {
        return nextStation;
    }

    public void setNextStation(StationDTO nextStation) {
        this.nextStation = nextStation;
    }
}
