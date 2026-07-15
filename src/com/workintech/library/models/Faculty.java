package com.workintech.library.models;

import java.time.LocalDate;

public class Faculty extends MemberRecord{
    public Faculty(long memberId, LocalDate dateOfMembership, int numOfBooksIssued, int maxBookLimit) {
        super(memberId, dateOfMembership, numOfBooksIssued, maxBookLimit);
    }
}
