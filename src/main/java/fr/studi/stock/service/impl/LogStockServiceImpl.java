package fr.studi.stock.service.impl;

import fr.studi.stock.pojo.LogStock;
import fr.studi.stock.repository.LogStockRepository;
import fr.studi.stock.service.LogStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogStockServiceImpl implements LogStockService {

    @Autowired
    private LogStockRepository logStockRepository;

    @Override
    public void createLogStock(LogStock logStock) {

    }

}
