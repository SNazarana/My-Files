package com.springboot.bike.repository;

import com.springboot.bike.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike ,Long>{

}
