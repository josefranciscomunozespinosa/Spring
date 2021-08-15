package es.eoi.springboot.rest.example.dto.asembler;

import es.eoi.springboot.rest.example.controller.StudentController;
import es.eoi.springboot.rest.example.dto.StudentModel;
import es.eoi.springboot.rest.example.entity.Student;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

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

        return studentModel;
    }

}
