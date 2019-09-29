package com.demo.calf.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileParserService {

    private static final Logger logger = LoggerFactory.getLogger(FileParserService.class);

    /**
     * 解析文档内容
     *
     * @param filename 文件路径
     * @return 文档中的文本内容
     */
    public String parse(String filename) {
        String text = "";
        try {
            Metadata metadata = new Metadata();
            text = parseUsingAutoDetect(filename, metadata);
            logger.info("Parsed metadata: " + metadata);
            logger.info("Parsed text content: " + text);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return text;
    }

    /**
     * 检测解析器是否支持文件类型
     *
     * @param filename 文件路径
     * @return 支持返回true, 否则返回false
     */
    public boolean isSupportedFileType(String filename) {
        boolean isSupport = false;
        // 获取当前文件类型
        String fileType = detectFileType(filename);
        // 获取支持的文件类型
        Set<MediaType> supportedTypes = getSupportedTypes();

        if (StringUtils.isNotEmpty(fileType)) {
            isSupport = checkSupportType(supportedTypes, fileType);
        }
        if (!isSupport) {
            logger.error("Parser does not support file type : " + fileType);
        }
        return isSupport;
    }

    /**
     * 使用自动解析类解析文档内容
     *
     * @param filename 文件名
     * @param metadata 文件信息
     * @return 返回解析后的文档内容
     * @throws Exception 异常抛出
     */
    private String parseUsingAutoDetect(String filename, Metadata metadata) throws Exception {
        logger.info("Handling using AutoDetectParser: [" + filename + "]");
        AutoDetectParser parser = getAutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        TikaInputStream stream = TikaInputStream.get(Paths.get(filename), metadata);
        parser.parse(stream, handler, metadata, new ParseContext());
        stream.close();
        return handler.toString();
    }

    /**
     * 检测文件类型
     *
     * @param filename 文件路径
     * @return 文件MIME类型
     */
    private String detectFileType(String filename) {
        String fileType = null;
        try {
            File inputFile = new File(filename);
            fileType = new Tika().detect(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileType;
    }

    /**
     * 检查当前文件类型是否存在于解析器支持的类型列表里
     *
     * @param supportedTypes 解析器支持的文件类型列表
     * @param fileType       当前文件类型
     * @return 支持返回true, 否则返回false
     */
    private boolean checkSupportType(Set<MediaType> supportedTypes, String fileType) {
        for (MediaType mediaType : supportedTypes) {
            String type = mediaType.toString();
            if (type.equals(fileType) && canSupportMainType(mediaType.getType())) {
                logger.info("media type : " + type);
                logger.info("detect type: " + fileType);
                return true;
            }
        }
        return false;
    }

    /**
     * 获取tika支持的文件格式
     *
     * @return tika解析器支持的文件格式
     */
    private Set<MediaType> getSupportedTypes() {
        AutoDetectParser parser = getAutoDetectParser();
        return parser.getSupportedTypes(new ParseContext());
    }

    /**
     * 是否支持此主文件类型
     *
     * @param mainType 主文件类型
     * @return 支持返回true, 否则返回false
     */
    private boolean canSupportMainType(String mainType) {
        Set<String> limitTypes = limitTypes(); // 限制只处理文本类文件
        return limitTypes.contains(mainType);
    }

    /**
     * 限制文件类型为文本类文件
     *
     * @return 文件主类型
     */
    private Set<String> limitTypes() {
        Set<String> types = new HashSet<>();
        types.add("application");
        types.add("text");
        return types;
    }

    /**
     * 获取自动检测解析器
     *
     * @return 自动检测解析器
     */
    private AutoDetectParser getAutoDetectParser() {
        TikaConfig tikaConfig = getConfig();
        return new AutoDetectParser(tikaConfig);
    }

    /**
     * 获取tika配置
     *
     * @return TikaConfig
     */
    private TikaConfig getConfig() {
        return TikaConfig.getDefaultConfig();
    }

}
