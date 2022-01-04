package com.kenez92.automanager.fuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT MAX(km_mileage) FROM fuel where car_id =:carId")
    Long findMaxMileage(Long carId);
}
