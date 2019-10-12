package com.demo.calf;

import com.demo.calf.data.CollectionDemo;
import com.demo.calf.data.MapDemo;
import com.demo.calf.service.FileParserService;
import com.demo.calf.service.EncryptService;
import com.demo.calf.service.HttpService;
import com.demo.calf.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import com.demo.calf.utils.QRCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

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

    @Autowired
    FileParserService fileParserService;

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
        /*
        String url = "https://www.pexels.com/sponsored_photos/8/small/?photo_id=2208836";
        String URLPath = httpService.getUrlPath(url); // 此例返回:"/sponsored_photos/8/small/"
        */

        // 文件下载
        /*
        String downloadURL = "https://cdn.segmentfault.com/v-5d5e3c1d/global/img/banner-login/banner-bg.svg";
        String filename = "simple_download_test.svg";
        String filePath = fileUtil.getDownloadFilePath(filename);
        // httpService.ignoreHTTPSVerification(); // ignore https verification
        String fileSavePath = httpService.simpleDownload(downloadURL, filePath);
        if (StringUtils.isEmpty(fileSavePath)) {
            logger.error("下载出错");
        }
        */

        // 文档解析,利用tika解析文档，返回文本文件内容
        String filename = "/home/hongde/code/assets/hello.pptx";
        boolean supportType = fileParserService.isSupportedFileType(filename); // 文件类型是否支持
        if (supportType) {
            String fileContent = fileParserService.parse(filename);
            logger.info("" + fileContent);
        }

    }


    /**
     * 根据url生成普通二维码
     */
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
        // test url : http://localhost:9090/createCommonQRCode?url=https://news.ycombinator.com/news
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 根据url生成带有logo二维码
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        // test url : http://localhost:9090/createLogoQRCode?url=http://sf.gg
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //使用工具类生成二维码
            QRCodeUtil.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

}
