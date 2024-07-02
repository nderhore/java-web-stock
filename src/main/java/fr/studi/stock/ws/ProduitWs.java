package fr.studi.stock.ws;

import fr.studi.stock.pojo.Produit;
import fr.studi.stock.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/produit")
@RestController
public class ProduitWs {

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public void createProduit(@Valid @RequestBody Produit produit) {
        this.produitService.createProduit(produit);
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return this.produitService.getAllProduit();
    }

    @DeleteMapping("/{id}")
    public void deleteProduitById(@PathVariable("id") Long id) {
        this.produitService.deleteProduitById(id);
    }
}
