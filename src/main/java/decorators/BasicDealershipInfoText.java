package decorators;


import javax.enterprise.inject.Default;

@Default
public class BasicDealershipInfoText implements DealershipInfoText{

    @Override
    public String info() {
        return "You are visiting Dealership ";
    }
}
