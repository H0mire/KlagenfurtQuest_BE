package com.muewie.KlagenfurtQuestBackend.DTO;

import java.util.List;

public class TourDTO {

    private long tourId;
    private String tourName;
    private String tourDescription;
    private byte[] tourImage;

    private StationDTO firstStation;
    private List<StationDTO> stations;

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

    public StationDTO getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(StationDTO firstStation) {
        this.firstStation = firstStation;
    }

    public List<StationDTO> getStations() {
        return stations;
    }

    public void setStations(List<StationDTO> stations) {
        this.stations = stations;
    }
}
