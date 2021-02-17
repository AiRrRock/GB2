package ru.geekbrains.java.level2.lesson2;


public class Main {
    public static void main(String[] args) {
        String[][] wrongSizeArray = new String[5][5];
        try {
            System.out.println(sumArray(wrongSizeArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            // Done to stop IDE nagging that catch branches are the same
            System.out.println("Incorrect value is" + e.getValue());
        }


        String[] correctRow = {"2", "3", "4", "5"};
        String[][] correctArray = new String[4][];
        for (int i = 0; i < 4; i++) {
            correctArray[i] = correctRow;
        }
        try {
            System.out.println(sumArray(correctArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] arrayWithIncorrectRowSizes = new String[4][];
        String[] incorrectRow = {"2", "3", "4", "5", "6"};

        for (int i = 0; i < 4; i++) {
            if (i > 0 && i % 2 == 0){
                arrayWithIncorrectRowSizes[i] = incorrectRow;
            } else {
                arrayWithIncorrectRowSizes[i] = correctRow;
            }
        }

        try {
            System.out.println(sumArray(arrayWithIncorrectRowSizes));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        correctArray[0][2] = "AAAAAAAAAAAAAA";
        try {
            System.out.println(sumArray(correctArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException();
        }
        for (String[] subArray : array) {
            if (subArray.length != 4) {
                throw new MyArraySizeException();
            }
        }
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    counter += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }

        return counter;


    }
}
