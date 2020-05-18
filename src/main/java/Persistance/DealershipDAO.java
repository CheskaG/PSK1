package Persistance;

import Entities.Dealership;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DealershipDAO {

    @Inject
    private EntityManager em;

    public List<Dealership> loadAll() {
        return em.createNamedQuery("Dealership.findAll", Dealership.class).getResultList();
    }

    public void persist(Dealership dealership) {
        this.em.persist(dealership);
    }

    public Dealership findOne(Integer id) {
        return em.find(Dealership.class, id);
    }

    public Dealership update(Dealership dealership) {
        return em.merge(dealership);
    }
}