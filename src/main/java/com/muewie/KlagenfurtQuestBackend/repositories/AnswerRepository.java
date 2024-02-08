package com.muewie.KlagenfurtQuestBackend.repositories;

import com.muewie.KlagenfurtQuestBackend.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
