package com.oauth.labapi.utils;


import com.oauth.labapi.model.Facture;
import com.oauth.labapi.model.Operation;
import com.oauth.labapi.model.OperationFacture;
import net.sf.jasperreports.engine.util.AdditionalEntryMap;
import org.sonatype.guice.bean.locators.EntryMapAdapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayPassedEtat {


    public List<Map<String, Long>> result(Map<Map<String, String>, Long> map) {

        List<Map<String, Long>> list = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {

        }

        return list;
    }

    public boolean trouve(List<Operation> operations, String source, String target) {
        int b = 0;
        int b2 = 0;
        for (int i = 0; i < operations.size() - 1; i++) {
            if (operations.get(i).getSource().compareTo(source) == 0) {
                b = 1;
                break;
            }

        }
        for (int i = 0; i < operations.size() - 1; i++) {
            if (operations.get(i).getTarget().compareTo(target) == 0) {
                b2 = 1;
                break;
            }

        }
        if (b == 1 & b2 == 1)
            return true;
        return false;
    }
   /* public Long facturesDayPassed(Facture facture, String source, String target){
        List<OperationFacture> operations = facture.getOperations().stream().collect(Collectors.toList());
        Long dayPassedFcture = 0L;
                    if(!trouve(operations,source,target))
                        return 0L;
                    dayPassedFcture = operationsDayPassed(operations,source,target);



        return dayPassedFcture;
    }*/

    public Long operationsDayPassed(List<OperationFacture> operations, String source, String target) {
        OperationTest o = new OperationTest();
        long dayPassed = 0;
        long d = 0;
        int indice = 0;
        int indice2 = 0;


        for (int i = 1; i < operations.size(); i++) {
            if (operations.get(i).getSource().compareTo(source) == 0) {
                indice = i;
                break;
            }
        }

        for (int k = operations.size() - 1; k >= 0; k--) {
            if (operations.get(k).getBut().compareTo(target) == 0) {
                indice2 = k;
                break;
            }
        }

        for (int j = indice; j < operations.size(); j++) {

            if (operations.get(j - 1).getBut().compareTo("FACTURE_TECHNIQUE_REJETEE") == 0 || operations.get(j - 1).getBut().compareTo("DOSSIER_PAIMENT_REJETE_FRNS") == 0) {

                d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(j - 1).getDate_effet()), o.convertToLocalDateViaSqlDate(operations.get(j).getDate_effet()));
            }
            dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(j - 1).getDate_effet()), o.convertToLocalDateViaSqlDate(operations.get(j).getDate_effet()));


            if (j == indice2) {
                System.out.println("//////////////////////////////////////////////////////  indice j = " +j);
                break;
            }
        }

        System.out.println("day lose " + d);
        System.out.println("day  " + dayPassed);

        return dayPassed - d;
    }

    public Long operationsDayPassed3(List<Operation> operations, String source, String target) {
        OperationTest o = new OperationTest();
        long dayPassed = 0;
        long d = 0;
        int indice = 1;


        if (operations.get(indice - 1).getTarget().compareTo("FACTURE_TECHNIQUE_REJETEE") == 0) {

            d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indice - 1).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(indice).getDateOperation()));

        }
        dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indice - 1).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(indice).getDateOperation()));


        operations.remove(operations.get(indice - 1));
        return dayPassed - d;
    }

    /*===============================================================================================================
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>using the parent id<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    ===============================================================================================================*/
    public Long operationsDayPassedNew(List<OperationFacture> operations, String source, String target) {
        OperationTest o = new OperationTest();
        long dayPassed = 0;
        long d = 0;
        int indice = 0;
        int indice2 = 0;
        int indiceTarget = 0;
        long id = 0L;

        //first id >>>>>>>>>>>>>>>>>>>>>
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).getSource().compareTo("INITIAL") == 0) {
                id = operations.get(i).getId();
                System.out.println("====================================id " +id);
                indice = i;
                break;
            }
        }
        //get indice of the operation with source equals source
        indice2 = indice;
        for (int j = 0; j < operations.size(); j++) {
            if (operations.get(indice2).getId() == operations.get(j).getParent_opertion_id()) {
                if (operations.get(j).getBut().compareTo(target) == 0) {
                    indiceTarget = j;
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>indiceTarget   " +indiceTarget);
                    break;

                }
                indice2 = j;
            }

        }
             id = operations.get(indiceTarget).getParent_opertion_id();

        for (int p = 0; p < operations.size(); p++) {

            if (id == operations.get(p).getId()) {
                if (operations.get(indiceTarget).getBut().compareTo("FACTURE_TECHNIQUE_REJETEE") == 0 || operations.get(p).getBut().compareTo("REJET_DOSSIER_DE_PAIEMENT_FOURNISSEUR") == 0) {

                    d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indiceTarget).getDate_effet()), o.convertToLocalDateViaSqlDate(operations.get(p).getDate_effet()));
                }
                dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indiceTarget).getDate_effet()), o.convertToLocalDateViaSqlDate(operations.get(p).getDate_effet()));


                if (operations.get(p).getBut().compareTo(target) == 0) {
                    break;
                }

                id = operations.get(p).getParent_opertion_id();
                indiceTarget = p;
                p = 0;
            }

        }

        return dayPassed - d;
    }

    //===============================================================================================================
//===============================================================================================================
    public Long facturesDayPassed2(Facture facture, String target) {
        List<Operation> operations = facture.getOperations().stream().collect(Collectors.toList());
        Long dayPassedFcture = 0L;

        dayPassedFcture = operationsDayPassed2(operations, target);


        return dayPassedFcture;
    }

    public Long operationsDayPassed2(List<Operation> operations, String target) {
        OperationTest o = new OperationTest();
        long dayPassed = 0;
        long d = 0;
        int indice = 0;
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).getTarget().compareTo(target) == 0) {
                indice = i;
                break;
            }
        }
        if (indice == 0) return 0L;

        if (operations.get(indice - 1).getTarget().compareTo("ST_REJET_DU_SERVICE_FAIT") == 0) {

            d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indice - 1).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(indice).getDateOperation()));

        }
        dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(indice - 1).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(indice).getDateOperation()));


        operations.remove(operations.get(indice - 1));
        return dayPassed - d;
    }

    public static void main(String[] args) throws IOException, ParseException {

        String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\menuProject\\api-lab\\src\\main\\java\\com\\oauth\\file\\Facture_lines.xlsx";
        OperationTest o = new OperationTest();
        List<Facture> factures = new ArrayList<>();
        List<OperationFacture> oprs = LoadFromXls.getLines(CTP_USERS_FILE, 0).stream().filter(operation -> operation.getId() == 1).collect(Collectors.toList());
        //  List<Operation> oprs2 =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==2).collect(Collectors.toList());





    }
}
