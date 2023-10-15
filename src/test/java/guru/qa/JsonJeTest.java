package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Products;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class JsonJeTest {
    private final ClassLoader cl = JsonJeTest.class.getClassLoader();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Проверка содержимого json файла JsonJeTest")
    @Test
    public void checkJson() throws IOException {
        try (InputStream is = cl.getResourceAsStream("testJson.json")) {
            Products productsJson = objectMapper.readValue(is, Products.class);

            assertTrue(productsJson.isAvailability());
            assertEquals("Apple", productsJson.getProductsAvailable().get(0).getBrand());
            assertEquals("iphone 15 Pro", productsJson.getProductsAvailable().get(0).getModel());
            assertEquals(990, productsJson.getProductsAvailable().get(0).getPrice());
            assertEquals("Apple", productsJson.getProductsAvailable().get(1).getBrand());
            assertEquals("iphone 15 Pro Max", productsJson.getProductsAvailable().get(1).getModel());
            assertEquals(1190, productsJson.getProductsAvailable().get(1).getPrice());
        }

    }
}