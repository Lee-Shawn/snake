package com.laughing.snake.config;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-10-06 17:11
 * @description : 控制按钮方法映射配置
 */
public class MethodConfig implements Serializable {
    private final String name;

    public MethodConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
