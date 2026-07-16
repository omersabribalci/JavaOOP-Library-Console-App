package com.workintech.library.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerator implements Biller {
    private static InvoiceGenerator instance;

    private InvoiceGenerator() {
    }

    public static InvoiceGenerator getInstance() {
        if (instance == null) {
            instance = new InvoiceGenerator();
        }
        return instance;
    }

    @Override
    public void generateInvoice(Reader reader, Book book) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        System.out.println("-------------------------------------------------");
        System.out.println("            KİTAP KİRALAMA FATURASI           ");
        System.out.println("Tarih: " + date);
        System.out.println("Sayın: " + reader.getName() + " (Müşteri No: " + reader.getId() + ")");
        System.out.println("Ürün: " + book.getTitle());
        System.out.println("Tutar: " + book.getPrice() + " TL");
        System.out.println("-------------------------------------------------");
    }
}