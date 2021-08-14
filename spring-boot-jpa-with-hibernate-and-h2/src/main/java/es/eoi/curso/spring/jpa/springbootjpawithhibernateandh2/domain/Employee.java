package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EMPLOYEE_TYPE")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String state;

    private String street;

    private String zip;

    @ManyToMany
    private List<Task> tasks;

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, String city, String state, String street, String zip) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
