package Persistance;

import Entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarDAO {

    @Inject
    private EntityManager em;

    public List<Car> loadAll(Integer dealershipId) {
        return em.createNamedQuery("Car.findAll", Car.class).setParameter("dealershipId", dealershipId).getResultList();
    }

    public void persist(Car car) {
        this.em.persist(car);
    }

    public Car findOne(Integer id) {
        return em.find(Car.class, id);
    }

}
