package es.eoi.springboot.rest.example.repository;

import es.eoi.springboot.rest.example.entity.Course;
import es.eoi.springboot.rest.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
