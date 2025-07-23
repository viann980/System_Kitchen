package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.service.BarangService;
import com.example.model.Barang;

@Controller
@RequestMapping("/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;

    // Menampilkan daftar barang
    @GetMapping
    public String listBarang(Model model) {
        model.addAttribute("barangList", barangService.findAll());
        return "barang/list"; // Mengarahkan ke templates/barang/list.html
    }
    
    Barang barang;

    // Menampilkan formulir tambah/edit barang
    @GetMapping("/form")
    public String formBarang(@RequestParam(value = "kode", required = false) String kode, Model model) {
        model.addAttribute("kode", new Object());
        model.addAttribute("nama", new Object());
        model.addAttribute("harga", new Object());
        model.addAttribute("stok", new Object());
        barang = (kode != null) ? barangService.findByKode(kode) : new Barang();
        model.addAttribute("barang", barang);
        return "barang/form"; // Mengarahkan ke templates/barang/form.html
    }

    // Menyimpan barang baru atau hasil edit
    @PostMapping("/save")
    public String saveBarang(@ModelAttribute Barang barang) {
        barangService.save(barang);
        return "redirect:/barang";
    }

    // Menghapus barang
    @GetMapping("/delete/{kode}")
    public String deleteBarang(@PathVariable ("kode") String kode) {
        barangService.deleteByKode(kode);
        return "redirect:/barang";
    }
}
