package com.naoshin.azure.repository;

import com.naoshin.azure.model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client,Long> {

    public Optional<Client> findByNameAndPassword(String name, String password);
    public Optional<Client> findByNameAndPhone(String name, String phone);

    @Query("FROM Client  as c WHERE c.role =:r")
    public List<Client> getClientByRole(@Param("r") String role);

    @Transactional
    @Modifying
    @Query("UPDATE Client SET name =:name, appointmentDate =:date,mechanic_id =:mid  WHERE id =:cid")
    public void updateClient(@Param("cid")long clientId, @Param("name") String name, @Param("date") String date,@Param("mid") long mechanicId);


}
