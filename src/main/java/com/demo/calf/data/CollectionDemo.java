package com.demo.calf.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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

    public void LinkedList(){
        List<String> linkedList = new LinkedList<>();
        System.out.println("LinkedList initial capacity : "+linkedList.size());

        // add element
        linkedList.add("My");
        linkedList.add("name");
        linkedList.add("is");
        linkedList.add("kevin");
        System.out.println("LinkedList current capacity : "+linkedList.size());

        // modify element
        linkedList.set(0,"my");
        linkedList.set(1,"nickname");
        System.out.println("LinkedList current content : "+linkedList.toString());

        // get element
        String element = linkedList.get(0);
        System.out.println("the first element : "+element);

        // iterator
        Iterator<String> iterator = linkedList.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

        // for
        for(String str : linkedList){
            System.out.println(str);
        }

        // isEmpty & contains
        boolean isEmpty = linkedList.isEmpty();
        boolean isContains = linkedList.contains("kevin");
        System.out.println(isEmpty);
        System.out.println(isContains);

        // remove element
        linkedList.remove(0);
        linkedList.remove("is");
        linkedList.clear();
        System.out.println("finally linkedList capacity : "+linkedList.size());
    }

}
