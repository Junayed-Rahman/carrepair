package com.naoshin.azure.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int clientsCount;


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

    public int getClientsCount() {
        return clientsCount;
    }

    public void setClientsCount(int clientsCount) {
        this.clientsCount = clientsCount;
    }


    public Mechanic() {
    }

    public Mechanic(String name, int clientsCount, List<Client> clientList) {
        this.name = name;
        this.clientsCount = clientsCount;

    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clientsCount=" + clientsCount +
                '}';
    }
}
