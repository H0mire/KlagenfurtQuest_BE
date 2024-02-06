package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name="tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tourId;

    private String tourName;
    private String tourDescription;
    private String tourImage;

    //foreign key to first station
    private long firstStationId;

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getTourImage() {
        return tourImage;
    }

    public void setTourImage(String tourImage) {
        this.tourImage = tourImage;
    }

    public long getFirstStationId() {
        return firstStationId;
    }

    public void setFirstStationId(int firstStationId) {
        this.firstStationId = firstStationId;
    }
}
