package es.eoi.curso.spring.jpa.springbootjpawithhibernateandh2.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Part Time")
public class PartTimeEmployee extends Employee{

    protected Double hourly_wage;

    public PartTimeEmployee() {

    }

    public PartTimeEmployee(Double hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public Double getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(Double hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", hourly_wage=" + hourly_wage +
                '}';
    }
}
