package com.application.assignment4.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class TextFormat {

    private static final Scanner scan = new Scanner(System.in);

    public static String textCenter(String text, int length) {
        int sz = text.length();
        return "-".repeat((length - sz) / 2) + text + "-".repeat((length - sz) / 2);
    }

    public static void pause() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        scan.nextLine();
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

}
