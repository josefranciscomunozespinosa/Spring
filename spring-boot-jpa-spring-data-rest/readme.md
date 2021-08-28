## Complete Code Example

Spring Data project provides common abstractions to integrate with different kinds of data stores. Spring Data Rest is part of the umbrella of Spring Data projects. It makes it easy to build hypermedia-driven REST web services, on top of Spring Data repositories.

You will learn
- What is Spring Data Rest?
- How does Spring Data Rest make building REST API easier?
- How to integration Spring Data Rest with Spring Data JPA?
- What are important features of Spring Data Rest?
- How to execute REST API created using Spring Data Rest?


What Does Spring Data Rest Do?

It does all of the following:
- Exposes REST services around the Spring Data, without having to write a lot of code.
- Supports both SQL-based and No SQL-based databases.
- Supports Pagination
- Enables Filtering
- Allows for Sorting
- Supports HATEOAS

## Why Spring Data Rest
Spring Data Rest is ideal for simple projects, that quickly want to get started with creating REST API.

Let’s look at a quick example of using Spring Data Rest to build a REST API.

## Generate your project

The easiest way to bring such projects up, would be to use the Spring Initializr.
- Web
- JPA
- Rest
- H2

Note that we have included the dependencies such as Web, JPA and Rest Repositories.You can generate the project, and then import it as a maven project into your IDE.

## The complete code

### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>es.eoi.springboot.rest.example</groupId>
	<artifactId>spring-boot-jpa-spring-data-rest</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-boot-jpa-with-hibernate-and-h2</name>
	<description>Spring Boot, Hibernate, JPA and H2 - Example Project</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.1.RELEASE</version>
		<relativePath />
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
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
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

### /src/main/java/es/eoi/springboot/jpa/spring/data/rest/example/SpringBoot2JPAWithSpringDataRestApplication.java

```java
package es.eoi.springboot.jpa.spring.data.rest.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import StudentDataRestRepository;

@SpringBootApplication
public class SpringBoot2JPAWithSpringDataRestApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentDataRestRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2JPAWithSpringDataRestApplication.class, args);
    }
}
```
---

### /src/main/java/es/eoi/springboot/jpa/spring/data/rest/example/student/Student.java

```java
package es.eoi.springboot.jpa.spring.data.rest.example.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String passportNumber;

	public Student() {
		super();
	}

	public Student(Long id, String name, String passportNumber) {
		super();
		this.id = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Student(String name, String passportNumber) {
		super();
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, passportNumber=%s]", id, name, passportNumber);
	}

}
```
---

### /src/main/java/es/eoi/springboot/jpa/spring/data/rest/example/student/StudentDataRestRepository.java
Once you have an entity, you can create a RepositoryRestResource for it.

```java
package es.eoi.springboot.jpa.spring.data.rest.example.student;

import es.eoi.springboot.jpa.spring.data.rest.example.student.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "students", collectionResourceRel = "students")
public interface StudentDataRestRepository extends PagingAndSortingRepository<Student, Long> {

}
```
---

### /src/main/resources/application.properties

```properties
# Enabling H2 Console
spring.h2.console.enabled=true
#Turn Statistics on
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=debug
# Show all queries
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type=trace
```
---

### /src/main/resources/data.sql

```
insert into student
values(10001,'Jose Francisco', 'E1234567');

insert into student
values(10002,'Maria Angeles', 'A1234568');
```
---

### /src/test/java/es/eoi/springboot/jpa/spring/data/rest/example/SpringBootJPAWithSpringDataRestApplicationTests.java

```java
package es.eoi.springboot.jpa.spring.data.rest.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJPAWithSpringDataRestApplicationTests {

	@Test
	public void contextLoads() {
	}

}
```
---



Final result in h2 console:

![H2 console](./images/img.png)

