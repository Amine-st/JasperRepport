package com.oauth.labapi.utils;

import com.oauth.labapi.model.Choix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class ChoixFromCsv {


    public static List<List<String>> read() throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\resources\\static\\choix1.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public static int getKey(Map<ChoixS, Integer> map, ChoixS choixS) {
        for (Map.Entry<ChoixS, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(choixS)) {
                return entry.getValue();
            }
        }
        return -1;

    }

    public static void minusKey(Map<ChoixS, Integer> map, ChoixS choixS) {
        for (Map.Entry<ChoixS, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(choixS)) {
                map.put(entry.getKey(), entry.getValue() - 1);
            }
        }


    }

    public static List<Choix> placeSelected(List<Choix> choixes) {

        Map<ChoixS, Integer> map = new HashMap<>();
        map.put(ChoixS.CHOIX1, 7);
        map.put(ChoixS.CHOIX2, 7);
        map.put(ChoixS.CHOIX3, 7);
        map.put(ChoixS.CHOIX4, 7);
        map.put(ChoixS.CHOIX5, 7);
        map.put(ChoixS.CHOIX6, 7);
        map.put(ChoixS.CHOIX7, 7);
        map.put(ChoixS.CHOIX8, 7);
        map.put(ChoixS.CHOIX9, 7);
        map.put(ChoixS.CHOIX10, 7);
        map.put(ChoixS.CHOIX11, 7);
        map.put(ChoixS.CHOIX12, 7);
        map.put(ChoixS.CHOIX13, 7);
        map.put(ChoixS.CHOIX14, 7);
       // List<Choix> choixList = choixes.stream().sorted(Comparator.comparingInt(Choix::getId)).collect(Collectors.toList());

        for (Choix choix : choixes) {

            if (getKey(map, choix.getChoix1()) != 0) {
                choix.setIsSelected(choix.getChoix1());
                minusKey(map, choix.getChoix1());

            } else {
                if (getKey(map, choix.getChoix2()) != 0) {
                    choix.setIsSelected(choix.getChoix2());
                    minusKey(map, choix.getChoix2());

                } else {
                    if (getKey(map, choix.getChoix3()) != 0) {
                        choix.setIsSelected(choix.getChoix3());
                        minusKey(map, choix.getChoix3());

                    }
                }

            }
        }
        return  choixes;
    }


    public static  List<Choix> getListOneRegion(List<Choix> choixes,String region,ChoixS choixS){
        List<Choix> choixList = new ArrayList<>();
         choixes.stream().forEach(choix -> {
             if(choix.getRegion().equals(region)){
                 choixList.add(choix);
             }
         });

        return choixList ;

    }
    public static  List<List<Choix>> getListsReion(List<Choix> choixList){

        return null;

    }
    public static int regionFirst(List<Choix> choixesR){
        return 5;

    }
    public static List<Choix> affectation(List<Choix> choixesR1,List<Choix> choixesR2,List<Choix> choixesR3,ChoixS choixS) {
        List<Choix> choixList = new ArrayList<>();
        boolean region1 = false;
        boolean region2 = false;
        boolean region3 = false;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<choixesR1"+choixesR1);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<choixesR2"+choixesR2);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<choixesR3"+choixesR3);
        for (int i = 0; i < 10 ; i++) {
            if (region1 == region2 == region3) {
                if (regionFirst(choixesR1) < regionFirst(choixesR2) && regionFirst(choixesR1) < regionFirst(choixesR3)) {
                    choixesR1.get(i).setIsSelected(choixS);
                    choixList.add(choixesR1.get(i));
                    region1 = true;

                } else {
                    if (regionFirst(choixesR2) < regionFirst(choixesR1) && regionFirst(choixesR2) < regionFirst(choixesR3)) {
                        choixesR2.get(i).setIsSelected(choixS);
                        choixList.add(choixesR2.get(i));
                        region2 = true;


                    } else {
                        choixesR3.get(i).setIsSelected(choixS);
                        choixList.add(choixesR3.get(i));
                        region2 = true;

                    }
                }

            } else {
                if (region1 == false) {
                    choixesR1.get(i).setIsSelected(choixS);
                    choixList.add(choixesR1.get(i));
                    region1 = true;

                } else {
                    if (region2 == false) {
                        choixesR2.get(i).setIsSelected(choixS);
                        choixList.add(choixesR2.get(i));
                        region2 = true;

                    } else {
                        if (region3 == false) {
                            choixesR3.get(i).setIsSelected(choixS);
                            choixList.add(choixesR3.get(i));
                            region3 = true;
                        }
                    }

                }


            }
        }
        return choixList;
    }

    public static List<Choix>  implimentation(List<Choix> choixes){
        List<Choix> choixListR1 = getListOneRegion(choixes,"Region1",ChoixS.CHOIX1);
        List<Choix> choixListR2 = getListOneRegion(choixes,"Region2",ChoixS.CHOIX1);
        List<Choix> choixListR3 = getListOneRegion(choixes,"Region2",ChoixS.CHOIX1);
        List<Choix> affectationList = affectation(choixListR1,choixListR2,choixListR3,ChoixS.CHOIX1);

        return affectationList;

    }



    public static void main(String[] args) throws IOException {
        List<Choix> childList = new ArrayList<>();


        read().stream().forEach(strings -> childList.add(new Choix(strings.get(0),strings.get(2),ChoixS.valueOf(strings.get(4)), ChoixS.valueOf(strings.get(5)), ChoixS.valueOf(strings.get(6)))));
        childList.remove(0);

        /*placeSelected(childList).stream().forEach(choix ->

        System.out.println(choix.getId()+","+choix.getIsSelected())
        );*/
        System.out.println(implimentation(childList));


    }
}
