package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : laughing
 * @create : 2021-09-19 14:04
 * @description : 数据接口配置
 */
public class DataInterfaceConfig implements Serializable {
    private final String className;

    private final Map<String, String> param;

    public DataInterfaceConfig(Element config) {
        this.className = config.attributeValue("className");
        this.param = new HashMap<>();
        List<Element> labels = config.elements("param");
        for (Element label : labels) {
            this.param.put(label.attributeValue("key"), label.attributeValue("value"));
        }
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getParam() {
        return param;
    }
}
