package com.workintech.library.models;

import java.time.LocalDate;
import java.util.HashSet;


public class Student extends Reader {

    public static final int STUDENT_BOOK_LIMIT = 5;

    public Student(long id, String name, String address, String phoneNo) {

        super(
                id,
                name,
                new HashSet<Book>(),
                new MemberRecord(id, LocalDate.now(), 0, STUDENT_BOOK_LIMIT),
                address,
                phoneNo
        );
    }

    @Override
    public String whoYouAre() {
        return "Öğrenci: " + getName();
    }
}
