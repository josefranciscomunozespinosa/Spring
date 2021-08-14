package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.repository;

import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
