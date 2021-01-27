package com.epam.jwd.criteria;

import com.epam.jwd.domain.Payment;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PaymentCriteria extends Criteria<Payment>{

    private final double sum;
    private final String IBAN;
    private final LocalDateTime dateTime;

    public PaymentCriteria(int id, double sum, String IBAN, LocalDateTime dateTime) {
        super(id);
        this.sum = sum;
        this.IBAN = IBAN;
        this.dateTime = dateTime;
    }

    public double getSum() {
        return sum;
    }

    public String getIBAN() {
        return IBAN;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getQuery(){
        String query = "SELECT * FROM payment WHERE ";
        query+=super.getQuery();
        if(sum!=0){
            query+="sum = "+ sum + " AND ";
        }
        if (IBAN!=null){
            query+="IBAN = \""+ IBAN + "\" AND ";
        }
        if(dateTime!=null) {
            query += "date_time = " + Timestamp.valueOf(dateTime) + " AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<PaymentCriteria.Builder>{
        public double sum;
        public String IBAN;
        public LocalDateTime dateTime;

        public Builder setSum(double sum) {
            this.sum = sum;
            return this;
        }

        public Builder setIBAN(String IBAN) {
            this.IBAN = IBAN;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public PaymentCriteria build(){
            return new PaymentCriteria(id,sum,IBAN,dateTime);
        }
    }
}
