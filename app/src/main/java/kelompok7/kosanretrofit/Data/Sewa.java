package kelompok7.kosanretrofit.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zs on 12/1/2017.
 */

public class Sewa {
    @SerializedName("id_sewa")
    private String id_sewa;
    @SerializedName("tgl_mulai")
    private String tgl_mulai;
    @SerializedName("tgl_sewa")
    private String tgl_sewa;
    @SerializedName("id_kamar")
    private String id_kamar;
    @SerializedName("id_penyewa")
    private String id_penyewa;

    public Sewa() {}
    public Sewa(String id_sewa, String tgl_mulai, String tgl_sewa, String
            id_kamar, String id_penyewa) {
        this.id_sewa = id_sewa;
        this.tgl_mulai = tgl_mulai;
        this.tgl_sewa = tgl_sewa;
        this.id_kamar = id_kamar;
        this.id_penyewa = id_penyewa;

    }
    public String getid_sewa() {
        return id_sewa;
    }
    public void setid_sewa(String id_sewa) {
        this.id_sewa = id_sewa;
    }
    public String gettgl_mulai() {
        return tgl_mulai;
    }
    public void settgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }
    public String gettgl_sewa() {
        return tgl_sewa;
    }
    public void settgl_sewa(String tgl_sewa) {
        this.tgl_sewa = tgl_sewa;
    }
    public String getid_kamar() {
        return id_kamar;
    }
    public void setid_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }
    public String getid_penyewa() {
        return id_penyewa;
    }
    public void setid_penyewa(String id_penyewa) {
        this.id_penyewa = id_penyewa;
    }

}
