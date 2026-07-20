package com.workintech.library.models;

import java.time.LocalDate;

public class MemberRecord {
    private long memberId;
    private LocalDate dateOfMembership;
    private int numOfBooksIssued;
    private int maxBookLimit;
    private double unpaidFines;

    public MemberRecord(long memberId, LocalDate dateOfMembership, int numOfBooksIssued, int maxBookLimit) {
        this.memberId = memberId;
        this.dateOfMembership = dateOfMembership;
        this.numOfBooksIssued = numOfBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.unpaidFines = 0.0;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getNumOfBooksIssued() {
        return numOfBooksIssued;
    }

    public void setNumOfBooksIssued(int numOfBooksIssued) {
        this.numOfBooksIssued = numOfBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public double getUnpaidFines() {
        return unpaidFines;
    }

    public void setUnpaidFines(double unpaidFines) {
        this.unpaidFines = unpaidFines;
    }

    public void addFine(double amount) {
        this.unpaidFines += amount;
    }


    public boolean hasLimit() {
        return numOfBooksIssued < maxBookLimit;
    }

    public void incBookIssued() {
        if (hasLimit()) {
            numOfBooksIssued++;
        }
    }

    public void decBookIssued() {
        if (numOfBooksIssued > 0) {
            numOfBooksIssued--;
        }
    }

    public void payBill() {
        System.out.println("Ödeme Başarılı!");
    }
}
