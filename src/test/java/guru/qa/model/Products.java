package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Products {
    @JsonProperty("availability")
    private boolean availability;
    @JsonProperty("products")
    private List<ProductsAvailable> products;

    public List<ProductsAvailable> getProductsAvailable() {
        return products;
    }

    public boolean isAvailability() {
        return availability;
    }

    public static class ProductsAvailable {
        @JsonProperty("brand")
        private String brand;
        @JsonProperty("model")
        private String model;
        @JsonProperty("price")
        private Integer price;

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public Integer getPrice() {
            return price;
        }
    }
}