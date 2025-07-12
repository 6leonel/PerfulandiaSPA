package com.perfulandia.service;

import com.perfulandia.model.Logistics;
import com.perfulandia.repository.LogisticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class LogisticsService {

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String SALES_SERVICE_URL = "http://localhost:8080/api/sales";

    public List<Logistics> getAllShipments() {
        return logisticsRepository.findAll();
    }

    public Logistics getShipmentById(Long id) {
        return logisticsRepository.findById(id).orElse(null);
    }

    public Logistics createShipment(Long saleId) {
        Logistics shipment = new Logistics();
        shipment.setSaleId(saleId);
        shipment.setTrackingNumber(UUID.randomUUID().toString()); // ✅ Corrección aquí
        shipment.setStatus("PENDING");
        return logisticsRepository.save(shipment);
    }

    public Logistics updateShipmentStatus(Long id, String status) {
        Logistics logistics = logisticsRepository.findById(id).orElse(null);
        if (logistics != null) {
            logistics.setStatus(status);
            return logisticsRepository.save(logistics);
        }
        return null;
    }

    public void deleteShipment(Long id) {
        logisticsRepository.deleteById(id);
    }
}
