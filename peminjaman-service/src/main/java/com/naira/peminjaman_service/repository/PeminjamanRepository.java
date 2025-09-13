package com.naira.peminjaman_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naira.peminjaman_service.model.Peminjaman;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {

}