package com.oauth.labapi.utils;

import com.oauth.labapi.model.Choix;
import com.oauth.labapi.model.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AffectationChoix {
    static Region region1 = new Region(1,20,0);
    static Region region2 = new Region(2,10,0);
    static Region region3 = new Region(3,10,0);
    static Region region4 = new Region(4,5,0);
    static Region region5 = new Region(5,5,0);
    static Region region6 = new Region(6,7,0);
    static Region region7 = new Region(7,3,0);
    static Region region8 = new Region(8,4,0);
    static Region region9 = new Region(9,3,0);
    static Region region10 = new Region(10,2,0);
    //static Region region11 = new Region(11,1,0);
    static Region region12 = new Region(12,30,0);


    public static List<List<String>> read() throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/choix1.csv"))) {
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


    public static  List<Choix> getListOneRegion(List<Choix> choixes,String region){
        List<Choix> choixList = new ArrayList<>();
         choixes.stream().forEach(choix -> {
             if(choix.getRegion().equals(region)){
                 choixList.add(choix);
             }
         });

        return choixList ;

    }

    public static  int getMinimum(List<Region> regionList){
        List<Region> list = regionList.stream().filter(region -> region.getCpt() != region.getCota()).collect(Collectors.toList());
        list =  list.stream().sorted(Comparator.comparingInt(Region::getCpt)).collect(Collectors.toList());
        return list.get(0).getId();

    }

    public static  boolean isExist(List<Choix> choixes,ChoixS choixS){
        for(Choix c : choixes){
            if(c.getIsSelected().equals(choixS))
              return true;
        }
        return false;
    }

    public static List<Region>  affectation(List<Choix> choixList,int cpt,ChoixS choix) {
        List<Region> regionList = new ArrayList<>();
        final int[] compteur = new int[55];
        compteur[0]=cpt;

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
        regionList.add(region1);
        regionList.add(region2);
        regionList.add(region3);
        regionList.add(region4);
        regionList.add(region5);
        regionList.add(region6);
        regionList.add(region7);
        regionList.add(region8);
        regionList.add(region9);
        regionList.add(region10);
        //regionList.add(region11);
        regionList.add(region12);
        List<Choix> choixRegion1 = getListOneRegion(choixList,"Region1");
        List<Choix> choixRegion2 =  getListOneRegion(choixList,"Region2");
        List<Choix> choixRegion3 =  getListOneRegion(choixList,"Region3");
        List<Choix> choixRegion4 =  getListOneRegion(choixList,"Region4");
        List<Choix> choixRegion5 =  getListOneRegion(choixList,"Region5");
        List<Choix> choixRegion6 =  getListOneRegion(choixList,"Region6");
        List<Choix> choixRegion7 =  getListOneRegion(choixList,"Region7");
        List<Choix> choixRegion8 =  getListOneRegion(choixList,"Region8");
        List<Choix> choixRegion9 =  getListOneRegion(choixList,"Region9");
        List<Choix> choixRegion10 =  getListOneRegion(choixList,"Region10");
       // List<Choix> choixRegion11 =  getListOneRegion(choixList,"Region11");
        List<Choix> choixRegion12 =  getListOneRegion(choixList,"Region12");

        region1.setListPersons(choixRegion1);
        region2.setListPersons(choixRegion2);
        region3.setListPersons(choixRegion3);
        region4.setListPersons(choixRegion4);
        region5.setListPersons(choixRegion5);
        region6.setListPersons(choixRegion6);
        region7.setListPersons(choixRegion7);
        region8.setListPersons(choixRegion8);
        region9.setListPersons(choixRegion9);
        region10.setListPersons(choixRegion10);
        //region11.setListPersons(choixRegion11);
        region12.setListPersons(choixRegion12);
        System.out.println(getListOneRegion(choixList,"Region12"));

        while (compteur[0]>0){
        regionList.stream().forEach(region -> {



                if(region.getId()==getMinimum(regionList) )
            {
                System.out.println(region.getId()+">>>>>>>>>>>>>>>>>>>>>>>>><"+region.getListPersons().get(0).getId());
                if (getKey(map, region.getListPersons().get(0).getChoix1()) != 0) {
                    region.getListPersons().get(0).setIsSelected(region.getListPersons().get(0).getChoix1());
                    minusKey(map, region.getListPersons().get(0).getChoix1());
                    region.getSelctedPersons().add(region.getListPersons().get(0));
                    region.getListPersons().remove(region.getListPersons().get(0));
                    region.setCpt(region.getCpt() + 1);
                    compteur[0]--;


                } else {
                    if (getKey(map, region.getListPersons().get(0).getChoix2()) != 0) {
                        region.getListPersons().get(0).setIsSelected(region.getListPersons().get(0).getChoix2());
                        minusKey(map, region.getListPersons().get(0).getChoix2());
                        region.getSelctedPersons().add(region.getListPersons().get(0));
                        region.getListPersons().remove(region.getListPersons().get(0));
                        compteur[0]--;
                        region.setCpt(region.getCpt() + 1);
                    } else {
                        if (getKey(map, region.getListPersons().get(0).getChoix3()) != 0) {
                            region.getListPersons().get(0).setIsSelected(region.getListPersons().get(0).getChoix3());
                            minusKey(map, region.getListPersons().get(0).getChoix3());
                            region.getSelctedPersons().add(region.getListPersons().get(0));
                            region.getListPersons().remove(region.getListPersons().get(0));
                            region.setCpt(region.getCpt() + 1);
                            compteur[0]--;

                        }
                    }

                }

                }



        });}


        return regionList;
    }

    public static List<Choix>  implimentation(List<Choix> choixes){


        return choixes;

    }



    public static void main(String[] args) throws IOException {
        List<Choix> childList = new ArrayList<>();


        read().stream().forEach(strings -> childList.add(new Choix(strings.get(0),strings.get(2),ChoixS.valueOf(strings.get(4)), ChoixS.valueOf(strings.get(5)), ChoixS.valueOf(strings.get(6)))));
        childList.remove(0);
        affectation(childList,20,ChoixS.CHOIX1).get(5).getSelctedPersons().stream().forEach(choix ->

        System.out.println(choix.getId()+","+choix.getIsSelected())
        );
        //System.out.println(affectation(childList,10,ChoixS.CHOIX1).get(0).getSelctedPersons());


    }
}
