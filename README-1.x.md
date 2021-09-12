# 贪吃蛇 V1.X

## 设计

项目采用 **MVC(Model-View-Controller)** 设计模式

* 模型（model）：存储内容

* 视图（view）：显示内容

* 控制器（controller）：处理用户输入

### 模型层

首先需要实体，有了实体才能存储，操作数据等操作。这里的实体有**蛇**，**食物**和**游戏地图**。

#### Nood

`Nood`很简单，成员变量只需要有横纵坐标即可。

```java
public class Node {
    private int x;
    private int y;
}
```

#### Snake

`Snake`类中，蛇是由于吃了食物后，身体会变长，可以认为是身体就是由蛇头和食物组成。这样蛇的身体和食物可以用一个类来表示。蛇的身体会一直变长，需要一直做插入操作，且不需要查询操作，可以使用`LinkedList`数据结构来存储蛇。

```java
public class Snake {
    private LinkedList<Node> snake = new LinkedList<>();
}
```

蛇除了由一节节身体组成之外，蛇还有一些属性。蛇需要移动，然后在移动的过程中需要吃食物。

```java
public class Snake {
    public Node move();
    public boolean eat();
}
```

#### Scene

蛇和食物要放在游戏地图中才能运行，所以需要一个地图，在地图中创建蛇和食物。

```java
public class Scence {
    private int x;
    private int y;
    Snake snake;
    Nood food;
}
```

