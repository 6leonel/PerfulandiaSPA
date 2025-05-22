package service;

import model.Inventory;
import repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

public class InventoryService {

    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory getItemById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory createItem(Inventory item) {
        return inventoryRepository.save(item);
    }

    public Inventory updateItem(Long id, Inventory itemDetails) {
        Inventory item = inventoryRepository.findById(id).orElse(null);
        if (item != null) {
            item.setProductName(itemDetails.getProductName());
            item.setQuantity(itemDetails.getQuantity());
            item.setPrice(itemDetails.getPrice());
            return inventoryRepository.save(item);
        }
        return null;
    }

    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }


}
