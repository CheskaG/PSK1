package Persistance;



import Entities.Car;
import Entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClientDAO {

    @Inject
    private EntityManager em;

    public List<Client> loadAll(Integer carId) {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    public void persist(Client client) {
        this.em.persist(client);
    }

    public void remove(Client client) { this.em.remove(client); }

    public Client findOne(Integer id) {
        return em.find(Client.class, id);
    }

}
