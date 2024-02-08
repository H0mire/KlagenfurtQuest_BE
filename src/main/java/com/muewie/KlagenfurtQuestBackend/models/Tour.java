package com.muewie.KlagenfurtQuestBackend.models;

import com.muewie.KlagenfurtQuestBackend.DTO.StationDTO;
import com.muewie.KlagenfurtQuestBackend.DTO.TourDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tourId;

    private String tourName;
    private String tourDescription;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] tourImage;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Station> stations;
    @OneToOne
    @JoinColumn(name="firstStationId")
    private Station firstStation;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

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

    public byte[] getTourImage() {
        return tourImage;
    }

    public void setTourImage(byte[] tourImage) {
        this.tourImage = tourImage;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public TourDTO toDTO() {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setTourId(this.tourId);
        tourDTO.setTourName(this.tourName);
        tourDTO.setTourDescription(this.tourDescription);
        tourDTO.setTourImage(this.tourImage);
        tourDTO.setFirstStation(this.firstStation.toDTO());
        List<StationDTO> stationDTOs = new ArrayList<>();
        for (Station station : this.stations) {
            stationDTOs.add(station.toDTO());
        }
        tourDTO.setStations(stationDTOs);
        return tourDTO;
    }
}
