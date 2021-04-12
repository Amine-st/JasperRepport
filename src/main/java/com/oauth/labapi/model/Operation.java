package com.oauth.labapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String source ;
    private String target ;
    private int poid ;
    private Date dateOperation ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Facture facture = null;
    private boolean eventEmail = false ;
}
