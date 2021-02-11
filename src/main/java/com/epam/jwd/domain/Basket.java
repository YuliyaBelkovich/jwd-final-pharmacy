package com.epam.jwd.domain;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private static Basket instance;
    private Map<Medicine, Integer> basketMedicine;
    private double totalPrice;

    private Basket() {
        basketMedicine = new HashMap<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            return instance = new Basket();
        }
        return instance;
    }

    public void addToBasket(Medicine medicine, int amount) {
        basketMedicine.put(medicine, amount);
        totalPrice += (medicine.getPrice() * amount) / 10;

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void removeFromBasket(Medicine medicine) {
        totalPrice = totalPrice - ((medicine.getPrice() * basketMedicine.get(medicine)) / 10);
        basketMedicine.remove(medicine);
    }

    public Map<Medicine, Integer> getBasketMedicine() {
        return basketMedicine;
    }
}
