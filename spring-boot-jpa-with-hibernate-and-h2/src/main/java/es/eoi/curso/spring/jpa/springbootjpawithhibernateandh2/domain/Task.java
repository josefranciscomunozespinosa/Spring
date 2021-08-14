package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Boolean done;

    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employees;

    public Task() {

    }

    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
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

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<Employee> getEmployees() {
        if(employees==null) {
            employees = new ArrayList<>();
        }

        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", done=" + done +
                '}';
    }
}
