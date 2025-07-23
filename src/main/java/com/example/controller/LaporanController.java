package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.service.PenjualanService;

@Controller
public class LaporanController {

    @Autowired
    private PenjualanService penjualanService;

    @GetMapping("/laporan")
    public String laporan(Model model) {
        // Data resume penjualan
        long totalTransaksi = penjualanService.getTotalTransaksi();
        int totalPendapatan = penjualanService.getTotalPendapatan();
        int rataRataPendapatan = penjualanService.getRataRataPendapatan();

        // Kirim data ke template
        model.addAttribute("totalTransaksi", totalTransaksi);
        model.addAttribute("totalPendapatan", totalPendapatan);
        model.addAttribute("rataRataPendapatan", rataRataPendapatan);

        return "laporan/list";
    }
}
