package com.muewie.KlagenfurtQuestBackend.repositories;

import com.muewie.KlagenfurtQuestBackend.models.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    Optional<Participant> findByNickname(String firstname);

    Optional<Participant> findByNicknameAndRoom_RoomNr(String nickname, int roomNr);
}

