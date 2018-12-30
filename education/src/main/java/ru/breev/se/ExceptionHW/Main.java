package ru.breev.se.ExceptionHW;

public class Main {
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
        if (arr.length != 4) {
            throw new MyArraySizeException("Error array size!");
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
        int[][] newArr = new int[arr.length][arr.length];
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
            for (int j = 0; j < arr.length; j++) {
                sum += arr[i][j];;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws MyArrayDataException {
        final String[][] arr = {{"a", "2", "3", "4"}, {"5", "6", "7", "8"}, {"1", "3", "5", "7"}, {"2", "4", "6", "8"}};
        try {
            checkSizeArray(arr);
        }
        catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        System.out.println("Sum = " + sumElementArray(transformArray(arr)));
    }
}