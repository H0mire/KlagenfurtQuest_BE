package com.muewie.KlagenfurtQuestBackend.services.impl;

import com.muewie.KlagenfurtQuestBackend.DTO.TourDTO;
import com.muewie.KlagenfurtQuestBackend.models.Tour;
import com.muewie.KlagenfurtQuestBackend.repositories.TourRepository;
import com.muewie.KlagenfurtQuestBackend.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public TourDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow();
        return tour.toDTO();
    }

    @Override
    public TourDTO getTourByName(String name) {
        Tour tour = tourRepository.findByTourName(name).orElseThrow();
        return tour.toDTO();
    }
}
