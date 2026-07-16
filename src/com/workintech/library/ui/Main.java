package com.workintech.library.ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Kütüphanemize Hoşgeldiniz!\n" +
                    "Lütfen rol seçiniz.\n" +
                    "0 - Exit\n" +
                    "1 - Librarian\n" +
                    "2 - User\n");

            int secim = scanner.nextInt();

            switch (secim) {
                case 0:
                    System.out.println("Sistemden çıkış yapılıyor...");
                    scanner.close();
                    return;
                case 1:
                    System.out.println("librarian menu\n" +
                            "1-\n" +
                            "2-\n"
                    );
                    int librarianSecim = scanner.nextInt();
                    break;

                case 2:
                    System.out.println("user menu\n" +
                            "1-\n" +
                            "2-\n"
                    );
                    int userSecim = scanner.nextInt();
                    break;
            }

        }
    }
}