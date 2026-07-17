package com.workintech.library.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Librarian extends Person{

    private String password;

    public Librarian(long id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    @Override
    public Person whoYouAre() {
        return this;
    }

    public boolean verifyMember(Reader reader, Map<Long, Reader> readers) {
        if (reader == null) {
            return false;
        }
        return readers.containsKey(reader.getId());
    }

    public double calculateFine(Book book) {
        LocalDate checkoutDate = book.getCheckoutDate();
        LocalDate now = LocalDate.now();

        long daysPassed = ChronoUnit.DAYS.between(checkoutDate, now);

        int allowedDays = 30;
        int finePerDay = 10;
        long overdueDays = Math.max(daysPassed - allowedDays, 0);

        return Math.toIntExact(overdueDays * finePerDay);
    }

}


