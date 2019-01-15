package ru.breev.se.HomeWork5;

public class App {
    final static int SIZE = 10000000;
    final static int H = SIZE / 2;
    static float[] arrMain = new float[SIZE];
    static float[] arrTmp1 = new float[H];
    static float[] arrTmp2 = new float[H];
    static float sum = 0;

    static void fillArrayDefault() {
        for (int i = 0; i < arrMain.length; i++) {
            arrMain[i] = 1;
        }
    }

    static void fillArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static void splitArray() {
        System.arraycopy(arrMain, 0, arrTmp1, 0, H);
        System.arraycopy(arrMain, H, arrTmp2, 0, H);
    }

    static void combineArray() {
        System.arraycopy(arrTmp1, 0, arrMain, 0, H);
        System.arraycopy(arrTmp2, 0, arrMain, H, H);
    }

    static float sumElement(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            sum =+ arr[i];
        }
        return sum;
    }

    static void printArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    static class MultiThread implements Runnable {
        float[] arr;

        public MultiThread(float[] arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            fillArray(arr);
        }
    }

    public static void main(String[] args) {
        fillArrayDefault();
        long a = System.currentTimeMillis();
        fillArray(arrMain);
        System.out.println("Однопоточный: " + String.valueOf(System.currentTimeMillis() - a));
        System.out.println(sumElement(arrMain));

        a = System.currentTimeMillis();
        splitArray();
        new Thread(new MultiThread(arrTmp1)).start();
        new Thread(new MultiThread(arrTmp2)).start();
        combineArray();
        System.out.println("Многопоточный: " + String.valueOf(System.currentTimeMillis() - a));
        System.out.println(sumElement(arrMain));
    }
}