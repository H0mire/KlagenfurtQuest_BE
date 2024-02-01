package com.muewie.KlagenfurtQuestBackend.repositories;

import com.muewie.KlagenfurtQuestBackend.models.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByFirstname(String firstname);

    Teacher findByMail(String mail);

    Teacher findByUsername(String username);
}
