package com.workintech.library.models;

import java.time.LocalDate;

public class Magazines extends Book{
    public Magazines(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        super(id, author, title, price, edition, dateOfPurchase);
    }

    @Override
    public int getAllowedDays() {
        return 7;
    }

}
