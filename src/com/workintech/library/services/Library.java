package com.workintech.library.services;

import com.workintech.library.exceptions.BookNotFoundException;
import com.workintech.library.models.Author;
import com.workintech.library.models.Book;
import com.workintech.library.models.Reader;

import java.util.*;


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
        return Collections.unmodifiableMap(books);
    }

    public void setBooks(Map<Long, Book> books) {
        this.books = books;
    }

    public Map<Long, Reader> getReaders() {
        return Collections.unmodifiableMap(readers);
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

        if (newBook == null) {
            throw new IllegalArgumentException("Kitap bilgisi boş (null) olamaz!");
        }

        if (books.containsKey(newBook.getId())) {
            throw new IllegalArgumentException("Bu ID ile zaten bir kitap var! Ekleme işlemi başarısız.");
        } else {
            books.put(newBook.getId(), newBook);
            System.out.println(newBook.getTitle() + ", kütüphaneye başarıyla eklendi!");
        }

    }

    public void deleteBook(Book book) {
        if (books.containsKey(book.getId())) {
            books.remove(book.getId());
            System.out.println(book.getTitle() + " adlı kitap kütüphaneden başarıyla silindi!");
        } else {
            throw new BookNotFoundException("Bu ID ile bir kitap bulunamadı! Silme işlemi başarısız.");
        }
    }

    public void addNewReader(Reader newReader) {
        if (readers.containsKey(newReader.getId())) {
            throw new IllegalArgumentException("Bu ID ile zaten kayıtlı bir okuyucu var!");
        } else {
            readers.put(newReader.getId(), newReader);
            System.out.println(newReader.getName() + " adlı kullanıcı başarıyla eklendi!");
        }

    }

    // 1. ID'ye göre kitap arama..
    public Book getBookById(long id) {
        return librarian.searchBook(id, this.books);
    }

    // 2. İsme göre kitap arama..
    public List<Book> getBooksByTitle(String title) {
        return librarian.searchBook(title, this.books);
    }


    // 3. Yazar ismine göre kitap arama..
    public List<Book> getBooksByAuthorName(String authorName) {
        return librarian.searchBookByAuthorName(authorName, this.books);
    }


    public void lendBook(Book book, Reader reader) {
        librarian.issueBook(book, reader, this);
    }

    public void takeBookBack(Book book, Reader reader) {
        librarian.returnBook(book, reader, this);
    }

    public void updateBookInfo(long bookId, String newTitle, double newPrice, String newEdition) {
        librarian.updateBook(bookId, newTitle, newPrice, newEdition, this.books);
    }

    public List<Book> getBooksByCategory(Class<?> category) {
        return librarian.getBooksByCategory(category, this.books);
    }

}
