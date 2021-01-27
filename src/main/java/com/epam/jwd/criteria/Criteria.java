package com.epam.jwd.criteria;

import com.epam.jwd.domain.Entity;

public class Criteria<T extends Entity> {
    private final int id;

    public Criteria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuery() {
        if (id != 0) {
            return "id = " + id + " AND ";
        } else return "";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<SELF extends Criteria.Builder<SELF>> {
        int id;

        public SELF id(int id) {
            this.id = id;
            return (SELF) this;
        }

        public Criteria build() {
            return new Criteria(this.id);
        }
    }
}
