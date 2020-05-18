package Services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped

public class RatingGenerator implements Generator {
    @Override
    public Double generateNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        int rangeMin= 0;
        int rangeMax= 10;
        Random r = new Random();
        double rating = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return rating;
    }
}
