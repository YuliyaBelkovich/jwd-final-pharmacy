package com.epam.jwd.domain;

import com.epam.jwd.annotation.StringValidation;

import java.util.Objects;

/**
 * Class represents medicine entity
 * Can be ordered by {@link User} with {@link Role} patient
 * Some of medicines requires {@link Recipe}
 */
public class Medicine implements Entity {

    private int id;
    private String name;
    private double dose;
    private boolean recipeRequirement;
    private String info;
    private double price;

    public Medicine(int id, String name, double dose, boolean recipeRequirement, String info, double price) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.recipeRequirement = recipeRequirement;
        this.info = info;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public boolean isRecipeRequirement() {
        return recipeRequirement;
    }

    public void setRecipeRequirement(boolean recipeRequirement) {
        this.recipeRequirement = recipeRequirement;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dose=" + dose +
                ", recipeRequirement=" + recipeRequirement +
                ", info='" + info + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id == medicine.id && Double.compare(medicine.dose, dose) == 0 && recipeRequirement == medicine.recipeRequirement && Double.compare(medicine.price, price) == 0 && name.equals(medicine.name) && Objects.equals(info, medicine.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dose, recipeRequirement, info, price);
    }
}
