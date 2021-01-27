package com.epam.jwd.domain;

import java.time.LocalDateTime;

public class Payment implements Entity {

    private int id;
    private double sum;
    private String IBAN;
    private LocalDateTime dateTime;

    public Payment(int id, double sum, String IBAN, LocalDateTime dateTime) {
        this.id = id;
        this.sum = sum;
        this.IBAN = IBAN;
        this.dateTime = dateTime;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", sum=" + sum +
                ", IBAN='" + IBAN + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
