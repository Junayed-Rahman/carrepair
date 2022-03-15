package com.naoshin.azure.repository;
import com.naoshin.azure.model.Car;
import org.springframework.data.repository.CrudRepository;
public interface CarRepository extends CrudRepository<Car,Long> {


}
