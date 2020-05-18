package UseCases;

import Entities.Dealership;
import Persistance.DealershipDAO;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateDealershipInfo implements Serializable {

    private Dealership dealership;

    @Inject
    private DealershipDAO dealershipDao;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer dealershipId = Integer.parseInt(requestParameters.get("dealershipId"));
        this.dealership = dealershipDao.findOne(dealershipId);
    }

    @Transactional
    @LoggedInvocation
    public String updateDealership() {
        try{
            dealershipDao.update(this.dealership);
        } catch (OptimisticLockException e) {
            return "/dealershipInfo.xhtml?faces-redirect=true&dealershipId=" + this.dealership.getId() + "&error=optimistic-lock-exception";
        }
        return "index.xhtml?&faces-redirect=true";
    }

}
