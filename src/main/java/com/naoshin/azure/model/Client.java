package com.naoshin.azure.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phone;
    private String address;
    private String role;
    private String appointmentDate;
    public String password;
    @Transient
    public String confirmPassword;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;
    private Long mechanic_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(Long mechanic_id) {
        this.mechanic_id = mechanic_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Client() {
    }

    public Client(String name, String phone, String address, String role, String appointmentDate, Car car, Long mechanic_id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.appointmentDate = appointmentDate;
        this.car = car;
        this.mechanic_id = mechanic_id;
    }

    public Client(String name, String password, String confirmPassword) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", car=" + car +
                ", mechanic_id=" + mechanic_id +
                '}';
    }
}


