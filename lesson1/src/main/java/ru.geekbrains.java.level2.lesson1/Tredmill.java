package ru.geekbrains.java.level2.lesson1;

public class Tredmill implements Obstacle {
    private int length;

    public Tredmill(int length) {
        this.length = length;
    }


    public void overcome(Sportsman sportsman) {
        sportsman.run(length);
    }
}
