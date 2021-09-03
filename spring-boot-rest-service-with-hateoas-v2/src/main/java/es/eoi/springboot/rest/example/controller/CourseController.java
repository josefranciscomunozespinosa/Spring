package es.eoi.springboot.rest.example.controller;

import es.eoi.springboot.rest.example.dto.CourseModel;
import es.eoi.springboot.rest.example.dto.asembler.CourseModelAssembler;
import es.eoi.springboot.rest.example.entity.Course;
import es.eoi.springboot.rest.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	CourseModelAssembler courseModelAssembler;


	@GetMapping("/api/courses")
	public ResponseEntity<CollectionModel<CourseModel>> getAllCourses()
	{
		List<Course> courseEntityList = courseRepository.findAll();
		return new ResponseEntity<>(
				courseModelAssembler.toCollectionModel(courseEntityList),
				HttpStatus.OK);
	}


	@GetMapping("/api/course/{id}")
	public ResponseEntity<CourseModel> getCourseById(@PathVariable("id") Long id)
	{
		return courseRepository.findById(id)
				.map(courseModelAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}


	@DeleteMapping("/api/courses/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteCourseApi(@PathVariable long id) {
		courseRepository.deleteById(id);
	}


	@PostMapping("/api/courses")
	public ResponseEntity<CourseModel> createCourseApi(@RequestBody Course course) {
		Course savedCourse = courseRepository.save(course);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(savedCourse.getId())
				.toUri();

		return ResponseEntity.created(location).body(courseModelAssembler.toModel(savedCourse));
	}

	@PutMapping("/api/courses/{id}")
	public ResponseEntity<CourseModel> updateCourseApi(@RequestBody Course course, @PathVariable long id) {

		Optional<Course> courseOptional = courseRepository.findById(id);

		if (!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		course.setId(id);

		Course savedCourse = courseRepository.save(course);
		return ResponseEntity.ok().body(courseModelAssembler.toModel(savedCourse));
	}
}
