package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Penjualan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pelanggan;

    private LocalDate tanggal;

    private Integer total; // Menambahkan total transaksi

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Penjualan{" +
                "id=" + id +
                ", pelanggan='" + pelanggan + '\'' +
                ", tanggal=" + tanggal +
                ", total=" + total +
                '}';
    }
}
