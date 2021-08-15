package es.eoi.springboot.rest.example.dto.asembler;

import es.eoi.springboot.rest.example.controller.CourseController;
import es.eoi.springboot.rest.example.controller.StudentController;
import es.eoi.springboot.rest.example.dto.CourseModel;
import es.eoi.springboot.rest.example.dto.StudentModel;
import es.eoi.springboot.rest.example.entity.Course;
import es.eoi.springboot.rest.example.entity.Student;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentModelAssembler extends RepresentationModelAssemblerSupport<Student, StudentModel> {

    public StudentModelAssembler() {
        super(StudentController.class, StudentModel.class);
    }

    @Override
    public StudentModel toModel(Student entity) {

        StudentModel studentModel = instantiateModel(entity);

        studentModel.add(linkTo(
                methodOn(StudentController.class)
                        .getStudentById(entity.getId()))
                .withSelfRel());

        studentModel.setId(entity.getId());
        studentModel.setName(entity.getName());
        studentModel.setPassportNumber(entity.getPassportNumber());

        studentModel.setCourses(toCourseModel(entity.getCourses()));
        return studentModel;
    }

    private List<CourseModel> toCourseModel(List<Course> courses) {
        if (courses.isEmpty())
            return Collections.emptyList();
        return courses.stream()
                .map(course -> CourseModel.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .description(course.getDescription())
                        .build()
                        .add(linkTo(
                                methodOn(CourseController.class)
                                        .getCourseById(course.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }

}
