package com.oauth.labapi.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class OperationTest {

//sending email when the facture is returned to fornisseur
   /*public boolean verifyFactureDay(List<Facture> factures){



        if(facture.getOperations()==null)
        System.out.println(facture.getOperations().get(0).getTarget());
        for(Operation o :facture.getOperations()){

            if(o.getTarget()==Etat.REJET & o.isEventEmail()==false)
                return true;
        }
       return false;
    }*/

   public long verifyDate(LocalDate d1,LocalDate d2) {
        //LocalDate date1 = LocalDate.parse(, DateTimeFormatter.ISO_LOCAL_DATE);
        //LocalDate date2 = LocalDate.parse("2018-06-28", DateTimeFormatter.ISO_LOCAL_DATE);
       Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
       long diffDays = diff.toDays();


       return diffDays;
   }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


 /*   public static void main(String[] args) {
        //OperationTest o =new OperationTest();


        LocalDate date1 = o.convertToLocalDateViaSqlDate(new Date(System.currentTimeMillis()));
        LocalDate date2 = o.convertToLocalDateViaSqlDate(new Date(System.currentTimeMillis()));

        System.out.println("d1"+date1+"d2"+date2);
    }*/

}