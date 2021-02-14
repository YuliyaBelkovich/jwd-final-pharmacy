package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;

import java.util.Objects;

public class RecipeProlongationRequest implements Entity {
    private int id;
    private int recipeId;
    @RoleValidation(role="DOCTOR")
    private int doctorId;
    private RecipeRequestStatus status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecipeProlongationRequest(int id, int recipeId, int doctorId,RecipeRequestStatus status) {
        this.id = id;
        this.recipeId = recipeId;
        this.doctorId = doctorId;
        this.status = status;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public RecipeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RecipeRequestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeProlongationRequest request = (RecipeProlongationRequest) o;
        return id == request.id && recipeId == request.recipeId && doctorId == request.doctorId && status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeId, doctorId, status);
    }
}
