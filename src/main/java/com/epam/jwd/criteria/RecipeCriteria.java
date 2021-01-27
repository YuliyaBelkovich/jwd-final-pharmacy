package com.epam.jwd.criteria;

import com.epam.jwd.domain.Recipe;

import java.time.LocalDate;
import java.util.Date;

public class RecipeCriteria extends Criteria<Recipe> {
    private final int patientId;
    private final int medicineId;
    private final double dose;
    private final int doctorId;
    private final LocalDate date;

    public RecipeCriteria(int id, int patientId, int medicineId, double dose, int doctorId, LocalDate date) {
        super(id);
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.dose = dose;
        this.doctorId = doctorId;
        this.date = date;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public double getDose() {
        return dose;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getQuery() {
        String query = "SELECT * FROM recipe WHERE ";
        query += super.getQuery();
        if (patientId != 0) {
            query += "patient_id = " + patientId + " AND ";
        }
        if (medicineId != 0) {
            query += "medicine_id = " + medicineId + " AND ";
        }
        if (dose != 0) {
            query += "dose = " + dose + " AND ";
        }
        if (doctorId != 0) {
            query += "doctor_id = " + doctorId + " AND ";
        }
        if (date != null) {
            query += "date = " + date.toString() + " AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<RecipeCriteria.Builder> {
        public int patientId;
        public int medicineId;
        public double dose;
        public int doctorId;
        public LocalDate date;

        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder setMedicineId(int medicineId) {
            this.medicineId = medicineId;
            return this;
        }

        public Builder setDose(double dose) {
            this.dose = dose;
            return this;
        }

        public Builder setDoctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public RecipeCriteria build() {
            return new RecipeCriteria(id, patientId, medicineId, dose, doctorId, date);
        }
    }
}
