package com.oauth.labapi.utils;

import com.oauth.labapi.model.child;
import org.apache.poi.poifs.property.Child;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFromCsv {

   public  static  List<List<String>> read() throws IOException {
       List<List<String>> records = new ArrayList<>();
       try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\AMINE-ALAOUI\\Desktop\\api_lab\\JasperRepport\\src\\main\\resources\\static\\regions.csv"))) {
           String line;
           while ((line = br.readLine()) != null) {
               String[] values = line.split(",");
               records.add(Arrays.asList(values));
           }
       }
       return  records;
   }

   public  static List<child> getChild(String parent , List<child> childList){
       List<child> children = new ArrayList<>();

       childList.stream().forEach(child -> {
           if(child.getFils().startsWith(parent) && child.getFils().length() > parent.length()) children.add(child);
       });
       children.stream().forEach(child -> child.getFils().replace("\"",""));
       return children;
   }

   public static  boolean isPrefix(String item ,String elt){

       if(elt.startsWith(item) && elt.length() > item.length())
           return false;
           return true;
   }

   public static List<child> removeElement(String item , List<child> childList){

       List<child> list = childList.stream().filter(child -> isPrefix(item,child.getFils())).collect(Collectors.toList());




       return list;
   }

   public static  List<child>  childrenFromParent(String parent , List<child> childList){


       List<child> childrenList =getChild(parent,childList);
       List<child> list = childrenList;
       int size = childrenList.size();
       for(int i=0 ;i<size;i++){
           list = removeElement(childrenList.get(i).getFils(),list);

       }
       if (list.isEmpty()) childList.stream().forEach(child -> {
           if (child.getFils().equals(parent))
               child.setHasdata("false");
       });
       return list;

   }




    public static void main(String[] args) throws IOException {
       List<child> childList =new ArrayList<>();
       read().stream().forEach(strings -> childList.add(new child(strings.get(1),strings.get(3),strings.get(4).replace("\"", ""))));
       childList.remove(0);

       // List<child> childrenList =getChild("01.331.",childList);
        //System.out.println(childrenList);
        System.out.println(childList);
        System.out.println(childrenFromParent("01.151.09.19.",childList));
        System.out.println(childList);




    }
}
