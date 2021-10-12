package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : laughing
 * @create : 2021-10-04 20:58
 * @description : 游戏设置配置
 */
public class SettingConfig implements Serializable {
    /**
     * 游戏设置窗口标题
     */
    private final String title;

    /**
     * 游戏设置窗口宽度
     */
    private final int width;

    /**
     * 游戏设置窗口高度
     */
    private final int height;

    /**
     * 配置文件
     */
    private final Map<String, String> param;

    /**
     * 控制按钮映射方法名
     */
    private final List<MethodConfig> method;

    /**
     * 控制按钮配置属性
     */
    private final List<ControlConfig> controlConfigs;

    public SettingConfig(Element setting) {
        this.title = setting.attributeValue("title");
        this.width = Integer.parseInt(setting.attributeValue("width"));
        this.height = Integer.parseInt(setting.attributeValue("height"));

        // 配置文件
        this.param = new HashMap<>();
        List<Element> params = setting.elements("param");
        for (Element e : params) {
            this.param.put(e.attributeValue("key"), e.attributeValue("value"));
        }

        // 控制按钮映射方法名
        this.method = new ArrayList<>();
        List<Element> methods = setting.elements("method");
        for (Element e : methods) {
            MethodConfig config = new MethodConfig(e.attributeValue("name"));
            method.add(config);
        }

        // 控制按钮框
        controlConfigs = new ArrayList<>();
        List<Element> control = setting.elements("control");
        for (Element c : control) {
            ControlConfig config = new ControlConfig(
                    Integer.parseInt(c.attributeValue("index")),
                    Integer.parseInt(c.attributeValue("x")),
                    Integer.parseInt(c.attributeValue("y")),
                    Integer.parseInt(c.attributeValue("w")),
                    Integer.parseInt(c.attributeValue("h"))
            );
            controlConfigs.add(config);
        }
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public List<MethodConfig> getMethod() {
        return method;
    }

    public List<ControlConfig> getControlConfigs() {
        return controlConfigs;
    }
}
