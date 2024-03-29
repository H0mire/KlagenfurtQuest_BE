package com.muewie.KlagenfurtQuestBackend.controller;

import com.muewie.KlagenfurtQuestBackend.DTO.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muewie.KlagenfurtQuestBackend.services.TourService;

//The TourController is responsible for handling requests to the /tours endpoint
@RestController
public class TourController {

    @Autowired
    private TourService tourService;

    //get Tour by id
    @GetMapping("/tours/{id}")
    public TourDTO getTourById(@PathVariable("id") long id) {
        TourDTO tour = tourService.getTourById(id);
        return tour;
    }

    //get all tour or one by name
    @GetMapping("/tours")
    public ResponseEntity<?> getTours(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            TourDTO tour = tourService.getTourByName(name);
            return ResponseEntity.ok(tour); // Oder eine geeignete Darstellung des TourDTO
        } else {
            // Logik, um alle Touren zurückzugeben
            return ResponseEntity.ok("Liste von Touren oder Ansichtsnamen");
        }
    }




}
