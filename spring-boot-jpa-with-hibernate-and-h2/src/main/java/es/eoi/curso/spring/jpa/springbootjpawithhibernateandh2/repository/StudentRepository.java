package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.repository;

import es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
