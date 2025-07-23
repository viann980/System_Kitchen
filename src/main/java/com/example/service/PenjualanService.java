package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Penjualan;
import com.example.model.PenjualanDetail;
import com.example.repository.PenjualanRepository;
import com.example.repository.PenjualanDetailRepository;
import java.util.List;

@Service
public class PenjualanService {

    @Autowired
    private PenjualanRepository penjualanRepository;

    @Autowired
    private PenjualanDetailRepository penjualanDetailRepository;

    public Penjualan save(Penjualan penjualan) {
        return penjualanRepository.save(penjualan);
    }

    public List<Penjualan> findAll() {
    	List<Penjualan> penjualanList = penjualanRepository.findAll();
        for (Penjualan penjualan : penjualanList) {
            // Hitung total dari detail penjualan
            Integer total = (int) penjualanDetailRepository
                .findByPenjualanId(penjualan.getId())
                .stream()
                .mapToDouble(detail -> detail.getSubtotal())
                .sum();
            penjualan.setTotal(total);
        }
        return penjualanRepository.findAll();
    }
    
    // Menghitung jumlah transaksi
    public long getTotalTransaksi() {
        return penjualanRepository.count(); // Menghitung jumlah record di tabel penjualan
    }

    // Menghitung total pendapatan
    public Integer getTotalPendapatan() {
        List<PenjualanDetail> detailList = penjualanDetailRepository.findAll();
        System.out.println("Detail Penjualan: " + detailList); // Debugging
        return (int) detailList.stream()
                .mapToDouble(PenjualanDetail::getSubtotal) // Ambil subtotal dari setiap detail
                .sum();
    }

    // Menghitung rata-rata pendapatan per transaksi
    public int getRataRataPendapatan() {
        long totalTransaksi = getTotalTransaksi();
        if (totalTransaksi == 0) {
            return 0;
        }
        return (int) (getTotalPendapatan() / totalTransaksi);
    }

    public void delete(Long id) {
        penjualanRepository.deleteById(id);
    }

    public Penjualan findById(Long id) {
        return penjualanRepository.findById(id).orElse(null);
    }

    public void saveDetail(PenjualanDetail detail) {
        penjualanDetailRepository.save(detail);
    }

    /**
     * Mengambil semua detail transaksi berdasarkan ID header penjualan.
     *
     * @param penjualanId ID header penjualan
     * @return Daftar detail transaksi
     */
    public List<PenjualanDetail> findDetailsByPenjualanId(Long penjualanId) {
        return penjualanDetailRepository.findByPenjualanId(penjualanId);
    }
}
