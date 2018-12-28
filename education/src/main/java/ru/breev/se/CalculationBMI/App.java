package ru.breev.se.CalculationBMI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static float weight;
    static float height;

    private static float calcBMI(float weight, float height) {
        return weight / (height * height);
    }

    private static String checkBMI(float bmi) {
        if (bmi > 0 && bmi < 18.5) return "under";
        if (bmi >= 18.5 && bmi < 25) return "normal";
        if (bmi >= 25 && bmi < 30) return "over";
        if (bmi > 30) return "obese";
        return null;
    }

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        System.out.println("Enter weight (kg):");
        try {
            weight = sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Error weight!");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Enter height (m):");
        try {
            height = sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Error height!");
            e.printStackTrace();
            System.exit(1);
        }

        final float bmi = calcBMI(weight, height);
        final String result = checkBMI(bmi);

        System.out.println(bmi + " " + result);
    }
}