package com.laughing.snake.dao;


import com.laughing.snake.dto.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-19 14:32
 * @description : 文件
 */
public class DataFile implements Data {
    private final String filePath;

    public DataFile(HashMap<String, String> param) {
        this.filePath = param.get("path");
    }

    @Override
    public List<Player> loadData() {
        ObjectInputStream ois = null;
        List<Player> players = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            try {
                players = (List<Player>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        // 先取出数据
        List<Player> players = this.loadData();
        // 追加新纪录
        players.add(player);
        // 重新写入
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("春燕", 100));
        players.add(new Player("张影", 1024));
        players.add(new Player("孝静", 2048));
        players.add(new Player("琳儿", 4096));
        players.add(new Player("秋雨", 8192));
        // 重新写入
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/recode.dat"));
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
