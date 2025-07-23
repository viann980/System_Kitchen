package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Barang;
import com.example.repository.BarangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BarangService {

    @Autowired
    private BarangRepository barangRepository;

    public List<Barang> findAll() {
        return barangRepository.findAll();
    }

    /**
     * Mengambil barang berdasarkan kode.
     *
     * @param kode Kode barang
     * @return Barang jika ditemukan, null jika tidak ditemukan
     */
    public Barang findByKode(String kode) {
        return barangRepository.findById(kode).orElse(null);
    }

    public void save(Barang barang) {
        barangRepository.save(barang);
    }

    public void deleteByKode(String kode) {
        barangRepository.deleteById(kode);
    }
    
    /**
     * Mencari barang berdasarkan kode.
     *
     * @param kode Kode barang
     * @return Barang jika ditemukan, null jika tidak ditemukan
     */
    public Barang findById(String kode) {
        Optional<Barang> barang = barangRepository.findById(kode);
        return barang.orElse(null);
    }
}
