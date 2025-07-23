package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.PenjualanDetail;

import java.util.List;

public interface PenjualanDetailRepository extends JpaRepository<PenjualanDetail, Long> {

    // Mengambil semua detail berdasarkan ID header penjualan
    List<PenjualanDetail> findByPenjualanId(Long penjualanId);
}
