import RestaurantCheckoutSystem.AnalyzeTakings;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AnalyzeTakingsTest {

    @Test
    public void testAnalyzeTransactions() {
        String input = "transactions.txt";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        AnalyzeTakings.analyzeTransactions();

        System.setIn(System.in);
    }

}
