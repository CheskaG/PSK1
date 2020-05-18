package UseCases;

import decorators.DealershipInfoText;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class dealershipUpdateInfo {

    @Inject
    private DealershipInfoText dealershipInfoText;

    public String info() {
        return dealershipInfoText.info();
    }
}
