package com.bin.utils;

import java.io.*;
import java.util.*;
import org.apache.commons.logging.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/14 6:19 PM
 */
public class PropertyReader {

    private static Log logger = LogFactory.getLog(PropertyReader.class);

    public static Properties read(String path) {
        InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(String.format("读取属性文件失败 : {%s}", path), e);
            throw new RuntimeException("读取属性文件失败");
        } finally {
            try {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("文件流关闭失败");
            }
        }
        return properties;
    }
}
