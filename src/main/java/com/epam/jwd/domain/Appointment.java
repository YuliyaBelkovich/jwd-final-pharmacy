package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;
import com.epam.jwd.annotation.StringValidation;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain class representing appointment entity
 */
public class Appointment implements Entity {
    private int id;
    @RoleValidation(role="PATIENT")
    private int patientId;
    @RoleValidation(role="DOCTOR")
    private int doctorId;
    private LocalDateTime dateTime;
    private String info;
    private AppointmentStatus appointmentStatus;

    public Appointment(int id, int patientId, int doctorId, LocalDateTime dateTime, String info, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.info = info;
        this.appointmentStatus = appointmentStatus;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", dateTime=" + dateTime +
                ", info='" + info + '\'' +
                ", appointmentStatus=" + appointmentStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && patientId == that.patientId && doctorId == that.doctorId && dateTime.equals(that.dateTime) && Objects.equals(info, that.info) && appointmentStatus == that.appointmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, doctorId, dateTime, info, appointmentStatus);
    }
}