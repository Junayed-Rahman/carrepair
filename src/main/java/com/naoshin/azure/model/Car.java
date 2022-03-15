package com.naoshin.azure.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String registrationNumber;
    private String licenseNumber;
    private String carEngineNumber;
    @OneToOne(mappedBy = "car")
    private Client client;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getCarEngineNumber() {
        return carEngineNumber;
    }

    public void setCarEngineNumber(String carEngineNumber) {
        this.carEngineNumber = carEngineNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car() {
    }

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", carEngineNumber='" + carEngineNumber + '\'' +
                ", client=" + client +
                '}';
    }
}
