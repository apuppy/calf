package com.demo.calf.data;

import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.Hashtable;

@Service

public class MapDemo {

    public String Hashtable(){
        Hashtable balance = new Hashtable();
        Enumeration names;
        String str;
        double bal;

        balance.put("PHP",new Double(3434.34));
        balance.put("Java",new Double(123.22));
        balance.put("Python",new Double(1378.00));
        balance.put("Golang",new Double(99.22));
        balance.put("Kotlin",new Double(-19.08));

        names = balance.keys();
        System.out.println(names);

        while(names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + " : " + balance.get(str));
        }

        bal = ((Double)balance.get("Java")).doubleValue();
        balance.put("Java",new Double(bal + 1000));
        System.out.println("Java's new balance : " + balance.get("Java"));

        return "";
    }

}
