package com.laughing.snake.dao;


import com.laughing.snake.dto.Player;

import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-19 14:30
 * @description : 数据持久层接口
 */
public interface Data {
    /**
     * 读取数据
     */
    List<Player> loadData();

    /**
     * 存储数据
     */
    void saveData(Player player);
}
