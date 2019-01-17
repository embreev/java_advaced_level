package ru.breev.se.homework5;

import java.util.Dictionary;

public class App {
    private final static int SIZE = 10000000;
    private final static int DIMENSION = 1;
    private final static int H = SIZE / DIMENSION;
    private static int shift;
    private static float[] arrMain = new float[SIZE];
    private static float[][] arrTmp = new float[DIMENSION][];
    private static float sum = 0;

//    static Thread[] threads = new Thread[DIMENSION];

    private static void fillArrayDefault() {
        for (int i = 0; i < arrMain.length; i++) {
            arrMain[i] = 1;
        }
    }

    private static void fillArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void splitArray() {
        for (int i = 0; i < DIMENSION; i++) {
            System.arraycopy(arrMain, i * H, arrTmp[i][], 0, H);
        }
    }

    static void combineArray() {
        for (int i = 0; i < DIMENSION; i++) {
            System.arraycopy(arrTmp[i], 0, arrMain, i * H, H);
        }
    }

    static float sumElement(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            sum = +arr[i];
        }
        return sum;
    }

    static void printArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void getThreads() {
        Thread[] thread = new Thread[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            fillArray(arrTmp[DIMENSION][]);
            thread[i].start();
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        fillArrayDefault(); //заполняем единицами
        long a = System.currentTimeMillis();
        splitArray();
        getThreads();
        combineArray();
        System.out.println("Потоко выполнения: " + DIMENSION + ". Выполнился за " + String.valueOf(System.currentTimeMillis() - a) + "мс");
//        System.out.println(sumElement(arrMain));
    }
}