package Services;

import Entities.Dealership;
import UseCases.CarsList;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

@Specializes
public class CorrectDealershipId extends DealershipIDService {
    @Inject
    private CarsList carsList;

    @Setter
    @Getter
    private Dealership dealership;

    public String id() {
        this.dealership=carsList.getDealership();
        String id = Integer.toString(this.dealership.getId());
        return id;
    }
}
