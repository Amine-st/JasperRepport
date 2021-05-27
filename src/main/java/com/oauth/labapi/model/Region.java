package com.oauth.labapi.model;


import lombok.Data;

import java.util.List;

@Data
public class Region {
    private List<Choix> selctedPersons;
    private long cota;
    private int cpt;


}
