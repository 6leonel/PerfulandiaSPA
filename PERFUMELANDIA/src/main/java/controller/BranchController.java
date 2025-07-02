package controller;

import model.Branch;
import service.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Operation(summary = "Listar sucursales", description = "Devuelve todas las sucursales registradas.")
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @Operation(summary = "Obtener sucursal", description = "Devuelve los datos de una sucursal específica.")
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return branch != null ? ResponseEntity.ok(branch) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear sucursal", description = "Registra una nueva sucursal en el sistema.")
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    @Operation(summary = "Actualizar sucursal", description = "Actualiza los datos de una sucursal existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch branchDetails) {
        try {
            Branch updated = branchService.updateBranch(id, branchDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal del sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
