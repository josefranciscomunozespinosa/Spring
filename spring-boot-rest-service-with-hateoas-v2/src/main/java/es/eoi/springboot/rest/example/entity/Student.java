package es.eoi.springboot.rest.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "courses")
@Entity
public class Student implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String passportNumber;


	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

}
