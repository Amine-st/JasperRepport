package com.oauth.labapi.utils;



import com.oauth.labapi.model.Facture;
import com.oauth.labapi.model.Operation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayPassed {

    public Map<Long,Long> facturesDayPassed(List<Facture> factures){
        Map<Long,List<Operation>> operations = factures.stream().collect(Collectors.toMap(Facture::getId,Facture::getOperations));
        Map<Long,Long> dayPassedFcture = new HashMap<>();
        operations.keySet().forEach(
                k-> {
                    dayPassedFcture.put(k,operationsDayPassed(operations.get(k)));

                }
        );
        return dayPassedFcture;
    }

    public  Long operationsDayPassed(List<Operation> operations){
        OperationTest o = new OperationTest();
        long dayPassed = 0;
        long d = 0 ;
        for(int j=0 ; j<operations.size()-1;j++) {
            if (operations.get(j).getTarget().compareTo("REJET") == 0) {

                d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(j).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(j + 1).getDateOperation()));

            }
            dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(operations.get(j).getDateOperation()), o.convertToLocalDateViaSqlDate(operations.get(j + 1).getDateOperation()));
        }
        return dayPassed  - d;
    }

    public static void main(String[] args) throws IOException, ParseException {

      /*  String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\menuProject\\api-lab\\src\\main\\java\\com\\oauth\\file\\Facture_lines.xlsx";
        OperationTest o = new OperationTest();
        List<Facture> factures = new ArrayList<>();
        List<Operation> oprs =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==1).collect(Collectors.toList());
        List<Operation> oprs2 =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().filter(operation -> operation.getId()==2).collect(Collectors.toList());

        Facture fac2 = new Facture();
        fac2.setId((long) 2);
        Facture fac = new Facture();
        fac.setId((long) 1);

        fac.setOperations(oprs);
        factures.add(fac);
        System.out.println(fac.getOperations());
        fac2.setOperations(oprs2);
        factures.add(fac2);

        DayPassed d = new DayPassed();
        Map<Long,Long> day = d.facturesDayPassed(factures);
        System.out.println(day);*/
    }
}
