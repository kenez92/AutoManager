package com.kenez92.automanager.car;

import com.kenez92.automanager.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);

    List<Car> findByUser(User user);
}
