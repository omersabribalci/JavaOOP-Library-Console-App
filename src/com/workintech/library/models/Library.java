package com.workintech.library.models;

import java.time.LocalDate;
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
            System.out.println("Kitap kütüphaneye başarıyla eklendi!");
        }

    }

    public void deleteBook(Book book) {
        if (books.containsKey(book.getId())) {
            books.remove(book.getId(), book);
            System.out.println("Kitap kütüphaneden başarıyla silindi!");
        } else {
            System.out.println("Bu ID ile bir kitap bulunamadı! Hatalı işlem.");
        }
    }

    public void addNewReader(Reader newReader) {
        if (readers.containsKey(newReader.getId())) {
            System.out.println("Hata: Bu ID ile zaten kayıtlı bir okuyucu var!");
        } else {
            readers.put(newReader.getId(), newReader);
            System.out.println("Kullanıcı başarıyla eklendi!");

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
        if (!librarian.verifyMember(reader, this.readers)) {
            System.out.println("Hata: Kullanıcı sistemde kayıtlı değil! Lütfen önce kayıt olunuz.");
            return;
        }

        if (!books.containsKey(book.getId())) {
            System.out.println("Hata: Kitap Bulunamadı!");
            return;
        }

        if (book.getStatus() != BookStatus.AVAILABLE) {
            System.out.println("Hata: Kitap müsait değil!");
            return;
        }

        if (!reader.getMemberRecord().hasLimit()) {
            System.out.println("Hata: Üyenin kitap alma limiti dolmuştur!");
            return;
        }

        reader.addBorrowedBook(book); // kullanıcının kitap listesine ekleme ve hakkını güncelleme.
        book.changeOwner(reader);    // kitabın ownerını değiştirme.
        book.updateStatus(BookStatus.BORROWED); //update book status.
        book.setCheckoutDate(LocalDate.now()); //ödünç verme tarihini güncelle.
        InvoiceGenerator.getInstance().generateInvoice(reader, book); // fatura kesme.
        // ilerde ödeme yapıldıktan sonra işlemler gerçekleşsin..

        System.out.println("Ödünç işlemi başarılı.");
    }

    public void takeBookBack(Book book, Reader reader) {
        if (!reader.getBooks().contains(book)) {
            System.out.println("Hata: Bu kitap bu kullanıcı üzerinde değil!");
            return;
        }

        double fine = librarian.calculateFine(book);
        if (fine > 0) {
            System.out.println("Gecikme cezası tespit edildi. Tutar: " + fine + " TL");
        }

        reader.deleteBorrowedBook(book);
        book.changeOwner(null);
        book.updateStatus(BookStatus.AVAILABLE);
        book.setCheckoutDate(null);

        System.out.println("İade işlemi başarılı.");
    }

}
