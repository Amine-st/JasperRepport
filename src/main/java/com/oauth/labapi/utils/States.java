package com.oauth.labapi.utils;

public enum States {

    INITIAL("Etat initiale"),
    FACTURE_DEPOSEE("Nouvelle facture déposée"),
    FACTURE_VALIDE_TECHNIQUE("Facture validée par le S.T"),
    FACTURE_TECHNIQUE_REJETEE("Facture rejetée par le S.T"),
    FACTURE_SATISFAITE("Facture satisfaute après rejet"),
    DOSSIER_PAIMENT_EMIS("Dossier de paiement émis"),
    DOSSIER_PAIMENT_REJETE_TECH("Dossier de paiement rejeté pour le S.T"),
    DOSSIER_PAIMENT_REJETE_FRNS("Dossier de paiement rejeté pour le fournisseur"),
    DOSSIER_PAIMENT_SATISFAIT("Dossier de paiement satisfait après rejet"),
    DOSSIER_PAIMENT_ORDONNANCE("Ordonnancement pour paiement"),
    ORDONNANCEMENT_REJETE("Ordonnancement rejeté"),
    SATISFACTION_ORDONNANCEMENT("Satisfaction de l'ordonnancement"),
    DOSSIER_PAYE("Dossier payé"),
    PAIEMENT_REJETE("Dossier rejeté"),
    VIREMENT("Virement");

    private String description;

    private States(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }







}