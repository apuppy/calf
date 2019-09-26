package com.demo.calf;

import com.demo.calf.data.CollectionDemo;
import com.demo.calf.data.MapDemo;
import com.demo.calf.service.EncryptService;
import com.demo.calf.service.HttpService;
import com.demo.calf.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    HttpService httpService;

    @Autowired
    FileUtil fileUtil;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public void data() {
        // collectionDemo.ArrayList();
        // collectionDemo.LinkedList();
        // collectionDemo.HashSet();
        // collectionDemo.TreeSet();
        // collectionDemo.Queue();
        // mapDemo.Hashtable();
        encryptService.testEncryption(); // 测试AES加解密
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        // 获取URL中的请求路径(不包括域名与参数)
        String url = "https://www.pexels.com/sponsored_photos/8/small/?photo_id=2208836";
        String URLPath = httpService.getUrlPath(url); // 此例返回:"/sponsored_photos/8/small/"

        // 文件下载
        String downloadURL = "https://cdn.segmentfault.com/v-5d5e3c1d/global/img/banner-login/banner-bg.svg";
        String filename = "simple_download_test.svg";
        String filePath = fileUtil.getDownloadFilePath(filename);
        // httpService.ignoreHTTPSVerification(); // ignore https verification
        String fileSavePath = httpService.simpleDownload(downloadURL, filePath);
        if (StringUtils.isEmpty(fileSavePath)) {
            logger.error("下载出错");
        }
    }

}
