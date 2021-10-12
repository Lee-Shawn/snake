package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-12 14:38
 * @description : frame配置
 */
public class FrameConfig implements Serializable {
    /**
     * 游戏标题
     */
    private final String title;

    /**
     * 游戏窗口宽度
     */
    private final int width;

    /**
     * 游戏窗口高度
     */
    private final int height;

    /**
     * 边框内边距
     */
    private final int border;

    /**
     * 图片左位移偏量
     */
    private final int padding;

    /**
     * 游戏区域格子大小
     */
    private final int interval;

    /**
     * 图层属性
     */
    private final List<LayerConfig> layerConfigs;

    /**
     * 按钮属性
     */
    private final ButtonConfig buttonConfig;

    public FrameConfig(Element frame) {
        this.title = frame.attributeValue("title");
        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.border = Integer.parseInt(frame.attributeValue("border"));
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        this.interval = Integer.parseInt(frame.attributeValue("interval"));

        // 获取窗体属性
        List<Element> layers = frame.elements("layer");
        layerConfigs = new ArrayList<>();
        // 获取所有窗体属性
        for (Element layer : layers) {
            LayerConfig config = new LayerConfig(
                    layer.attributeValue("className"),
                    Integer.parseInt(layer.attributeValue("x")),
                    Integer.parseInt(layer.attributeValue("y")),
                    Integer.parseInt(layer.attributeValue("w")),
                    Integer.parseInt(layer.attributeValue("h"))
            );
            layerConfigs.add(config);
        }
        // 初始化按钮属性
        buttonConfig = new ButtonConfig(frame.element("button"));
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

    public int getBorder() {
        return border;
    }

    public int getPadding() {
        return padding;
    }

    public int getInterval() {
        return interval;
    }

    public List<LayerConfig> getLayerConfigs() {
        return layerConfigs;
    }

    public ButtonConfig getButtonConfig() {
        return buttonConfig;
    }
}
