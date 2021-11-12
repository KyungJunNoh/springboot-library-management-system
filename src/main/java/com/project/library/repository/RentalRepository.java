package com.project.library.repository;

import com.project.library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {
}
