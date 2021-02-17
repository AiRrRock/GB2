package ru.geekbrains.java.level2.lesson2;

public class MyArraySizeException extends Exception {

    public MyArraySizeException(String message) {
        super(message);
    }

    public MyArraySizeException() {
        this("Array size ia not 4x4");
    }
}
