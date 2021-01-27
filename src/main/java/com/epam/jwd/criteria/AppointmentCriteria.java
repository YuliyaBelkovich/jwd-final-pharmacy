package com.epam.jwd.criteria;

import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.AppointmentStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentCriteria extends Criteria<Appointment> {
    private final int patientId;
    private final int doctorId;
    private final LocalDateTime dateTime;
    private final AppointmentStatus appointmentStatus;

    public AppointmentCriteria(int id, int patientId, int doctorId, LocalDateTime dateTime, AppointmentStatus appointmentStatus) {
        super(id);
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.appointmentStatus = appointmentStatus;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getQuery() {
        String query = "SELECT * FROM appointment WHERE ";
        query += super.getQuery();
        if (patientId != 0) {
            query += "patient_id = " + patientId + "AND ";
        }
        if (doctorId != 0) {
            query += "doctor_id = " + doctorId + "AND ";
        }
        if (dateTime != null) {
            query += "dateTime = " + Timestamp.valueOf(dateTime) + "AND ";
        }
        if (appointmentStatus != null) {
            query += "appointment_status = \"" + appointmentStatus.getDbName() + "\" AND ";
        }

        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<AppointmentCriteria.Builder> {
        public int patientId;
        public int doctorId;
        public LocalDateTime dateTime;
        public AppointmentStatus appointmentStatus;

        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder setDoctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setAppointmentStatus(AppointmentStatus appointmentStatus) {
            this.appointmentStatus = appointmentStatus;
            return this;
        }

        public AppointmentCriteria build() {
            return new AppointmentCriteria(id, patientId, doctorId, dateTime,appointmentStatus);
        }
    }
}
