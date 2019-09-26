package com.demo.calf.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileUtil {

    @Value("${file.save.dir:}")
    private String fileSaveDir;

    /**
     * 返回文件下载路径
     *
     * @param filename 文件名
     * @return 文件下载路径
     */
    public String getDownloadFilePath(String filename) {
        return getDir(fileSaveDir) + filename;
    }

    /**
     * 统一处理目录，以"/"结束
     *
     * @param dir 目录参数
     * @return 以"/"结尾的目录路径
     */
    private String getDir(String dir) {
        return dir.replaceAll("/+$", "") + "/";
    }

}
