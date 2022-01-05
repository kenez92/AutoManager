package com.kenez92.automanager.fuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT MAX(km_mileage) FROM fuel where car_id =:carId")
    Long findMaxMileage(Long carId);

//        @Query("SELECT m FROM Manifest m JOIN FETCH m.items WHERE m.id = ?1")

//        @Query("SELECT m.id FROM Manifest m WHERE m.tmsDocument.tmsLineHaulStopPoint.id IN (?1)")
//    List<Long> findIdByTmsDocumentTmsLineHaulStopPointIdIn(List<Long> idsTmsLineHaulStopPoints);
}
