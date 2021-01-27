package com.epam.jwd.criteria;

import com.epam.jwd.domain.BankAccount;

public class BankAccountCriteria extends Criteria<BankAccount> {

    private final int patientId;
    private final String IBAN;
    private final String cardHolder;

    public BankAccountCriteria(int id, int patientId, String iban, String cardHolder) {
        super(id);
        this.patientId = patientId;
        IBAN = iban;
        this.cardHolder = cardHolder;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getQuery() {
        String query = "SELECT * FROM patient_bank_account WHERE ";
        query += super.getQuery();
        if (patientId != 0) {
            query += "patient_id = " + patientId + "AND ";
        }
        if (IBAN != null) {
            query += "IBAN = \"" + IBAN + "\" AND ";
        }
        if (cardHolder != null) {
            query += "card_holder = \"" + cardHolder + "\" AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<BankAccountCriteria.Builder> {
        public int patientId;
        public String IBAN;
        public String cardHolder;

        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder setIBAN(String IBAN) {
            this.IBAN = IBAN;
            return this;
        }

        public Builder setCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }


        public BankAccountCriteria build() {
            return new BankAccountCriteria(id, patientId, IBAN, cardHolder);
        }
    }

}
