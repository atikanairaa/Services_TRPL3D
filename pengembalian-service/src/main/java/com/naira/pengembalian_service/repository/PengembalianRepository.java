package com.naira.pengembalian_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naira.pengembalian_service.model.Pengembalian;

public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {

}
