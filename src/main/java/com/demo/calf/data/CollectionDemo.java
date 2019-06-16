package com.demo.calf.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CollectionDemo {

    public void ArrayList(){

        /****** ArrayList ******/

        //create ArrayList
        List<String> strList = new ArrayList<>();

        System.out.println("ArrayList initial capacity :"+strList.size());

        //add elements
        strList.add("Hello");
        strList.add("world");
        strList.add(2,"!");
        System.out.println("ArrayList current capacity :"+strList.size());

        //modify ArrayList
        strList.set(0,"HELLO");
        strList.set(1,"WORLD");
        System.out.println("ArrayList current content :"+strList.toString());

        //get element
        String element = strList.get(0);
        System.out.println(element);

        //Iterator
        Iterator<String> iterator = strList.iterator();
        while(iterator.hasNext()){
            String strNext = iterator.next();
            System.out.println(strNext);
        }

        //for
        for(String str : strList){
            System.out.println(str);
        }

        // isEmpty & contains
        boolean isEmpty = strList.isEmpty();
        System.out.println("strList is empty ? " + isEmpty);
        boolean isContain = strList.contains("HELLO");
        System.out.println("strList contains hello ? " + isContain);

        // collection(ArrayList) to Array
        String[] strArray = strList.toArray(new String[]{});
        System.out.println("ArrayList(String) convert to Array(String) : " + strArray.toString());

        //remove element
        strList.remove(0);
        strList.remove("world");
        strList.clear();

        System.out.println("finally strList capacity:" + strList.size());
    }

}
