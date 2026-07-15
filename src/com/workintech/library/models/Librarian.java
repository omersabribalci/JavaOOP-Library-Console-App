package com.workintech.library.models;

public class Librarian extends Person{

    private String password;

    public Librarian(long id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    @Override
    public Person whoYouAre() {
        return null;
    }
}
