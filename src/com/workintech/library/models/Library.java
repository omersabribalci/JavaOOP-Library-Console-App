package com.workintech.library.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Long, Book> books;
    private Map<Long, Reader> readers;
    private Librarian librarian;

    public Library(Librarian librarian) {
        this.books = new HashMap<>();
        this.readers = new HashMap<>();
        this.librarian = librarian;
    }

    public Map<Long, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Long, Book> books) {
        this.books = books;
    }

    public Map<Long, Reader> getReaders() {
        return readers;
    }

    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public void addNewBook(Book newBook) {
        if (books.containsKey(newBook.getId())) {
            System.out.println("Bu ID ile zaten bir kitap var! Hatalı işlem.");
        } else {
            books.put(newBook.getId(), newBook);
        }

    }

    public void deleteBook(Book book) {
        if (books.containsKey(book.getId())) {
            books.remove(book.getId(), book);
        } else {
            System.out.println("Bu ID ile bir kitap bulunamadı! Hatalı işlem.");
        }
    }

    public void addNewReader(Reader newReader) {
        if (readers.containsKey(newReader.getId())) {
            System.out.println("Hata: Bu ID ile zaten kayıtlı bir okuyucu var!");
        } else {
            readers.put(newReader.getId(), newReader);
        }

    }

    public List<Book> getBooksByAuthor(Author author) {

        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }

        return result;
    }

    public void lendBook(Book book, Reader reader) {
        // guard tam olarak nasıl nerde olmalı?
        if (books.containsKey(book.getId()) && book.getStatus() == BookStatus.AVAILABLE && reader.getMemberRecord().hasLimit()) {
            InvoiceGenerator.getInstance().generateInvoice(reader, book); // fatura kesme.
            reader.addBorrowedBook(book); // kullanıcının kitap listesine ekleme ve hakkını güncelleme.
            book.changeOwner(reader);    // kitabın ownerı değiştirme.
        } else {
            System.out.println("Hata!");
        }
    }

}
