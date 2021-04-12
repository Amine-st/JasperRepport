package com.oauth.labapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long current_opertion_id;
    private Long contrat_id;
    private Long prestataire_id;
    private String numero ;
    private String contrat ;
    private String statut ;
    private String statut_utilisateur ;
    private double montant;
    private String reference ;
    private String prestataire ;
    private Date dateDepot;
    private Date datePvRecep;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> operations;



}
