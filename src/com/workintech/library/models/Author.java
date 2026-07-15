package com.workintech.library.models;

import java.util.List;

public class Author extends Person{

    private List<Book> books;

    public Author(long id, String name, List<Book> books) {
        super(id, name);
        this.books = books;
    }

    @Override
    public Person whoYouAre() {
        return this;
    }
}
