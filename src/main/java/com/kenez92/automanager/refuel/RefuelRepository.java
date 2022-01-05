package com.kenez92.automanager.refuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface RefuelRepository extends JpaRepository<Refuel, Long> {
    Optional<Refuel> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT MAX(km_mileage) FROM refuel where car_id =:carId")
    Long findMaxMileage(Long carId);
}
