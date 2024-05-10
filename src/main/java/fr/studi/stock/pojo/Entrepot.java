package fr.studi.stock.pojo;

import lombok.Data;

@Data
public class Entrepot {

    private Long entrepotId; // en base : entrepot_id
    private String adresse;
    private String nom;

}
