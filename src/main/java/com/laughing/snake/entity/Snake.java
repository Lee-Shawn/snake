package com.laughing.snake.entity;

import com.laughing.snake.common.Constant;
import com.laughing.snake.common.Direction;

import java.util.LinkedList;

/**
 * @author : laughing
 * @create : 2021-08-24 23:23
 * @description : 蛇
 */
public class Snake {
    private final LinkedList<Node> snake = new LinkedList<>();

    /**
     * 判断蛇是否吃到食物
     * @param food 食物
     * @return 吃到食物结果
     */
    public boolean eat(Node food) {
        // 如果食物和蛇头碰撞，则将食物加入蛇的身体
        return isCollision(snake.getFirst(), food);
    }

    /**
     * 蛇头和食物是否碰撞
     * @param head 蛇头
     * @param food  食物
     * @return      碰撞
     */
    private boolean isCollision(Node head, Node food) {
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) <= Constant.NODE_SIZE;
    }

    /**
     * 蛇的移动
     * @param direction 方向
     * @return 移动之前的蛇尾
     */
    public Node move(Direction direction) {
        Node head = null;
        // 根据移动方向更新蛇的身体，将最后一个结点移动到第一个结点
        switch (direction) {
            case UP:
                // 超出边界，不让蛇移动
                if (this.getHead().getY() < Constant.NODE_SIZE) {
                    head = new Node(this.getHead().getX(), Constant.NODE_SIZE);
                    //return this.snake.getLast();
                } else {
                    head = new Node(this.getHead().getX(), this.getHead().getY() - Constant.NODE_SIZE);
                }
                break;
            case RIGHT:
                if (this.getHead().getX() > Constant.GAME_AREA_WIDTH - Constant.NODE_SIZE) {
                    head = new Node(Constant.GAME_AREA_WIDTH - Constant.NODE_SIZE, this.getHead().getY());
                    //return this.snake.getLast();
                } else {
                    head = new Node(this.getHead().getX() + Constant.NODE_SIZE, this.getHead().getY());
                }
                break;
            case DOWN:
                if (this.getHead().getY() > Constant.GAME_AREA_HEIGHT - Constant.NODE_SIZE) {
                    head = new Node(this.getHead().getX(), Constant.GAME_AREA_WIDTH - Constant.NODE_SIZE);
                    //return this.snake.getLast();
                } else {
                    head = new Node(this.getHead().getX(), this.getHead().getY() + Constant.NODE_SIZE);
                }
                break;
            case LEFT:
                if (this.getHead().getX() < Constant.NODE_SIZE) {
                    head = new Node(Constant.NODE_SIZE, this.getHead().getY());
                    //return this.snake.getLast();
                } else {
                    head = new Node(this.getHead().getX() - Constant.NODE_SIZE, this.getHead().getY());
                }
                break;
            default:
                break;
        }
        // 返回移动之前的尾部 Node
        Node last = this.snake.getLast();
        // 身体其他部位不动，头部新增一个结点，尾部删除结点
        this.snake.addFirst(head);
        this.snake.removeLast();
        //System.out.println("head:" + head.getX() + "," + head.getY());
        return last;
    }

    /**
     * 判断生成的食物有没有和蛇重合
     * @return 重合的结果
     */
    public boolean isCoincidence(Node node) {
        for (Node n : snake) {
            if (Math.abs(n.getX() - node.getX()) + Math.abs(n.getY() - node.getY()) < Constant.NODE_SIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断蛇头是否碰到身体
     * @param head 蛇头
     * @return 碰撞结果
     */
    public boolean isSelfCollision(Node head) {
        for (int i = 1; i < snake.size(); i++) {
            if (Math.abs(snake.get(i).getX() - head.getX()) +
                    Math.abs(snake.get(i).getY() - head.getY()) < Constant.NODE_SIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取蛇的头部
     * @return 蛇头
     */
    public Node getHead() {
        return snake.getFirst();
    }

    /**
     * 获取蛇的尾巴
     */
    public void addTail(Node node) {
        this.snake.addLast(node);
    }

    /**
     * 获取蛇的身体
     * @return 身体
     */
    public LinkedList<Node> getBody()  {
        return snake;
    }
}