package es.eoi.springboot.rest.example.dto.asembler;

import es.eoi.springboot.rest.example.controller.CourseController;
import es.eoi.springboot.rest.example.dto.CourseModel;
import es.eoi.springboot.rest.example.entity.Course;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

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

        return CourseModel;
    }

}
