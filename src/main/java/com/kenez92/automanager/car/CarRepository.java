package com.kenez92.automanager.car;

import com.kenez92.automanager.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.fuelList f WHERE c.id = ?1 ORDER BY f.date DESC")
    Optional<Car> getWithFuelById(Long id);

    List<Car> findByUser(User user);
}
