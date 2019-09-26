package com.demo.calf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.X509Certificate;

@Service
public class HttpService {

    private static final Logger logger = LoggerFactory.getLogger(HttpService.class);

    /**
     * 忽略HTTPS安全验证
     */
    public void ignoreHTTPSVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc;
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            /*HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };*/
            HostnameVerifier allHostsValid = (hostname, session) -> true;

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从URL下载文件保存到本地文件系统
     *
     * @param downloadURL  文件下载地址
     * @param saveFilename 文件保存路径
     * @return 文件保存路径
     */
    public String simpleDownload(String downloadURL, String saveFilename) {
        try {
            InputStream inputStream = new URL(downloadURL).openStream();
            Path saveFilePath = Paths.get(saveFilename);
            Files.copy(inputStream, saveFilePath, StandardCopyOption.REPLACE_EXISTING);
            return saveFilename;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }


    /**
     * 取出URL地址中的URL路径
     *
     * @param url URL地址
     * @return 返回URL地址中的路径
     */
    public String getUrlPath(String url) {
        String URLPath = "";
        try {
            URL urlResource = new URL(url);
            URLPath = urlResource.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URLPath;
    }

}
