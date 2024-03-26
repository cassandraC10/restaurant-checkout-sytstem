import RestaurantCheckoutSystem.BillingCheckout;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillingCheckoutTest {

    @Test
    public void testGenerateBill() {
        List<RestaurantCheckoutSystem.MenuItem> mockMenu = new ArrayList<>();
        mockMenu.add(new RestaurantCheckoutSystem.MenuItem(1, "Category", "Item Description", 10.0));

        BillingCheckout billingCheckout = new BillingCheckout(mockMenu);

        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n0\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        billingCheckout.generateBill(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Bill:"));
        assertTrue(output.contains("Subtotal: "));
        assertTrue(output.contains("VAT: "));
        assertTrue(output.contains("Grand Total: "));
    }
}
