package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Full Time")
public class FullTimeEmployee extends Employee{

    protected Integer salary;

    public FullTimeEmployee() {
        super();
    }

    public FullTimeEmployee(Integer salary) {
        super();
        this.salary = salary;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", salary=" + salary +
                '}';
    }
}
