package com.naoshin.azure.repository;

import com.naoshin.azure.model.Mechanic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface MechanicRepository extends CrudRepository<Mechanic, Long> {


    @Transactional
    @Modifying
    @Query("UPDATE Mechanic SET clientsCount = clientsCount-1 WHERE id =:mechanicId")
    public void decreaseClientsCount(@Param("mechanicId")Long mechanicId);


    @Transactional
    @Modifying
    @Query("UPDATE Mechanic SET clientsCount = clientsCount-1 WHERE id =:mechanicId")
    public void increaseClientsCount(@Param("mechanicId")Long mechanicId);


}
