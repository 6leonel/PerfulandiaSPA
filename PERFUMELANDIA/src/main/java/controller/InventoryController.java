package controller;

import model.Inventory;
import service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class InventoryController {

    private InventoryService inventoryService;

    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    public ResponseEntity<Inventory> getItemById(@PathVariable Long id) {
        Inventory item = inventoryService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    public Inventory createItem(@RequestBody Inventory item) {
        return inventoryService.createItem(item);
    }

    public ResponseEntity<Inventory> updateItem(@PathVariable Long id, @RequestBody Inventory itemDetails) {
        Inventory updatedItem = inventoryService.updateItem(id, itemDetails);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
