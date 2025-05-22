package repository;

import model.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogisticsRepository extends JpaRepository<Logistics, Long> {
}
