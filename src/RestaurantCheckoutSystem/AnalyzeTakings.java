package RestaurantCheckoutSystem;

import java.io.*;

public class AnalyzeTakings {

    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void main(String[] args) {
        analyzeTransactions();
    }

    public static void analyzeTransactions() {
        displayDailyStatistics(TRANSACTION_FILE);
    }

    private static void displayDailyStatistics(String transactionFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading transaction file: " + e.getMessage());
        }
    }
}
