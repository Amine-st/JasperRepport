package com.oauth.labapi.model;

import java.util.List;


import lombok.Data;

import java.util.Date;

@Data
public class Model {
    private String reference;
    private String prestataire ;
    private long  factureId;
    private String numero ;
    private Date datePvRecep;
    private Date dateDepot;
    private double montant;
    private long nbrTotalDeJours;
    private long nbrDeJoursRetard;
    private long indicateurSf;
    private long indicateurEm;
    private long indicateurTp;




}
