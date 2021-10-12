package com.laughing.snake.dao;


import com.laughing.snake.dto.Player;
import com.laughing.snake.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-19 14:31
 * @description : 数据库
 */
public class DataBase implements Data{
    /**
     * 数据库连接器
     */
    private final Connection connection;

    /**
     * 预处理
     */
    private PreparedStatement preparedStatement;

    /**
     * 结果集
     */
    private ResultSet resultSet;

    /**
     * 查询语句
     */
    private final String load;

    /**
     * 插入语句
     */
    private final String save;

    public DataBase(HashMap<String, String> param) {
        this.connection = DBUtil.connection();
        this.load = param.get("load");
        this.save = param.get("save");
    }

    /**
     * 加载数据库数据
     * @return 玩家集合
     */
    @Override
    public List<Player> loadData() {
        List<Player> players = new ArrayList<>();
        try {
            preparedStatement = this.connection.prepareStatement(load);
            resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()) {
                players.add(new Player(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnect(preparedStatement, resultSet, connection);
        }
        return players;
    }

    /**
     * 保存数据到数据库
     * @param player 玩家
     */
    @Override
    public void saveData(Player player) {
        try {
            preparedStatement = this.connection.prepareStatement(save);
            preparedStatement.setObject(1, player.getName());
            preparedStatement.setObject(2, player.getPoint());
            preparedStatement.setObject(3, 1);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnect(preparedStatement, null, connection);
        }
    }
}
