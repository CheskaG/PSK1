package UseCases;

import Services.Generator;
import interceptors.LoggedInvocation;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateRating implements Serializable {

    @Inject
    Generator generator;

    private CompletableFuture<Double> generatedRating = null;

    @LoggedInvocation
    public String GenerateRating() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        generatedRating = CompletableFuture.supplyAsync(() -> generator.generateNumber());

        return  "/dealershipInfo.xhtml?faces-redirect=true&dealershipId=" + requestParameters.get("dealershipId");
    }

    public boolean isGenerationRunning() {
        return generatedRating != null && !generatedRating.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (generatedRating== null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Calculating rating";
        }
        return " Rating: " + generatedRating.get();
    }
}