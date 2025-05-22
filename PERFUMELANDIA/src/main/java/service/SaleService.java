package service;

import model.Sale;
import repository.SaleRepository;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

public class SaleService {

    private SaleRepository saleRepository;

    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:8080/api/users";
    private static final String INVENTORY_SERVICE_URL = "http://localhost:8080/api/inventory";

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale createSale(Sale sale) {
        Boolean userExists = restTemplate.getForObject(USER_SERVICE_URL + "/" + sale.getUserId(), Boolean.class);
        if (!Boolean.TRUE.equals(userExists)) {
            throw new IllegalArgumentException("El usuario no existe: " + sale.getUserId());
        }

        InventoryResponse inventory = restTemplate.getForObject(
                INVENTORY_SERVICE_URL + "/" + sale.getProductId(),
                InventoryResponse.class
        );

        InventoryResponse Inventory = restTemplate.getForObject(
                INVENTORY_SERVICE_URL + "/" + sale.getProductId(),
                InventoryResponse.class
        );

        Inventory.setQuantity(Inventory.getQuantity() - sale.getQuantity());
        restTemplate.put(INVENTORY_SERVICE_URL + "/" + sale.getProductId(), Inventory);

        // Registrar la venta
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalPrice(sale.getQuantity() * Inventory.getPrice());
        return saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    private static class InventoryResponse {
        private Long id;
        private String productName;
        private int quantity;
        private double price;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }
}

