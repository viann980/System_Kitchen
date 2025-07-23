package com.example.model;

import jakarta.persistence.*;

@Entity
public class PenjualanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Penjualan penjualan;

    @ManyToOne
    private Barang barang;

    private int jumlah;

    private int subtotal; // Subtotal untuk setiap item

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
