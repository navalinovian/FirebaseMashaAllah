package com.example.firebasemashaallah;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Tanaman {

    public String nama;
    public String deskripsi;
    public int waktu;

    public Tanaman() {
    }

    public Tanaman(String nama, String deskripsi, int waktu) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", nama);
        result.put("deskripsi", deskripsi);
        result.put("waktu", waktu);
        return result;
    }

}
