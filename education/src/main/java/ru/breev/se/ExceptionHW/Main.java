package ru.breev.se.ExceptionHW;

import java.util.Random;

public class Main {
    final static int ROW = 4;
    final static int COLUMN = 4;
    final static int DIMENSION = 4;

    static class MyArraySizeException extends Exception {
        MyArraySizeException(String msg) {
            super(msg);
        }
    }

    static class MyArrayDataException extends Exception {
        MyArrayDataException(String msg) {
            super(msg);
        }
    }

    static void checkSizeArray(String[][] arr) throws MyArraySizeException {
        if (arr.length > DIMENSION) {
            throw new MyArraySizeException("Error external array size!");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length > DIMENSION) {
                throw new MyArraySizeException("Error internal array size!");
            }
        }
    }

    static int transformElement(String element) throws MyArrayDataException {
        if (!element.isEmpty() && element.matches("^-?\\d+$")) {
            return Integer.parseInt(element);
        } else {
            throw new MyArrayDataException("Error element type!");
        }
    }

    static int[][] transformArray (String[][] arr) throws MyArrayDataException {
        int[][] newArr = new int[ROW][COLUMN];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                newArr[i][j] = transformElement(arr[i][j]);
            }
        }
        return newArr;
    }

    static int sumElementArray(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
                sum += arr[i][j];;
            }
            System.out.println("");
        }
        return sum;
    }

    static String[][] fillArray (int dimRow, int dimColumn) {
        final Random random = new Random();
        String[][] arr = new String[dimRow][dimColumn];
        for (int i = 0; i < dimRow; i++) {
            for (int j = 0; j < dimColumn; j++) {
                arr[i][j] = String.valueOf(random.nextInt(10));
            }

        }
        return arr;
    }

    public static void main(String[] args) throws MyArrayDataException {
        String[][] arr = fillArray(ROW, COLUMN);
        try {
            checkSizeArray(arr);
        }
        catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        System.out.println("Sum = " + sumElementArray(transformArray(arr)));
    }
}