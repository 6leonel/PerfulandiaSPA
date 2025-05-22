package controller;

import model.Logistics;
import service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class LogisticsController {

    private LogisticsService logisticsService;

    public List<Logistics> getAllShipments() {
        return logisticsService.getAllShipments();
    }

    public ResponseEntity<Logistics> getShipmentById(@PathVariable Long id) {
        Logistics logistics = logisticsService.getShipmentById(id);
        if (logistics != null) {
            return ResponseEntity.ok(logistics);
        }
        return ResponseEntity.notFound().build();
    }

    public Logistics createShipment(@PathVariable Long saleId) {
        return logisticsService.createShipment(saleId);
    }

    public ResponseEntity<Logistics> updateShipmentStatus(@PathVariable Long id, @RequestParam String status) {
        Logistics updatedLogistics = logisticsService.updateShipmentStatus(id, status);
        if (updatedLogistics != null) {
            return ResponseEntity.ok(updatedLogistics);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        logisticsService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}
