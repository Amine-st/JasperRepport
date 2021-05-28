package com.oauth.labapi.model;



import com.oauth.labapi.utils.ChoixS;
import lombok.Data;

import java.util.Date;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ChoixS getChoix1() {
        return choix1;
    }

    public void setChoix1(ChoixS choix1) {
        this.choix1 = choix1;
    }

    public ChoixS getChoix2() {
        return choix2;
    }

    public void setChoix2(ChoixS choix2) {
        this.choix2 = choix2;
    }

    public ChoixS getChoix3() {
        return choix3;
    }

    public void setChoix3(ChoixS choix3) {
        this.choix3 = choix3;
    }

    public ChoixS getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(ChoixS isSelected) {
        this.isSelected = isSelected;
    }
}
