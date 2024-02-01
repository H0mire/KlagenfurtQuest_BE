package com.muewie.KlagenfurtQuestBackend;

import com.muewie.KlagenfurtQuestBackend.models.Teacher;
import com.muewie.KlagenfurtQuestBackend.repositories.TeacherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KlagenfurtQuestBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(KlagenfurtQuestBackendApplication.class, args);
		Teacher t = new Teacher();
		t.setHashedPassword("asdasd");
		t.setLastname("Mustermann");
		t.setMail("Mustarmail@mail.com");
		t.setFirstname("Max");
		TeacherRepository tRepo = applicationContext.getBean(TeacherRepository.class);
		tRepo.save(t);
	}

}
