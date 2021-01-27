package com.epam.jwd.criteria;

import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;

public class UserCriteria extends Criteria<User> {
    private final String email;
    private final String password;
    private final String name;
    private final Role role;
    private final UserStatus status;

    public UserCriteria(int id, String email, String password, String name, Role role, UserStatus status) {
        super(id);
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getQuery() {
        String query = "SELECT * FROM user_pharmacy WHERE ";
        query += super.getQuery();
        if (email != null) {
            query += "e_mail = \"" + email + "\" AND ";
        }
        if (password != null) {
            query += "password = \"" + password + "\" AND ";
        }
        if (name != null) {
            query += "name = \"" + name + "\" AND ";
        }
        if (role != null) {
            query += "user_role = \"" + role.getBaseName() + "\" AND ";
        }
        if (status != null) {
            query += "user_status = \"" + status.getDbName() + "\" AND ";
        }
        return query.substring(0, query.length() - 4);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Criteria.Builder<UserCriteria.Builder> {
        public String email;
        public String password;
        public String name;
        public Role role;
        public UserStatus status;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }
        public Builder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserCriteria build() {
            return new UserCriteria(id, email, password, name, role,status);
        }
    }

}
