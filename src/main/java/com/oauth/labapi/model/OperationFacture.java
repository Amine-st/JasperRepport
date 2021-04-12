package com.oauth.labapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class OperationFacture {
    private long id ;
    private String source ;
    private Long facture_id;
    private Date date_effet ;
    private String source_label ;
    private String target_label ;
    private String but ;
    private long parent_opertion_id;


}
