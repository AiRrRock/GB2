package ru.geekbrains.java.level2.lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr;

    public static void main(String[] args) throws InterruptedException {
        // Part 1.
        singleThreadedCalculation();
        //Part 2
        multiThreadedCalculate();

    }

    private static class MyRunnable implements Runnable {

        float[] array;

        public MyRunnable(float[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            for (int i = 0; i < array.length; i++) {
                array[i] = calculate(array[i], i);
            }
        }

        private float calculate(float number, int index) {
            return (float) (number * Math.sin(0.2f + index / 5.0) * Math.cos(0.2f + index / 5.0) * Math.cos(0.4f + index / 2.0));
        }
    }

    private static void singleThreadedCalculation() throws InterruptedException {
        initArray();
        System.out.println("Single threaded execution");
        long start = System.currentTimeMillis();
        Thread single = new Thread(new MyRunnable(arr));
        single.start();
        single.join();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void multiThreadedCalculate() throws InterruptedException {
        initArray();
        System.out.println("Two threaded execution");

        long start = System.currentTimeMillis();
        float[] first = new float[h];
        float[] second = new float[h];

        System.arraycopy(arr, 0, first, 0, h);
        System.arraycopy(arr, h, second, 0, h);
        Thread t1 = new Thread(new MyRunnable(first));
        Thread t2 = new Thread(new MyRunnable(second));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.arraycopy(first, 0, arr, 0, h);
        System.arraycopy(second, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - start);
    }


    private static void initArray() {
        arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
    }

}
