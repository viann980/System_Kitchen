package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.model.Penjualan;
import com.example.model.PenjualanDetail;
import com.example.model.Barang;
import com.example.service.PenjualanService;
import com.example.service.BarangService;

import java.util.List;

@Controller
@RequestMapping("/penjualan")
public class PenjualanController {

    @Autowired
    private PenjualanService penjualanService;

    @Autowired
    private BarangService barangService;

    /**
     * Menampilkan daftar transaksi penjualan.
     *
     * @param model Model untuk mengirimkan data ke view
     * @return View daftar penjualan
     */
    /*@GetMapping
    public String listPenjualan(Model model) {
        model.addAttribute("penjualan", penjualanService.findAll());
        return "penjualan/list";
    }
    */
    @GetMapping
    public String listPenjualan(Model model) {
        List<Penjualan> penjualanList = penjualanService.findAll();
        System.out.println("Data Penjualan: " + penjualanList); // Debugging
        model.addAttribute("penjualan", penjualanList);
        return "penjualan/list";
    }

    /**
     * Menampilkan formulir untuk membuat transaksi penjualan baru.
     *
     * @param model Model untuk mengirimkan data ke view
     * @return View formulir penjualan
     */
    @GetMapping("/form")
    public String formPenjualan(Model model) {
        Penjualan penjualan = new Penjualan();
        model.addAttribute("pelanggan", new Object());
        model.addAttribute("tanggal", new Object());
        List<Barang> barangList = barangService.findAll();
        model.addAttribute("penjualan", penjualan);
        model.addAttribute("barangList", barangList);
        model.addAttribute("jumlah", new Object());
        return "penjualan/form";
    }

    /**
     * Menyimpan transaksi penjualan dan detailnya.
     *
     * @param penjualan Objek penjualan untuk header transaksi
     * @param barangIds Daftar kode barang
     * @param jumlahs Daftar jumlah barang yang dibeli
     * @return Redirect ke daftar penjualan
     */
    @PostMapping("/save")
    public String savePenjualan(@ModelAttribute Penjualan penjualan,
                                @RequestParam("barangId") List<String> barangIds,
                                @RequestParam("jumlah") List<Integer> jumlahs) {
        // Simpan header transaksi
        Penjualan savedPenjualan = penjualanService.save(penjualan);

        // Simpan detail transaksi
        for (int i = 0; i < barangIds.size(); i++) {
            String barangId = barangIds.get(i);
            int jumlah = jumlahs.get(i);

            Barang barang = barangService.findById(barangId);

            if (barang != null && jumlah > 0) {
                // Membuat detail transaksi
                PenjualanDetail detail = new PenjualanDetail();
                detail.setPenjualan(savedPenjualan);
                detail.setBarang(barang);
                detail.setJumlah(jumlah);
                detail.setSubtotal(jumlah * barang.getHarga());

                // Simpan detail transaksi
                penjualanService.saveDetail(detail);

                // Kurangi stok barang
                barang.setStok(barang.getStok() - jumlah);
                barangService.save(barang);
            }
        }

        return "redirect:/penjualan";
    }


    /**
     * Menampilkan detail transaksi penjualan.
     *
     * @param penjualanId ID header transaksi penjualan
     * @param model Model untuk mengirimkan data ke view
     * @return View detail transaksi
     */
    @GetMapping("/detail/{penjualanId}")
    public String viewDetail(@PathVariable ("penjualanId") Long penjualanId, Model model) {
        // Ambil data penjualan berdasarkan ID
        Penjualan penjualan = penjualanService.findById(penjualanId);

        // Ambil detail transaksi berdasarkan ID penjualan
        List<PenjualanDetail> details = penjualanService.findDetailsByPenjualanId(penjualanId);

        // Kirimkan data ke template
        model.addAttribute("penjualan", penjualan);
        model.addAttribute("details", details);

        return "penjualan/detail"; // Mengarahkan ke templates/penjualan/detail.html
    }
    
    @GetMapping("/delete/{penjualanId}")
    public String deletePenjualan(@PathVariable ("penjualanId") Long penjualanId, Model model) {
        
    	penjualanService.delete(penjualanId);
    	
        return "redirect:/penjualan"; // Mengarahkan ke templates/penjualan/detail.html
    }

}
