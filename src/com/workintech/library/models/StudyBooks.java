package com.workintech.library.models;

import java.time.LocalDate;

public class StudyBooks extends Book{

    public static final int ALLOWED_DAYS = 14;


    public StudyBooks(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, price, edition, dateOfPurchase);
    }

    @Override
    public int getAllowedDays() {
        return ALLOWED_DAYS;
    }
}
