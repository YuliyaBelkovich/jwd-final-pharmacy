package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;

import java.util.Map;

/**
 * Class represents order entity
 * Order can be created for {@link User} with {@link Role} patient and for guest
 * field patientId for guest sets null
 * Order can have status PAID or NOT PAID, depending of created {@link Payment} class
 */
public class Order implements Entity {

    private int id;
    private double price;
    private int patientId;
    private OrderStatus status;
    private Map<Integer,Integer> orderedMedicines;

    public Order(int id, double price, int patientId, OrderStatus status) {
        this.id = id;
        this.patientId = patientId;
        this.price = price;
        this.status=status;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Integer, Integer> getOrderedMedicines() {
        return orderedMedicines;
    }

    public void setOrderedMedicines(Map<Integer, Integer> orderedMedicines) {
        this.orderedMedicines = orderedMedicines;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", patientId=" + patientId +
                ", orderedMedicines=" + orderedMedicines.toString() +
                '}';
    }
}