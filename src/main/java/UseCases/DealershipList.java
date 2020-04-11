package UseCases;

import Entities.Dealership;
import Persistance.DealershipDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class DealershipList {

    @Inject
    private DealershipDAO dealershipDAO;

    @Getter
    @Setter
    private Dealership dealership = new Dealership();

    @Getter
    private List<Dealership> dealershipList;

    @PostConstruct
    public void init(){
        loadAllDealerships();
    }

    private void loadAllDealerships(){
        this.dealershipList = dealershipDAO.loadAll();
    }

    @Transactional
    public String createDealership() {
        this.dealershipDAO.persist(dealership);
        return "index?faces-redirect=true";
    }

}
