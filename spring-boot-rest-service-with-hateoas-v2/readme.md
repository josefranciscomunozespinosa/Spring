## Complete Code Example


### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>es.eoi.springboot.rest.example</groupId>
    <artifactId>spring-boot-rest-service-hateoas</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>spring-boot-rest-service-hateoas</name>
    <description>Spring Boot and REST with Swagger and HATEOAS - Example Project</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>

        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-hateoas</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-snapshots</id>
            <name>Spring Snapshots</name>
            <url>https://repo.spring.io/snapshot</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>spring-snapshots</id>
            <name>Spring Snapshots</name>
            <url>https://repo.spring.io/snapshot</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
        <pluginRepository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>
</project>


```
---

### /src/main/java/es/eoi/springboot/rest/example/SpringBoot2RestServiceApplication.java

```java
package es.eoi.springboot.rest.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
}
```
---

### /src/main/java/es/eoi/springboot/rest/example/entity/Student.java

```java
package es.eoi.springboot.rest.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passportNumber;

}

```
---

### /src/main/java/es/eoi/springboot/rest/example/exceptions/StudentNotFoundException.java

```java
package es.eoi.springboot.rest.example.entity;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String exception) {
        super(exception);
    }

}
```
---

### /src/main/java/es/eoi/springboot/rest/example/repository/StudentRepository.java

```java
package es.eoi.springboot.rest.example.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
```
---

### /src/main/java/es/eoi/springboot/rest/example/dto/StudentModel.java

```java
package es.eoi.springboot.rest.example.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class StudentModel extends RepresentationModel<StudentModel> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passportNumber;
}

```
---

### /src/main/java/es/eoi/springboot/rest/example/dto/assembler/StudentModelAssembler.java

```java
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

```
---

### /src/main/java/es/eoi/springboot/rest/example/controller/StudentController.java

```java
package es.eoi.springboot.rest.example.controller;

import es.eoi.springboot.rest.example.dto.StudentModel;
import es.eoi.springboot.rest.example.dto.asembler.StudentModelAssembler;
import es.eoi.springboot.rest.example.entity.Student;
import es.eoi.springboot.rest.example.repository.StudentRepository;
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
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    StudentModelAssembler studentModelAssembler;

	/*	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentRepository.findAll();
	}
	*/

    @GetMapping("/api/students")
    public ResponseEntity<CollectionModel<StudentModel>> getAllStudents()
    {
        List<Student> actorEntities = studentRepository.findAll();
        return new ResponseEntity<>(
                studentModelAssembler.toCollectionModel(actorEntities),
                HttpStatus.OK);
    }
	
	/*
	@GetMapping("/students/{id}")
	public EntityModel<Student> retrieveStudent(@PathVariable long id) {
		Optional<Student> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);

		EntityModel<Student> resource = EntityModel.of(student.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());

		resource.add(linkTo.withRel("all-students"));

		return resource;
	}
	*/

    @GetMapping("/api/student/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable("id") Long id)
    {
        return studentRepository.findById(id)
                .map(studentModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	/*
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}
	*/

    @DeleteMapping("/api/students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudentApi(@PathVariable long id) {
        studentRepository.deleteById(id);
    }
	
	/*
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	*/

    @PostMapping("/api/students")
    public ResponseEntity<StudentModel> createStudentApi(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedStudent.getId())
                .toUri();

        return ResponseEntity.created(location).body(studentModelAssembler.toModel(savedStudent));
    }
	
	/*
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}
	*/

    @PutMapping("/api/students/{id}")
    public ResponseEntity<StudentModel> updateStudentApi(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);

        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok().body(studentModelAssembler.toModel(savedStudent));
    }
}

```
---


### /src/main/java/es/eoi/springboot/rest/example/entity/Course.java

```java
package es.eoi.springboot.rest.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

}
```
---


### /src/main/resources/application.properties

```properties
# Enabling H2 Console
spring.h2.console.enabled=true

spring.datasource.url=jdbc:h2:mem:testdb

# Mostrar las consultas sql
spring.jpa.show-sql=true
```
---

### /src/main/resources/data.sql

```

insert into student values(10001,'Jose Francisco', 'E1234567');
insert into student values(10002,'Maria Angeles', 'A1234568');


insert into course values(1001,'Programación', 'Spring');
insert into course values(1002,'Inglés I', 'Inglés básico');
insert into course values(1003,'Inglés II', 'Inglés medio');
insert into course values(1004,'Inglés II', 'Inglés avanzado');
```
---

### /src/test/java/es/eoi/springboot/rest/example/SpringBootRestServiceApplicationTests.java

```java
package es.eoi.springboot.rest.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
```
---
