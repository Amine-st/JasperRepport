package com.oauth.labapi.model;



import com.oauth.labapi.utils.ChoixS;
import lombok.Data;

import java.util.Date;

@Data
public class Choix {
    private String id;
    private String region;
    private ChoixS choix1;
    private ChoixS choix2;
    private ChoixS choix3;
    private ChoixS isSelected = ChoixS.RIEN;

    public Choix(String id, String region, ChoixS choix1, ChoixS choix2, ChoixS choix3) {
        this.id = id;
        this.region = region;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
    }
}
