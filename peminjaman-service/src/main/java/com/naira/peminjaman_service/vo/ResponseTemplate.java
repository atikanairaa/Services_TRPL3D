package com.naira.peminjaman_service.vo;

import com.naira.peminjaman_service.model.Peminjaman;

public class ResponseTemplate {
    Peminjaman peminjaman;
    Buku buku;
    Anggota anggota;

    public ResponseTemplate(){

    }

    public ResponseTemplate(Peminjaman peminjaman, Buku buku, Anggota anggota) {
        this.peminjaman = peminjaman;
        this.buku = buku;
        this.anggota = anggota;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}