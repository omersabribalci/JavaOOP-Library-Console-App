package com.workintech.library.models;

public interface Biller {
    void generateInvoice(Reader reader, Book book);
}