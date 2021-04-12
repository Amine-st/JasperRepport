package com.oauth.labapi.model;


import lombok.Data;

import java.util.Date;

@Data
public class POModel {
    private Long id;
    private String libelle ;
    private double montant;
    private String reference ;
    private String prestataire ;
    private Date dateDepot;
    private Date dateFinal;
    private int dayPassed;
    private double montantTotal;
    private Long indicateurSf;
    private Long indicateurEm;
    private Long indicateurTp;
    private long nbrTotalJour;
    private long nbrJourRetard;

    public POModel(){}

    public POModel(Long id, String libelle, double montant, String reference, String prestataire, Date dateDepot, Date dateFinal, int dayPassed, double montantTotal) {
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
        this.reference = reference;
        this.prestataire = prestataire;
        this.dateDepot = dateDepot;
        this.dateFinal = dateFinal;
        this.dayPassed = dayPassed;
        this.montantTotal = montantTotal;
    }
}
