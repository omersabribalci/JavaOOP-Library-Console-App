package com.workintech.library.models;

import java.time.LocalDate;

public class StudyBooks extends Book{
    public StudyBooks(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, price, edition, dateOfPurchase);
    }
}
