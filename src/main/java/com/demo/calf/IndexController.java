package com.demo.calf;

import com.demo.calf.data.CollectionDemo;
import com.demo.calf.data.MapDemo;
import com.demo.calf.service.EncryptService;
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

    @Autowired
    EncryptService encryptService;

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
        // mapDemo.Hashtable();
        encryptService.testEncryption(); // 测试AES加解密
    }

}
