package com.demo.calf;

import com.demo.calf.data.CollectionDemo;
import com.demo.calf.data.MapDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    CollectionDemo collectionDemo;

    @Autowired
    MapDemo mapDemo;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public void data(){
        // collectionDemo.ArrayList();
        // collectionDemo.LinkedList();
        // collectionDemo.HashSet();
        // collectionDemo.TreeSet();
        // collectionDemo.Queue();
        mapDemo.Hashtable();
    }

}