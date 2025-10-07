package com.naira.peminjaman_service.controller;

import com.naira.peminjaman_service.model.Peminjaman;
import com.naira.peminjaman_service.service.PeminjamanCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/peminjaman/command") // NEW: Specific path for commands
public class PeminjamanCommandController { // Renamed

    @Autowired
    private PeminjamanCommandService peminjamanCommandService; // Renamed

    @PostMapping("/")
    public ResponseEntity<Peminjaman> createPeminjaman(@RequestBody Peminjaman peminjaman){
        Peminjaman createdPeminjaman = peminjamanCommandService.createPeminjaman(peminjaman);
        return new ResponseEntity<>(createdPeminjaman, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeminjaman(@PathVariable Long id){
        peminjamanCommandService.deletePeminjaman(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Metode untuk GET (pembacaan) dihapus dari sini dan dipindahkan ke PeminjamanQueryController
}