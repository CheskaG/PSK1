package Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select c from Car as c where c.dealership.id=:dealershipId")
})
@Table(name = "CAR")
@Getter
@Setter
public class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(max = 100)
    @Column(name = "MANUACTURER")
    private String manufacturer;

    @Size(max = 100)
    @Column(name = "MODEL")
    private String model;

    @ManyToOne
    @JoinColumn(name="DEALERSHIP_ID")
    private Dealership dealership;

    @ManyToMany
    @JoinTable(name="CAR_CLIENT")
    private List<Client> clients = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}

