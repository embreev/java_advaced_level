package ru.breev.se.WorkingHours;

import java.util.Scanner;

public class App {
    static int wh;
    private static int calcHours(int param) {
        return 40 - 8 * param;
    }

    private static int getWorkingHours(DayOfWeek day) {
        return calcHours(day.ordinal());
    }

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        System.out.println("Enter weekly day:");
        final String wd = sc.nextLine().toUpperCase();

        try {
            wh = getWorkingHours(DayOfWeek.valueOf(wd));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error weekly day!");
            e.printStackTrace();
            System.exit(1);
        }
        if (wh > 0) {
            System.out.println("Working hours left: " + wh);
        } else {
            System.out.println("Weekend!!!");
        }
    }
}