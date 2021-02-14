package com.epam.jwd.domain;

import com.epam.jwd.annotation.RoleValidation;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain class representing timeslot entity for {@link User} timetable with {@link Role} doctor.
 */
public class AppointmentWindow implements Entity {

    private int id;
    @RoleValidation(role = "DOCTOR")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentWindow window = (AppointmentWindow) o;
        return id == window.id && doctorId == window.doctorId && dateTime.equals(window.dateTime) && status == window.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, dateTime, status);
    }
}
