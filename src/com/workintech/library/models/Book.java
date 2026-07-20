package com.workintech.library.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private final long id;
    private Author author;
    private String title;
    private Reader owner;
    private double price;
    private BookStatus status;
    private String edition;
    private LocalDate dateOfPurchase;
    private LocalDate checkoutDate;
    public static final int ALLOWED_DAYS = 30;

    public Book(long id, Author author, String title, double price, String edition, LocalDate dateOfPurchase) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.owner = null;
        setPrice(price);
        this.status = BookStatus.AVAILABLE;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.checkoutDate = null;
    }

    public long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Reader getOwner() {
        return owner;
    }

    public void setOwner(Reader owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Kitap fiyatı negatif olamaz!");
        }
        this.price = price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void changeOwner(Reader newOwner) {
        this.owner = newOwner;
    }

    public String display() {
        return toString();
    }

    public int getAllowedDays() {
        return ALLOWED_DAYS;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", edition='" + edition + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }


}
