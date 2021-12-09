package com.project.library.repository;

import com.project.library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    @Query("select r from Rental r join fetch r.member join fetch r.book")
    List<Rental> findAll();
}
