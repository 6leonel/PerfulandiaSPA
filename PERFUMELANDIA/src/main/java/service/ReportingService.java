package com.perfulandia.service;

import com.perfulandia.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportingService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SALES_SERVICE_URL = "http://localhost:8080/api/sales";
    private static final String LOGISTICS_SERVICE_URL = "http://localhost:8080/api/logistics";

    public Report getSalesSummary() {
        Object[] sales = restTemplate.getForObject(SALES_SERVICE_URL, Object[].class);
        int totalSales = (sales != null) ? sales.length : 0;

        Map<String, Object> data = new HashMap<>();
        data.put("totalSales", totalSales);

        return new Report("Sales Summary", data);
    }

    public Report getLogisticsSummary() {
        Object[] shipments = restTemplate.getForObject(LOGISTICS_SERVICE_URL, Object[].class);

        int pending = 0, shipped = 0, delivered = 0;

        if (shipments != null) {
            for (Object shipment : shipments) {
                @SuppressWarnings("unchecked")
                Map<String, Object> shipmentData = (Map<String, Object>) shipment;
                String status = (String) shipmentData.get("status");
                switch (status) {
                    case "PENDING" -> pending++;
                    case "SHIPPED" -> shipped++;
                    case "DELIVERED" -> delivered++;
                }
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("pending", pending);
        data.put("shipped", shipped);
        data.put("delivered", delivered);

        return new Report("Logistics Summary", data);
    }
}
