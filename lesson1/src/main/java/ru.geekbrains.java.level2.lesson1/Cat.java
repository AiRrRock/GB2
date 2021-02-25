package ru.geekbrains.java.level2.lesson1;

public class Cat implements Sportsman {
    private String name;
    private int runLimit;
    private int jumpHeightLimit;
    private boolean canContinue = true;

    public Cat(String name, int runLimit, int jumpHeightLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpHeightLimit = jumpHeightLimit;
    }

    public void run(int distance) {
        if (distance < runLimit) {
            System.out.println(name + " successfully run the distance");
        } else {
            System.out.println(name + " failed to run the distance");
            canContinue = false;
        }
    }

    public void jump(int height) {
        if (height < jumpHeightLimit) {
            System.out.println(name + " successfully jumped");
        } else {
            System.out.println(name + " failed to jump");
            canContinue = false;
        }
    }


    public boolean canContinue() {
        return canContinue;
    }

    @Override
    public String toString() {
        return name;
    }
}
