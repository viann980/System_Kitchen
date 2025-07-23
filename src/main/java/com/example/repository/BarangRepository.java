package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Barang;

public interface BarangRepository extends JpaRepository<Barang, String> {}
