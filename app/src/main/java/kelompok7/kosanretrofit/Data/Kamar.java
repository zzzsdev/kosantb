package kelompok7.kosanretrofit.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zs on 11/28/2017.
 */

public class Kamar {

    @SerializedName("id_kamar")
    private String id_kamar;
    @SerializedName("nama_kamar")
    private String nama_kamar;
    @SerializedName("harga")
    private String harga;
    @SerializedName("fasilitas")
    private String fasilitas;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("gambar")
    private String gambar;

    public Kamar() {}

    public Kamar(String id_kamar, String nama_kamar, String harga, String fasilitas, String deskripsi, String gambar) {
        this.id_kamar = id_kamar;
        this.nama_kamar = nama_kamar;
        this.harga = harga;
        this.fasilitas = fasilitas;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getNama_kamar() {
        return nama_kamar;
    }

    public void setNama_kamar(String nama_kamar) {
        this.nama_kamar = nama_kamar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
