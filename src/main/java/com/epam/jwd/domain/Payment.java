package com.epam.jwd.domain;

import com.epam.jwd.annotation.StringValidation;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representation of payment entity
 * Created in order to set {@link OrderStatus} PAID
 */
public class Payment implements Entity {

    private int id;
    private double sum;
    @StringValidation(pattern ="\\d{16}",maxLength = 16, minLength = 16)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Double.compare(payment.sum, sum) == 0 && IBAN.equals(payment.IBAN) && dateTime.equals(payment.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sum, IBAN, dateTime);
    }
}
