package com.muewie.KlagenfurtQuestBackend;

import com.muewie.KlagenfurtQuestBackend.models.Question;
import com.muewie.KlagenfurtQuestBackend.models.Answer;
import com.muewie.KlagenfurtQuestBackend.models.Station;
import com.muewie.KlagenfurtQuestBackend.models.Tour;
import com.muewie.KlagenfurtQuestBackend.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class KlagenfurtQuestBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(KlagenfurtQuestBackendApplication.class, args);
		//save to database
		AnswerRepository aRepo = applicationContext.getBean(AnswerRepository.class);
		QuestionRepository qRepo = applicationContext.getBean(QuestionRepository.class);
		StationRepository sRepo = applicationContext.getBean(StationRepository.class);
		TourRepository tRepo = applicationContext.getBean(TourRepository.class);

		Tour t = new Tour();
		Station s = new Station();
		Question q = new Question();
		Answer a1 = new Answer();
		Answer a2 = new Answer();
		Answer a3 = new Answer();
		Answer a4 = new Answer();

		// Setze die Variablen und Beziehungen
		a1.setContent("Answer 1");
		a1.setCorrect(true);
		a1.setQuestion(q);

		a2.setContent("Answer 2");
		a2.setCorrect(false);
		a2.setQuestion(q);

		a3.setContent("Answer 3");
		a3.setCorrect(false);
		a3.setQuestion(q);

		a4.setContent("Answer 4");
		a4.setCorrect(false);
		a4.setQuestion(q);

		List<Answer> answers = Arrays.asList(a1, a2, a3, a4);

		q.setContent("Question 1");
		q.setAnswers(answers);
		q.setStation(s);

		s.setStationName("Station 1");
		s.setStationDescription("Description 1");
		s.setStationNr(1);
		s.setStationLocation("46.6277, 14.3089");
		s.setTour(t);
		s.setQuestions(Arrays.asList(q));

		t.setFirstStation(s);
		t.setTourName("Tour 1");
		t.setTourDescription("Description 1");
		t.setStations(Arrays.asList(s));

		// Speichere die Objekte in der Datenbank
		// Hinweis: Aufgrund der Kaskadierungseinstellungen muss möglicherweise nur das Tour-Objekt explizit gespeichert werden.
		// Stelle sicher, dass deine Entitätsbeziehungen (OneToMany, ManyToOne) korrekt kaskadierend konfiguriert sind.
		tRepo.save(t);

	}
}
