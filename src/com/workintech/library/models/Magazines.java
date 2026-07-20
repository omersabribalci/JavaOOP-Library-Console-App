package com.workintech.library.models;

import java.time.LocalDate;

public class Magazines extends Book{

    public static final int ALLOWED_DAYS = 7;


    public Magazines(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, price, edition, dateOfPurchase);
    }

    @Override
    public int getAllowedDays() {
        return ALLOWED_DAYS;
    }

}
