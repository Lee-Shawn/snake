package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-09-19 14:02
 * @description : 数据配置
 */
public class DataConfig implements Serializable {
    private final int maxRow;

    private final DataInterfaceConfig dataBase;

    private final DataInterfaceConfig dataFile;

    public DataConfig(Element data) {
        this.maxRow = Integer.parseInt(data.attributeValue("maxRow"));
        this.dataBase = new DataInterfaceConfig(data.element("dataBase"));
        this.dataFile = new DataInterfaceConfig(data.element("dataFile"));
    }

    public int getMaxRow() {
        return maxRow;
    }

    public DataInterfaceConfig getDataBase() {
        return dataBase;
    }

    public DataInterfaceConfig getDataFile() {
        return dataFile;
    }
}
