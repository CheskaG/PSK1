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
        @NamedQuery(name = "Dealership.findAll", query = "select d from Dealership as d")
})
@Table(name = "DEALERSHIP")
@Getter
@Setter
public class Dealership{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(max = 100)
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "dealership")
    private List<Car> cars= new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealership dealership = (Dealership) o;
        return Objects.equals(name, dealership.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}

