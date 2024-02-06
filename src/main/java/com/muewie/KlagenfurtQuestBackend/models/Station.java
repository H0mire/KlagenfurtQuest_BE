package com.muewie.KlagenfurtQuestBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name="station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stationId;
    private String stationName;
    private String stationDescription;
    private String stationImage;
    private int stationNr;
    private long tourId;
    private String stationLocation;
    private long nextStationId;

}
