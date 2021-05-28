package com.oauth.labapi.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Region {
    private int id ;
    private List<Choix> selctedPersons=new ArrayList<>();
    private List<Choix> ListPersons;
    private int cota;
    private int cpt;
    private boolean notDamaged= true ;

    public Region(int id, int cota, int cpt) {
        this.id = id;
        this.cota = cota;
        this.cpt = cpt;
    }

    public boolean isNotDamaged() {
        return notDamaged;
    }

    public void setNotDamaged(boolean notDamaged) {
        this.notDamaged = notDamaged;
    }

    public List<Choix> getListPersons() {
        return ListPersons;
    }

    public void setListPersons(List<Choix> listPersons) {
        ListPersons = listPersons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Choix> getSelctedPersons() {
        return selctedPersons;
    }

    public void setSelctedPersons(List<Choix> selctedPersons) {
        this.selctedPersons = selctedPersons;
    }

    public int getCota() {
        return cota;
    }

    public void setCota(int cota) {
        this.cota = cota;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }
}
