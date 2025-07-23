package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Penjualan;

/**
 * Repository untuk entitas Penjualan.
 */
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {}
