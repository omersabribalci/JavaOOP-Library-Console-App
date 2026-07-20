package com.workintech.library.ui;

import com.workintech.library.exceptions.BookLimitExceededException;
import com.workintech.library.exceptions.BookNotAvailableException;
import com.workintech.library.exceptions.BookNotFoundException;
import com.workintech.library.exceptions.MemberNotRegisteredException;
import com.workintech.library.models.*;
import com.workintech.library.services.InvoiceGenerator;
import com.workintech.library.services.Librarian;
import com.workintech.library.services.Library;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    private static Librarian librarian = new Librarian(1L, "Omer Balci", "1234", InvoiceGenerator.getInstance());
    private static Library library;

    public static void main(String[] args) {

        library = new Library(librarian);
        DataSeeder.seed(library);

        while (true) {

            System.out.println("""
                    Kütüphanemize Hoşgeldiniz!
                    0 - Exit
                    1 - Librarian
                    2 - User
                    """);

            String secim = readInput();

            switch (secim) {
                case "0" -> {
                    System.out.println("Sistemden çıkış yapılıyor...");
                    scanner.close();
                    return;
                }
                case "1" -> librarianMenu();
                case "2" -> userMenu();
                default -> System.out.println("Geçersiz seçim, tekrar deneyin.");
            }

        }
    }

    private static void userMenu() {
        Reader reader = identifyReader();
        if (reader == null) return;

        boolean back = false;
        while (!back) {
            System.out.println("""
                    -- Kullanıcı Menü (%s) --
                    0 - Geri dön
                    1 - Kitap ara
                    2 - Kitap ödünç al
                    3 - Kitap iade et
                    """.formatted(reader.getName()));

            String secim = readInput();

            switch (secim) {
                case "0" -> back = true;
                case "1"-> {
                    System.out.println("""
                            Lütfen bir aram türü seçiniz:
                            1- İsim ile arama
                            2- Yazar ismi ile arama
                            """);
                    String searchType = readInput();
                    switch (searchType) {
                        case "1" -> {
                            System.out.println("Lütfen kitap ismi giriniz.");
                            String searchText = readInput();
                            library.getBooksByTitle(searchText).forEach(System.out::println);
                        }
                        case "2" -> {
                            System.out.println("Lütfen yazar ismi giriniz.");
                            String searchText = readInput();
                            library.getBooksByAuthorName(searchText).forEach(System.out::println);
                        }
                        default -> System.out.println("Geçersiz seçim, tekrar deneyin.");
                    }
                }
                case "2" -> {
                    System.out.println("Kiralanacak kitap ID'si giriniz.");
                    long id = Long.parseLong(readInput());
                    try {
                        Book book = library.getBookById(id);
                        library.lendBook(book, reader);
                    } catch (MemberNotRegisteredException |
                             BookNotFoundException |
                             BookNotAvailableException |
                             BookLimitExceededException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "3" -> {
                    System.out.println("İade edilcek kitabın ID'sini giriniz.");
                    long id = Long.parseLong(readInput());
                    try {
                        Book book = library.getBookById(id);
                        library.takeBookBack(book, reader);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
    }

    private static Reader identifyReader() {
        System.out.println("""
                1 - Mevcut kullanıcı ile giriş
                2 - Yeni kayıt
                """);
        String secim = readInput();

        switch (secim) {
            case "1" -> {
                System.out.println("""
                        Lütfen ID giriniz.
                        """);
                long id = Long.parseLong(readInput());

                Reader user = library.getReaders().get(id);

                if (user != null) {
                    return user;
                } else {
                    System.out.println("Kayıt bulunamadı.");
                    return null;
                }
            }

            case "2" -> {
                System.out.println("İsim giriniz");
                String name = readInput();
                System.out.println("Adres giriniz");
                String address = readInput();
                System.out.println("Telefom numarası giriniz.");
                String telNo = readInput();
                System.out.println("""
                        Lütfen birini seçin:
                        1- Student
                        2- Faculty
                        """);
                String memberTypeChoice = readInput();
                Reader memberType = null;

                switch (memberTypeChoice) {
                    case "1" -> memberType = new Student(System.currentTimeMillis(), name, address, telNo);
                    case "2" -> memberType = new Faculty(System.currentTimeMillis(), name, address, telNo);
                    default -> System.out.println("Geçersiz seçim, kayıt yapılamadı.");
                }

                if (memberType != null) {
                    library.addNewReader(memberType);
                }

                return memberType;

            }
            default -> {
                System.out.println("Geçersiz seçim.");
                return null;
            }

        }
    }

    private static void librarianMenu() {
        System.out.println("Lütfen şifrenizi giriniz.");
        String password = readInput();
        if (librarian.verifyPassword(password)) {
            boolean back = false;
            while (!back) {
                System.out.println("""
                    -- Librarian Menü --
                    0 - Geri dön
                    1 - Kitap ekle
                    2 - Tüm kitapları listele
                    3 - Kitap sil
                    4 - Kitap güncelle
                    """);

                String secim = readInput();

                switch (secim) {
                    case "0" -> back = true;
                    case "1" -> {
                        long bookID = System.currentTimeMillis();
                        System.out.println("Kitap ismi giriniz");
                        String title = readInput();
                        System.out.println("Yazar ismi giriniz");
                        String authorName = readInput();
                        System.out.println("Fiyat giriniz.");
                        double price = Double.parseDouble(readInput());
                        System.out.println("Edition giriniz.");
                        String edition = readInput();
                        Author author = new Author(System.currentTimeMillis(), authorName);
                        Book book = new Book(bookID, author, title, price, edition, LocalDate.now());
                        try {
                            library.addNewBook(book);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case "2" -> library.getBooks().values().forEach(System.out::println);
                    case "3" -> {
                        System.out.println("Lütfen silinecek kitabın ID'sini giriniz.");
                        long id = Long.parseLong(readInput());
                        try {
                            Book book = library.getBookById(id);
                            library.deleteBook(book);
                        } catch (BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case "4" -> {
                        System.out.println("Lütfen güncellenecek kitabın ID'sini giriniz.");
                        long id = Long.parseLong(readInput());
                        System.out.println("Lütfen yeni title giriniz. (değiştirmek istemiyorsan boş bırak)");
                        String newTitle = readInput();
                        System.out.println("Yeni fiyat (değiştirmek istemiyorsan boş bırak):");
                        String priceInput = readInput();
                        double newPrice = priceInput.isEmpty() ? -1 : Double.parseDouble(priceInput);
                        System.out.println("Lütfen yeni edition giriniz. (değiştirmek istemiyorsan boş bırak)");
                        String newEdition = readInput();
                        try {
                            library.updateBookInfo(id, newTitle, newPrice, newEdition);
                        } catch (BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    default -> System.out.println("Geçersiz seçim, tekrar deneyin.");
                }
            }
        } else {
            System.out.println("Yanlış Şifre");
        }

    }

    private static String readInput() {
        return scanner.nextLine().trim();
    }
}