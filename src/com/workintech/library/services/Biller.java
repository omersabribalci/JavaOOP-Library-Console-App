package com.workintech.library.services;

import com.workintech.library.models.Book;
import com.workintech.library.models.Reader;

public interface Biller {
    void generateInvoice(Reader reader, Book book);
}