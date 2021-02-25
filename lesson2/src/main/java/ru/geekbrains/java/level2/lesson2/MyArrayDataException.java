package ru.geekbrains.java.level2.lesson2;

public class MyArrayDataException extends Exception {
    private int i;
    private int j;
    private String value;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getValue() {
        return value;
    }

    public MyArrayDataException(int i, int j, String value) {
        super("Expected to have an int at [" + i + "," + j + "] but got [" + value + "]");
        this.i = i;
        this.j = j;
        this.value = value;
    }
}
