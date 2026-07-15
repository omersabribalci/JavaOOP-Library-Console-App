package com.workintech.library.models;

import java.time.LocalDate;

public class Magazines extends Book{
    public Magazines(long id, Author author, String title, Reader owner, double price, BookStatus status, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, owner, price, status, edition, dateOfPurchase);
    }
}
