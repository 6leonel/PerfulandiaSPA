package repository;

import model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchReoisitory extends JpaRepository<Branch, Long> {
}
