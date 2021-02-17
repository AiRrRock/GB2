package ru.geekbrains.java.level2.lesson1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public void overcome(Sportsman sportsman) {
        sportsman.jump(height);
    }
}
