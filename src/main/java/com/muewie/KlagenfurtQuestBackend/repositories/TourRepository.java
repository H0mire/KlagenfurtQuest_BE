package com.muewie.KlagenfurtQuestBackend.repositories;

import com.muewie.KlagenfurtQuestBackend.models.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends CrudRepository<Tour, Long> {
    Optional<Tour> findByTourId(long id);

}
