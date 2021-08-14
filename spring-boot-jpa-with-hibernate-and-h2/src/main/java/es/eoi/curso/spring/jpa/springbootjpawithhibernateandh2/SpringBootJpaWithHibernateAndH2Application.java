package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2;

import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.Student;
import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringBootJpaWithHibernateAndH2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaWithHibernateAndH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Código a ejecutar cuando inicie la aplicación
		logger.info("Iniciamos aplicación!!");

		// Read
		Optional<Student> user = repository.findById(10001L);
		if(user.isPresent()) {
			logger.info("Student id 10001 -> {}", user.get());
		} else{
			logger.error("Usuario no encontrado!");
		}
		logger.info("All users 2 -> {}", repository.findAll());

		//Insert
		logger.info("Inserting -> {}", repository.save(new Student("Pedro", "B1234657")));

		//Update
		logger.info("Update 10001 -> {}", repository.save(new Student(10001L, "Nuevo nombre", "747474")));

		//Delete
		repository.deleteById(10002L);

		logger.info("All users 2 -> {}", repository.findAll());

		logger.info("Fin de aplicación!!");
	}

}
