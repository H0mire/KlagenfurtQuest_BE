package com.muewie.KlagenfurtQuestBackend.services;
import com.muewie.KlagenfurtQuestBackend.DTO.TourDTO;

public interface TourService {

    public TourDTO getTourById(Long id);

    public TourDTO getTourByName(String name);

}
