package com.workintech.library.models;

import java.time.LocalDate;

public class Journals extends Book{
    public Journals(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, price, edition, dateOfPurchase);
    }
}
