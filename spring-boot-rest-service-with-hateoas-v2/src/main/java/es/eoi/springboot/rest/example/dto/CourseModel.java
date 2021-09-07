package es.eoi.springboot.rest.example.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseModel extends RepresentationModel<CourseModel> {

	private Long id;
	private String name;
	private String description;

	private List<StudentModel> students;
}