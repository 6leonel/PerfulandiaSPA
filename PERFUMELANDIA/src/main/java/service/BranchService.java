package com.perfulandia.service;

import com.perfulandia.model.Branch;
import com.perfulandia.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch updateBranch(Long id, Branch branchDetails) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch != null) {
            branch.setName(branchDetails.getName());
            branch.setAddress(branchDetails.getAddress());
            branch.setCity(branchDetails.getCity());
            branch.setState(branchDetails.getState());
            branch.setOpeningHours(branchDetails.getOpeningHours());
            return branchRepository.save(branch);
        }
        return null;
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}
