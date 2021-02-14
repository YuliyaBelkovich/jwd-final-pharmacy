package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;
import com.epam.jwd.annotation.StringValidation;

import java.util.Objects;

/**
 * Class represents bank account entity for {@link User} with {@link Role} patient.
 */
public class BankAccount  implements Entity{
    private int id;
    @RoleValidation(role="PATIENT")
    private int patientId;
    @StringValidation(pattern ="\\d{16}",maxLength = 16,minLength = 16)
    private String IBAN;
    @StringValidation(pattern = "^((?:[A-Za-z]+ ?){1,3})$")
    private String cardHolder;
    @StringValidation(pattern = "(?:0[1-9]|1[0-2])/[0-9]{2}", minLength = 5)
    private String expirationDate;
    private int cvv;

    public BankAccount(int id, int patientId, String IBAN, String cardHolder, String expirationDate, int cvv) {
        this.id = id;
        this.patientId = patientId;
        this.IBAN = IBAN;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", IBAN='" + IBAN + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv=" + cvv +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && patientId == that.patientId && cvv == that.cvv && IBAN.equals(that.IBAN) && cardHolder.equals(that.cardHolder) && expirationDate.equals(that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, IBAN, cardHolder, expirationDate, cvv);
    }
}
