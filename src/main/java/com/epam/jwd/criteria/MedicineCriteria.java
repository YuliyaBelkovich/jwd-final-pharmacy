package com.epam.jwd.criteria;

import com.epam.jwd.domain.Medicine;

public class MedicineCriteria extends Criteria<Medicine> {

    private final String name;
    private final double dose;
    private final boolean recipeRequirement;
    private final boolean nonRecipeRequirement;
    private final double price;


    public MedicineCriteria(int id, String name, double dose, boolean recipeRequirement, boolean nonRecipeRequirement, double price) {
        super(id);
        this.name = name;
        this.dose = dose;
        this.recipeRequirement = recipeRequirement;
        this.nonRecipeRequirement = nonRecipeRequirement;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getDose() {
        return dose;
    }

    public boolean isRecipeRequirement() {
        return recipeRequirement;
    }

    public boolean isNonRecipeRequirement() {
        return nonRecipeRequirement;
    }

    public double getPrice() {
        return price;
    }

    public String getQuery() {
        String query = "SELECT * FROM medicine WHERE ";
        query += super.getQuery();
        if (name != null) {
            query += "name = \"" + name + "\" AND ";
        }
        if (dose != 0) {
            query += "dose = " + dose + " AND ";
        }
        if (!recipeRequirement || !nonRecipeRequirement) {
            if (recipeRequirement) {
                query += "recipe_requirement = " + true + " AND ";
            } else if (nonRecipeRequirement) {
                query += "recipe_requirement = " + false + " AND ";
            }
        }
        if (price != 0) {
            query += "price = " + price + " AND ";
        }

        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<MedicineCriteria.Builder> {
        public String name;
        public double dose;
        public boolean recipeRequirement;
        public boolean nonRecipeRequirement;
        public double price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDose(double dose) {
            this.dose = dose;
            return this;
        }

        public Builder setNonRecipeRequirement(boolean nonRecipeRequirement) {
            this.nonRecipeRequirement = nonRecipeRequirement;
            return this;
        }

        public Builder setRecipeRequirement(boolean recipeRequirement) {
            this.recipeRequirement = recipeRequirement;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public MedicineCriteria build() {
            return new MedicineCriteria(id, name, dose, recipeRequirement, nonRecipeRequirement,price);
        }
    }

}
