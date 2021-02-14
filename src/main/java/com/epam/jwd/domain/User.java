package com.epam.jwd.domain;

import com.epam.jwd.annotation.StringValidation;

import java.util.Objects;

/**
 * Represents the user entity of pharmacy
 * Pharmacist is equivalent to administrator, can manage {@link Medicine} list
 * Pharmacist can browse {@link Payment}, {@link Order}, {@link Payment} lists, and {@link User} with {@link Role} patient and doctor
 * Doctor can prescribe {@link Medicine} by creating {@link Recipe} for patients
 * Doctor manages it's own timeslots by creating {@link AppointmentWindow} objects
 * Patient can buy {@link Medicine} with automatic creations of {@link Order} and {@link Payment} objects
 * Patient may have {@link BankAccount}
 * Patient may make an appointment with doctor
 */
public class User implements Entity {
    private int id;
    @StringValidation(pattern = "\\A[a-z0-9!#$%&'*+/=?^_‘{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_‘{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z")
    private String email;
    private String password;
    private String name;
    private Role role;
    private UserStatus status;

    public User(int id, String name, String email, String password, Role role, UserStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && email.equals(user.email) && password.equals(user.password) && name.equals(user.name) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, role, status);
    }
}
