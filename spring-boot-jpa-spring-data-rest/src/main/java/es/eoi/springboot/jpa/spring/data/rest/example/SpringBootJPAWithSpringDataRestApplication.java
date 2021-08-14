package es.eoi.springboot.jpa.spring.data.rest.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.eoi.springboot.jpa.spring.data.rest.example.student.StudentDataRestRepository;

@SpringBootApplication
public class SpringBootJPAWithSpringDataRestApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentDataRestRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJPAWithSpringDataRestApplication.class, args);
	}
}
