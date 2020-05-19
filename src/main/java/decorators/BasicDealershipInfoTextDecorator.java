package decorators;

import Entities.Dealership;
import Services.DealershipIDService;
import UseCases.CarsList;
import lombok.Getter;
import lombok.Setter;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class BasicDealershipInfoTextDecorator implements DealershipInfoText{


    @Inject
    @Delegate
    @Any
    BasicDealershipInfoText baseDealershipInfoText;

    @Inject
    private DealershipIDService idService;

    @Override
    public String info() {

        String info = baseDealershipInfoText.info() ;
        return info + idService.id();
    }
}
