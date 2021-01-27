package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;
import com.epam.jwd.annotation.StringValidation;

public class BankAccount  implements Entity{
    private int id;
    @RoleValidation(role="PATIENT")
    private int patientId;
    @StringValidation(pattern ="^([A-Z]{2}[ '+'\\'+'-]?[0-9]{2})(?=(?:[ '+'\\'+'-]?[A-Z0-9]){9,30}$)((?:[ '+'\\'+'-]?[A-Z0-9]{3,5}){2,7})([ '+'\\'+'-]?[A-Z0-9]{1,3})?$",maxLength = 20,minLength = 20)
    private String IBAN;
    @StringValidation(pattern = "^((?:[A-Za-z]+ ?){1,3})$")
    private String cardHolder;
    @StringValidation(pattern = "(?:0[1-9]|1[0-2])/[0-9]{2}",minLength = 5)
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
}
