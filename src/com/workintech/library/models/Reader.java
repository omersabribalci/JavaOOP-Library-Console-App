package com.workintech.library.models;

import java.util.List;

public class Reader extends Person{

    private List<Book> books;
    private MemberRecord memberRecord;
    private String address;
    private String phoneNo;

    public Reader(long id, String name) {
        super(id, name);
    }


    @Override
    public Person whoYouAre() {
        return this;
    }
}
