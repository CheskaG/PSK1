package UseCases;

import Entities.Car;
import Entities.Client;
import Persistance.CarDAO;
import Persistance.ClientDAO;
import com.sun.mail.util.LineInputStream;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import mybatis.dao.CarClientMapper;
import mybatis.dao.ClientMapper;
import mybatis.model.CarClient;
;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
@RequestScoped
public class ClientListMyBatis{

    @Inject
    private ClientMapper clientMapper;

    @Inject
    private CarClientMapper carClientMapper;

    @Getter
    @Setter
    private mybatis.model.CarClient carClient = new mybatis.model.CarClient();;

    @Getter
    @Setter
    private Car car= new Car();

    @Inject
    private CarDAO carDAO;

    @Getter @Setter
    private List<mybatis.model.Client> allClients;

    @Getter @Setter
    private List<mybatis.model.Client> selectedClients;

   @Inject
    private ClientDAO clientDao;


    @Getter @Setter
    private mybatis.model.Client clientToCreate = new mybatis.model.Client();


    @Getter @Setter
    private mybatis.model.Client client = new mybatis.model.Client();




    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carId = Integer.parseInt(requestParameters.get("carId"));
            this.car = carDAO.findOne(carId);
            this.loadAllClients();
            this.loadSelectedClients(carId);
    }



    private void loadAllClients() {
        this.allClients = clientMapper.selectAll();
    }
    private void loadSelectedClients(Integer carId){
        this.selectedClients =clientMapper.getSelectedClients(carId);
    }

    @Transactional
    public String createClient() {
        clientMapper.insert(clientToCreate);

        return "Clients?faces-redirect=true&carId=" + this.car.getId();
    }

    @Transactional
    public String addClient() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer clientId = Integer.parseInt(requestParameters.get("clientId"));

        carClient.setCarsId(this.car.getId());
        carClient.setClientsId(clientId);
        carClientMapper.insert(carClient);
        return "Clients?faces-redirect=true&carId=" + this.car.getId();
    }


    @Override
    public String toString() {
        return "ClientListMyBatis{" +
                "client=" + client.getId() +" "+client.getName() +
                '}';
    }
}