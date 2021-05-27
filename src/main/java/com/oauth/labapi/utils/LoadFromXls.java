package com.oauth.labapi.utils;


import com.oauth.labapi.model.Facture;
import com.oauth.labapi.model.Operation;
import com.oauth.labapi.model.OperationFacture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadFromXls {
    static Logger LOGGER = LoggerFactory.getLogger(LoadFromXls.class);
    public  Long day ;
    public static List<OperationFacture> getLines(String file, int sheet) throws IOException, ParseException {





        List<OperationFacture> lignes = new ArrayList<>();

        try {
            int i = 0;
            File initialFile = new File( file);
            InputStream targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workBook = new XSSFWorkbook(targetStream);
            XSSFSheet worksheet = workBook.getSheetAt(sheet);
            XSSFRow firstRow = worksheet.getRow(0);
            if (true) {
                for (int j = 0; j < 2; j++)
                    System.out.println(j + "-" + firstRow.getCell(j).getStringCellValue().toLowerCase() + ";");
            }
            i = 1;

            System.out.println("-------------" +worksheet.getLastRowNum());
            while (i < worksheet.getLastRowNum()+1 ) {
                XSSFRow row = worksheet.getRow(i++);


                if (row != null) {
                    OperationFacture operation = new OperationFacture();
                    String sDate =  row.getCell(5).toString();
                    DateFormate dateFormate =new DateFormate();

                    String id = row.getCell(0).toString();
                    String parent_id = row.getCell(6).toString();

                    operation.setId(new Double(Double.parseDouble(id)).longValue());
                   operation.setSource(row.getCell(2).toString());
                   operation.setBut(row.getCell(3).toString());
                   operation.setParent_opertion_id(new Double(Double.parseDouble(parent_id)).longValue());
                    operation.setDate_effet(dateFormate.getDate(sDate,"dd-MMM-yyyy"));


                   lignes.add(operation);



                }
            } // while
        } catch (Exception e) {
            LOGGER.error("failure to parse document: {}");
            return null;
        }
        return lignes;
    }








    public static void main(String[] args) throws ParseException, IOException {

        String CTP_USERS_FILE = "C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\resources\\static\\regions.csv";
        OperationTest o = new OperationTest();
        Facture facture = new Facture();
        List<OperationFacture> oprs =  LoadFromXls.getLines(CTP_USERS_FILE,0 ).stream().collect(Collectors.toList());

        System.out.println(oprs);
       // long da = d.facturesDayPassed(fac,"fournisseur","paiement facture");
       // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :"+da);

      /*  //String CTP_USERS_PATH = "C:\\Users\\chamd\\IdeaProjects\\menuProject\\api-lab\\src\\main\\java\\com\\oauth\\file";
        String CTP_USERS_FILE = "C:\\Users\\chamd\\IdeaProjects\\menuProject\\api-lab\\src\\main\\java\\com\\oauth\\file\\Facture_lines.xlsx";
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


        System.out.println(fac2.getOperations());

        Map<Long,List<Operation>> operations = factures.stream().collect(Collectors.toMap(Facture::getId,Facture::getOperations));
        operations.keySet().forEach(
                k-> {System.out.println(operations.get(k).size());


                }
        );


        Iterator<Map.Entry<Long,List<Operation>>> itr = operations.entrySet().iterator();
        Map<Long,Long> dayPassedFcture = new HashMap<>();

        while(itr.hasNext()){
            Map.Entry<Long,List<Operation>> entry = itr.next();
            long dayPassed = 0;
            long d = 0 ;

            for(int j=0 ; j<entry.getValue().size()-1;j++) {
                if (entry.getValue().get(j).getTarget().compareTo("REJET") == 0) {

                    d = d + o.verifyDate(o.convertToLocalDateViaSqlDate(entry.getValue().get(j).getDateOperation()), o.convertToLocalDateViaSqlDate(entry.getValue().get(j + 1).getDateOperation()));
                    System.out.println(">>>>>>>>>>"+d);
                }


                dayPassed = dayPassed + o.verifyDate(o.convertToLocalDateViaSqlDate(entry.getValue().get(j).getDateOperation()), o.convertToLocalDateViaSqlDate(entry.getValue().get(j + 1).getDateOperation()));
            }

            dayPassed = dayPassed-d;
            dayPassedFcture.put(entry.getKey(),dayPassed);

        }

        System.out.println("the day passed : "+dayPassedFcture);*/

    }
}
