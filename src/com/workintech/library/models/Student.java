package com.workintech.library.models;

import java.time.LocalDate;

public class Student extends MemberRecord{
    public Student(long memberId, LocalDate dateOfMembership, int numOfBooksIssued, int maxBookLimit) {
        super(memberId, dateOfMembership, numOfBooksIssued, maxBookLimit);
    }
}
