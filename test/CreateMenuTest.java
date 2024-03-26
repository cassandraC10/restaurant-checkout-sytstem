import RestaurantCheckoutSystem.CreateMenu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CreateMenuTest {

    @Test
    public void testLoadMenuFromFile() {
        String input = "menu.txt";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        CreateMenu.loadMenuFromFile();

        System.setIn(System.in);
    }

}
