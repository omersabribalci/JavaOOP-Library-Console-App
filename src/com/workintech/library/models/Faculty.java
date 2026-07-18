package com.workintech.library.models;

import java.time.LocalDate;
import java.util.HashSet;

public class Faculty extends Reader {

    public static final int FACULTY_BOOK_LIMIT = 10;

    public Faculty(long id, String name, String address, String phoneNo) {
        super(
                id,
                name,
                new HashSet<Book>(),
                new MemberRecord(id, LocalDate.now(), 0, FACULTY_BOOK_LIMIT),
                address,
                phoneNo
        );
    }


    @Override
    public String whoYouAre() {
        return "Öğretmen: " + getName();
    }
}
