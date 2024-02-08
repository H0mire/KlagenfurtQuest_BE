package com.muewie.KlagenfurtQuestBackend.repositories;

import com.muewie.KlagenfurtQuestBackend.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
