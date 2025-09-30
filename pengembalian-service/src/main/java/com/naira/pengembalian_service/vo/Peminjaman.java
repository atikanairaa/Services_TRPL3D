package com.naira.pengembalian_service.vo;

import java.sql.Date;
import java.time.LocalDate;

public class Peminjaman {
    private Long id;
    private Long bukuId;
    private Long anggotaId;
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalPengembalian;

    public Peminjaman(){

    }

    public Peminjaman(Long id, Long bukuId, Long anggotaId, LocalDate tanggalPeminjaman,
            LocalDate tanggalPengembalian) {
        this.id = id;
        this.bukuId = bukuId;
        this.anggotaId = anggotaId;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }

    public Long getAnggotaId() {
        return anggotaId;
    }

    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }

    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
