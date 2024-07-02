package fr.studi.stock.manager;

import fr.studi.stock.pojo.LogStock;
import fr.studi.stock.pojo.Produit;
import fr.studi.stock.service.LogStockService;
import fr.studi.stock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogStockManager {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private LogStockService logStockService;

    public void processLog(LogStock logStock){
        //1. intégration du log
        this.logStockService.createLogStock(logStock);

        //2. mise en place de la modification
        switch(logStock.getAction()){
            case MODIFICATION -> {
                this.produitService.updateProduitQuantity(
                        logStock.getProduit_id(), logStock.getQuantite()
                );
                break;
            }
            case SUPPRESSION -> {
                this.produitService.deleteProduitById(logStock.getProduit_id());
                break;
            }
        }
    }
}
