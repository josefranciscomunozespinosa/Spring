package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2;

import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.Employee;
import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.FullTimeEmployee;
import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.Student;
import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.repository.EmployeeRepository;
import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootJpaWithHibernateAndH2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaWithHibernateAndH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Código a ejecutar cuando inicie la aplicación
		logger.info("Iniciamos aplicación!!");

		// Read
		Optional<Student> user = studentRepository.findById(10001L);
		if(user.isPresent()) {
			logger.info("Student id 10001 -> {}", user.get());
		} else{
			logger.error("Usuario no encontrado!");
		}
		logger.info("All users 2 -> {}", studentRepository.findAll());

		//Insert
		logger.info("Inserting -> {}", studentRepository.save(new Student("Pedro", "B1234657")));

		//Update
		logger.info("Update 10001 -> {}", studentRepository.save(new Student(10001L, "Nuevo nombre", "747474")));

		//Delete
		studentRepository.deleteById(10002L);

		logger.info("All users 2 -> {}", studentRepository.findAll());


		// Read empleados
		List<Employee> employeeList = employeeRepository.findAll();
		if(!employeeList.isEmpty()) {
			logger.info("Hay un total de {} empleados!", employeeList.size());
		} else{
			logger.warn("No hay empleados!");
		}

		Optional<Employee> employee = employeeRepository.findById(1L);
		if(employee.isPresent()) {
			logger.info("Empleado id 1 -> {}", (FullTimeEmployee) employee.get());
		} else{
			logger.error("Empleado no encontrado!");
		}

		logger.info("Fin de aplicación!!");
	}

}
