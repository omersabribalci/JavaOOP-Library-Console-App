package com.workintech.library.models;

import java.util.Map;

public class Author extends Person{

    public Author(long id, String name) {
        super(id, name);
    }

    @Override
    public Person whoYouAre() {
        return this;
    }

}
