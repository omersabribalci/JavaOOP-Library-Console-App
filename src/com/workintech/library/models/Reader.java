package com.workintech.library.models;

import java.util.Collections;
import java.util.Set;


public abstract class Reader extends Person {

    private Set<Book> books;
    private MemberRecord memberRecord;
    private String address;
    private String phoneNo;

    public Reader(long id, String name, Set<Book> books, MemberRecord memberRecord, String address, String phoneNo) {
        super(id, name);
        this.books = books;
        this.memberRecord = memberRecord;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books); // Dışarıdan sadece okunabilir, eleman eklenip silinemez!
    }


    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void addBorrowedBook(Book book) {
        books.add(book);
        getMemberRecord().incBookIssued();
    }

    public void deleteBorrowedBook(Book book) {
        books.remove(book);
        getMemberRecord().decBookIssued();
    }



}
