package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;

import java.time.LocalDateTime;

public class AppointmentWindow  implements Entity{

    private int id;
    @RoleValidation(role="DOCTOR")
    private int doctorId;
    private LocalDateTime dateTime;
    private WindowStatus status;

    public AppointmentWindow(int id, int doctorId, LocalDateTime dateTime, WindowStatus status) {
        this.id = id;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
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

    public WindowStatus getStatus() {
        return status;
    }

    public void setStatus(WindowStatus status) {
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AppointmentWindow{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }
}
