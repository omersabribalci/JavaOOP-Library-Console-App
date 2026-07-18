package com.workintech.library.models;

import com.workintech.library.exceptions.BookLimitExceededException;
import com.workintech.library.exceptions.BookNotAvailableException;
import com.workintech.library.exceptions.BookNotFoundException;
import com.workintech.library.exceptions.MemberNotRegisteredException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Librarian extends Person {

    private String password;
    private Biller biller;
    private static final int FINE_PER_DAY = 10;

    public Librarian(long id, String name, String password, Biller biller) {
        super(id, name);
        this.password = password;
        this.biller = biller;
    }

    @Override
    public String whoYouAre() {
        return "Görevli Kütüphaneci: " + getName();
    }

    public List<Book> searchBook(String title, Map<Long, Book> books) {
        if (title == null || title.trim().isEmpty()) return new ArrayList<>();

        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle() != null && book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }


    public Book searchBook(long id, Map<Long, Book> books) {
        return books.get(id);
    }

    public List<Book> searchBook(Author author, Map<Long, Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (Objects.equals(book.getAuthor(), author)) {
                result.add(book);
            }
        }
        return result;
    }


    public void issueBook(Book book, Reader reader, Library library) {

        if (book == null) {
            throw new IllegalArgumentException("Kitap bilgisi boş (null) olamaz!");
        }


        if (!verifyMember(reader, library.getReaders())) {
            throw new MemberNotRegisteredException("Kullanıcı sistemde kayıtlı değil! Lütfen önce kayıt olunuz.");
        }

        if (!library.getBooks().containsKey(book.getId())) {
            throw new BookNotFoundException("Kitap Bulunamadı!");
        }

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new BookNotAvailableException("Kitap müsait değil, daha önce başkası tarafından alınmış!");
        }

        if (!reader.getMemberRecord().hasLimit()) {
            throw new BookLimitExceededException("Üyenin kitap alma limiti dolmuştur!");
        }

        reader.addBorrowedBook(book); // kullanıcının kitap listesine ekleme ve hakkını güncelleme.
        book.changeOwner(reader);    // kitabın ownerını değiştirme.
        book.setStatus(BookStatus.BORROWED); //update book status.
        book.setCheckoutDate(LocalDate.now()); //ödünç verme tarihini güncelle.
        createBill(reader, book);
        // ilerde ödeme yapıldıktan sonra işlemler gerçekleşsin..

        System.out.println("Ödünç işlemi başarılı.");
    }

    public void returnBook(Book book, Reader reader, Library library) {

        if (book == null) {
            throw new IllegalArgumentException("Kitap bilgisi boş (null) olamaz!");
        }


        if (!reader.getBooks().contains(book)) {
            throw new BookNotFoundException("Bu kitap bu kullanıcı üzerinde değil, iade edilemez!");
        }

        double fine = calculateFine(book);
        double refundAmount = book.getPrice() - fine;

        if (fine > 0) {
            System.out.println("Gecikme cezası tespit edildi. Tutar: " + fine + " TL");
        }
        if (refundAmount > 0) {
            System.out.println("Kullanıcıya iade edilecek tutar: " + refundAmount + " TL");
        } else {
            double ekOdeme = Math.abs(refundAmount);
            System.out.println("Gecikme cezası, kitap bedelini aşmıştır. İade yapılamaz. Ek ödeme (Borç): " + ekOdeme + " TL");

            reader.getMemberRecord().addFine(ekOdeme);
            System.out.println("Kullanıcının güncel borcu: " + reader.getMemberRecord().getUnpaidFines() + " TL");
        }

        reader.deleteBorrowedBook(book);
        book.changeOwner(null);
        book.setStatus(BookStatus.AVAILABLE);
        book.setCheckoutDate(null);

        System.out.println("İade işlemi başarılı.");

        // ilerde ek ödeme gerekliyse ve yapıldıysa işlemler tamamlansın.
    }

    public void createBill(Reader reader, Book book) {
        this.biller.generateInvoice(reader, book);
    }


    public boolean verifyMember(Reader reader, Map<Long, Reader> readers) {
        if (reader == null) {
            return false;
        }
        return readers.containsKey(reader.getId());
    }

    public double calculateFine(Book book) {
        LocalDate checkoutDate = book.getCheckoutDate();

        if (checkoutDate == null) {
            return 0;
        }

        LocalDate now = LocalDate.now();

        long daysPassed = ChronoUnit.DAYS.between(checkoutDate, now);

        int allowedDays = book.getAllowedDays();
        long overdueDays = Math.max(daysPassed - allowedDays, 0);

        return Math.toIntExact(overdueDays * FINE_PER_DAY );
    }

    public void updateBook(long bookId, String newTitle, double newPrice, String newEdition, Map<Long, Book> books) {
        Book bookToUpdate = searchBook(bookId, books);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(newTitle);
            bookToUpdate.setPrice(newPrice);
            bookToUpdate.setEdition(newEdition);
            System.out.println("Kitap bilgileri güncellendi!");
        } else {
            throw new BookNotFoundException("Güncellenecek kitap bulunamadı!");
        }
    }

    public List<Book> getBooksByCategory(Class<?> category, Map<Long, Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (category.isInstance(book)) {
                result.add(book);
            }
        }
        return result;
    }


}


