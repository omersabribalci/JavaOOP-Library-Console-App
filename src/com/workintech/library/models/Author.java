package com.workintech.library.models;


public class Author extends Person{

    public Author(long id, String name) {
        super(id, name);
    }

    @Override
    public String whoYouAre() {
        return "Yazar: " + getName();
    }

}
