package fr.studi.stock.service;

import fr.studi.stock.pojo.LogStock;
import jakarta.validation.Valid;

public interface LogStockService {

    void createLogStock(LogStock logStock);
}
