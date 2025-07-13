package com.perfulandia.repository;

import com.perfulandia.model.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends JpaRepository<Logistics, Long> {
}
