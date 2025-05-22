package controller;

import model.Branch;
import service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class BranchController {

    private BranchService branchService;

    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        if (branch != null) {
            return ResponseEntity.ok(branch);
        }
        return ResponseEntity.notFound().build();
    }

    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch branchDetails) {
        try {
            Branch updatedBranch = branchService.updateBranch(id, branchDetails);
            return ResponseEntity.ok(updatedBranch);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
