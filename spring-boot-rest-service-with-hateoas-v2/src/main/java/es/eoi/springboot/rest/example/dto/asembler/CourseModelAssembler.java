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
public class CourseModelAssembler extends RepresentationModelAssemblerSupport<Course, CourseModel> {

    public CourseModelAssembler() {
        super(CourseController.class, CourseModel.class);
    }

    @Override
    public CourseModel toModel(Course entity) {

        CourseModel CourseModel = instantiateModel(entity);

        CourseModel.add(linkTo(
                methodOn(CourseController.class)
                        .getCourseById(entity.getId()))
                .withSelfRel());

        CourseModel.setId(entity.getId());
        CourseModel.setName(entity.getName());
        CourseModel.setDescription(entity.getDescription());
        CourseModel.setStudents(toStudentModel(entity.getStudents()));
        return CourseModel;
    }

    private List<StudentModel> toStudentModel(List<Student> students) {
        if (students.isEmpty())
            return Collections.emptyList();
        return students.stream()
                .map(student -> StudentModel.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .passportNumber(student.getPassportNumber())
                        .build()
                        .add(linkTo(
                                methodOn(StudentController.class)
                                        .getStudentById(student.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }

}
