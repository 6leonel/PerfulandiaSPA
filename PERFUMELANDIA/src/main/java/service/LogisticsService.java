package service;

import model.Logistics;
import repository.LogisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.UUID;

public class LogisticsService {

    private LogisticsRepository logisticsRepository;
    private RestTemplate restTemplate;

    private static final String SALES_SERVICE_URL = "http://localhost:8080/api/sales";

    public List<Logistics> getAllShipments() {
        return logisticsRepository.findAll();
    }

    public Logistics getShipmentById(Long id) {
        return logisticsRepository.findById(id).orElse(null);
    }

    public Logistics createShipment(Long saleId) {
        Boolean saleExists = restTemplate.getForObject(SALES_SERVICE_URL + "/" + saleId, Boolean.class);
        if (!Boolean.TRUE.equals(saleExists)) {
            throw new IllegalArgumentException("La venta no existe: " + saleId);
        }

        Logistics logistics = new Logistics();
        logistics.setSaleId(saleId);
        logistics.setStatus("PENDING");
        logistics.setTrackingNumber(UUID.randomUUID().toString());
        return logisticsRepository.save(logistics);
    }

    public Logistics updateShipmentStatus(Long id, String status) {
        Logistics logistics = logisticsRepository.findById(id).orElse(null);
        if (logistics != null) {
            logistics.setStatus(status);
            return logisticsRepository.save(logistics);
        }
        throw new IllegalArgumentException("El envío no existe: " + id);
    }

    public void deleteShipment(Long id) {
        logisticsRepository.deleteById(id);
    }
}
