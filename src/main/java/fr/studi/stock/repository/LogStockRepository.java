package fr.studi.stock.repository;

import fr.studi.stock.pojo.LogStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogStockRepository extends JpaRepository<LogStock, Long> {
}
