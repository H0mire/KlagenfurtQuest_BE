package com.muewie.KlagenfurtQuestBackend.models;

import com.muewie.KlagenfurtQuestBackend.DTO.StationDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stationId;
    private String stationName;
    private String stationDescription;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] stationImage;
    private int stationNr;
    @ManyToOne
    @JoinColumn(name="tourId")
    private Tour tour;
    private String stationLocation;

    //Next Station with annotation
    @OneToOne
    @JoinColumn(name="nextStationId")
    private Station nextStation;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public Station getNextStation() {
        return nextStation;
    }

    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
    }

    public StationDTO toDTO(){
        StationDTO stationDTO = new StationDTO();
        stationDTO.setStationId(this.stationId);
        stationDTO.setStationName(this.stationName);
        stationDTO.setStationDescription(this.stationDescription);
        stationDTO.setStationImage(this.stationImage);
        stationDTO.setStationNr(this.stationNr);
        stationDTO.setTourId(this.tour.getTourId());
        stationDTO.setStationLocation(this.stationLocation);
        stationDTO.setNextStation(this.nextStation.toDTO());
        stationDTO.setQuestions(this.questions.stream().map(Question::toDTO).collect(Collectors.toList()));
        return stationDTO;
    }
}
