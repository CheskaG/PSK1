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
        @NamedQuery(name = "Client.findAll", query = "select c from Client as c")
})
@Table(name = "CLIENT")
@Getter
@Setter
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @Size(max = 100)
    @Column(name = "SURNAME")
    private String surname;


    @Column(name = "MILES_DRIVEN")
    private Integer milesDriven;

    @Column(name = "AMOUNT_PAID")
    private Double amountPaid ;




    @ManyToMany(mappedBy="clients")
    private List<Car> cars = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }


}

