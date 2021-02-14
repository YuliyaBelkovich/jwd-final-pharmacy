package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Representation of recipe entity
 * Created by {@link User} with {@link Role} doctor for {@link User} with {@link Role} patient
 * Has {@link Medicine} id
 * Can expire due to the end of the validity period, but stored in database forever
 */
public class Recipe implements Entity {

    private int id;
    @RoleValidation(role="PATIENT")
    private int patientId;
    private int medicineId;
    private double dose;
    private int duration;
    @RoleValidation(role="DOCTOR")
    private int doctorId;
    private LocalDate date;

    public Recipe(int id, int patientId, int medicineId, double dose, int duration, int doctorId, LocalDate date) {
        this.id = id;
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.dose = dose;
        this.duration = duration;
        this.doctorId = doctorId;
        this.date = date;
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

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", medicineId=" + medicineId +
                ", dose=" + dose +
                ", duration=" + duration +
                ", doctorId=" + doctorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && patientId == recipe.patientId && medicineId == recipe.medicineId && Double.compare(recipe.dose, dose) == 0 && duration == recipe.duration && doctorId == recipe.doctorId && date.equals(recipe.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, medicineId, dose, duration, doctorId, date);
    }
}