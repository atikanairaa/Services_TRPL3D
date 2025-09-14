package com.naira.pengembalian_service.vo;

import com.naira.pengembalian_service.model.Pengembalian;

public class ResponseTemplate {
    Pengembalian pengembalian;
    Peminjaman peminjaman;

    public ResponseTemplate(){

    }

    public ResponseTemplate(Pengembalian pengembalian, Peminjaman peminjaman) {
        this.pengembalian = pengembalian;
        this.peminjaman = peminjaman;
    }

    public Pengembalian getPengembalian() {
        return pengembalian;
    }

    public void setPengembalian(Pengembalian pengembalian) {
        this.pengembalian = pengembalian;
    }


    public Peminjaman getPeminjaman() {
        return peminjaman;
    }


    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
