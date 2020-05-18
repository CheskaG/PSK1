package Services;

import Entities.Dealership;
import UseCases.CarsList;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DealershipIDService {
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
