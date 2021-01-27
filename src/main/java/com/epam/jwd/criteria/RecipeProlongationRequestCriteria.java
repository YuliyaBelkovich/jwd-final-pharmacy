package com.epam.jwd.criteria;

import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.domain.RecipeRequestStatus;

public class RecipeProlongationRequestCriteria extends Criteria<RecipeProlongationRequest> {
    private final int recipeId;
    private final int doctorId;
    public final RecipeRequestStatus status;

    public RecipeProlongationRequestCriteria(int id, int recipeId, int doctorId, RecipeRequestStatus status) {
        super(id);
        this.recipeId = recipeId;
        this.doctorId = doctorId;
        this.status = status;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public RecipeRequestStatus getStatus() {
        return status;
    }

    public String getQuery() {
        String query = "SELECT * FROM recipe_prolongation_requests WHERE ";
        query += super.getQuery();
        if (recipeId != 0) {
            query += "recipe_id = " + recipeId + " AND ";
        }
        if (doctorId != 0) {
            query += "doctor_id = " + recipeId + " AND ";
        }
        if(status!=null){
            query+="request_status = \'" + status.getDbName()+"\" AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<RecipeProlongationRequestCriteria.Builder> {
        public int recipeId;
        public int doctorId;
        public RecipeRequestStatus status;

        public Builder setRecipeId(int recipeId) {
            this.recipeId = recipeId;
            return this;
        }

        public Builder setDoctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder setStatus(RecipeRequestStatus status) {
            this.status = status;
            return this;
        }

        public RecipeProlongationRequestCriteria build() {
            return new RecipeProlongationRequestCriteria(id, recipeId, doctorId, status);
        }
    }
}
