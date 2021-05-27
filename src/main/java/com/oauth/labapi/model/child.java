package com.oauth.labapi.model;


import lombok.Data;

import java.util.Date;

@Data
public class child {
    private String name;
    private String hasdata;
    private String fils;

    public child(String name, String hasdata, String fils) {
        this.name = name;
        this.hasdata = hasdata;
        this.fils = fils;
    }
}
