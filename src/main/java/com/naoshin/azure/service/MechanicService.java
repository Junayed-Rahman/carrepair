package com.naoshin.azure.service;

import com.naoshin.azure.model.Mechanic;
import com.naoshin.azure.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MechanicService {
    @Autowired
    public MechanicRepository mechanicRepository;

    public List<Mechanic> getAllMechanics(){
        List<Mechanic> mechanicList = (List<Mechanic>) mechanicRepository.findAll();
        return mechanicList;
    }
}
