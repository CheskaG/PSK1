package UseCases;


import Entities.Car;
import Entities.Client;
import Entities.Dealership;
import Persistance.CarDAO;
import Persistance.ClientDAO;
import Persistance.DealershipDAO;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class CarsList{

    @Inject
    private DealershipDAO dealershipDAO;

    @Inject
    private CarDAO carDAO;

    @Getter
    @Setter
    private Car car = new Car();

    @Getter
    @Setter
    private Dealership dealership = new Dealership();

    @Getter
    private List<Car> carList;


    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer dealershipId = Integer.parseInt(requestParameters.get("dealershipId"));
        this.dealership = dealershipDAO.findOne(dealershipId);
        loadDealershipCars(dealershipId);
    }

    private void loadDealershipCars(Integer dealershipId) {
        this.carList = carDAO.loadAll(dealershipId);
    }

    @Transactional
    public String createCar() {
        car.setDealership(this.dealership);
        carDAO.persist(car);
        return "Cars?faces-redirect=true&dealershipId=" + this.dealership.getId();
    }
}
