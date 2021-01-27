package com.epam.jwd.criteria;

import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentWindowCriteria extends Criteria<AppointmentWindow> {

    private final int doctorId;
    private final LocalDateTime dateTime;
    private final WindowStatus status;


    public AppointmentWindowCriteria(int id, int doctorId, LocalDateTime dateTime, WindowStatus status) {
        super(id);
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public WindowStatus getStatus() {
        return status;
    }

    public String getQuery() {
        String query = "SELECT * FROM doctor_timetable WHERE ";
        query += super.getQuery();
        if (doctorId != 0) {
            query += "doctor_id = " + doctorId + " AND ";
        }
        if (dateTime != null) {
            query += "dateTime = " + Timestamp.valueOf(dateTime) + "AND ";
        }
        if (status != null) {
            query += "status = \"" + status.getDbName() + "\" AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<AppointmentWindowCriteria.Builder> {

        public int doctorId;
        public LocalDateTime dateTime;
        public WindowStatus status;

        public Builder setDoctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setStatus(WindowStatus status) {
            this.status = status;
            return this;
        }

        public AppointmentWindowCriteria build(){
            return new AppointmentWindowCriteria(id,doctorId,dateTime,status);
        }
    }
}
