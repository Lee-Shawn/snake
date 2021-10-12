package com.laughing.snake.dto;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-09-15 20:23
 * @description : 玩家
 */
public class Player implements Comparable<Player>, Serializable {
    private String name;

    private int point;

    public Player(String name, int point) {
        super();
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int compareTo(Player player) {
        return player.point - this.point;
    }
}
