package com.laughing.snake.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * @author : laughing
 * @create : 2021-10-04 16:06
 * @description : 读取配置文件工具
 */
public class PropertiesUtil {
    private static final Properties properties = new Properties();

    static {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/config/snake.properties"));
            properties.load(bufferedReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String property) {
        return properties.getProperty(property);
    }
}
