package com.kenez92.automanager.fuel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findById(Long id);
}
