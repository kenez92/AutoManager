package com.kenez92.automanager.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
}
