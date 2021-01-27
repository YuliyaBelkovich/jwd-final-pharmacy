package com.epam.jwd.criteria;

import com.epam.jwd.domain.Order;
import com.epam.jwd.domain.OrderStatus;

public class OrderCriteria extends Criteria<Order> {

    private final double price;
    private final int patientId;
    private final int medicineId;
    private final OrderStatus status;



    public OrderCriteria(int id, double price, int patientId, int medicineId,OrderStatus status) {
        super(id);
        this.price = price;
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.status=status;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public double getPrice() {
        return price;
    }

    public int getPatientId() {
        return patientId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getQuery() {
        String query = "SELECT * FROM medicine_order JOIN ordered_medicines ON medicine_order.id = ordered_medicines.id WHERE ";
        query += super.getQuery();
        if (price != 0) {
            query += "price = " + price + " AND ";
        }
        if (patientId != 0) {
            query += "patient_id = " + patientId + " AND ";
        }
        if (medicineId != 0) {
            query += "ordered_medicines.id = " + medicineId + " AND ";
        }
        if(status!=null){
            query+= "order_status = \""+ status.getDbName()+"\" AND ";
        }
        return query.substring(0, query.length() - 4);

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<OrderCriteria.Builder> {
        public double price;
        public int patientId;
        public int medicineId;
        public OrderStatus status;

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder setMedicineId(int medicineId) {
            this.medicineId = medicineId;
            return this;
        }
        public Builder setStatus(OrderStatus status){
            this.status=status;
            return this;
        }

        public OrderCriteria build() {
            return new OrderCriteria(id, price, patientId, medicineId,status);
        }
    }
}
