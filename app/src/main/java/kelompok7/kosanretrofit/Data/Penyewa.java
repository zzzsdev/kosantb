package kelompok7.kosanretrofit.Data;

/**
 * Created by zs on 11/24/2017.
 */
import com.google.gson.annotations.SerializedName;
public class Penyewa {
    @SerializedName("id_penyewa")
    private String id_penyewa;
    @SerializedName("nama_penyewa")
    private String nama_penyewa;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_hp")
    private String no_hp;
    
    public Penyewa() {}
    public Penyewa(String id_penyewa, String nama_penyewa, String alamat, String
            no_hp) {
        this.id_penyewa = id_penyewa;
        this.nama_penyewa = nama_penyewa;
        this.alamat = alamat;
        this.no_hp = no_hp;

    }
    public String getid_penyewa() {
        return id_penyewa;
    }
    public void setid_penyewa(String id_penyewa) {
        this.id_penyewa = id_penyewa;
    }
    public String getnama_penyewa() {
        return nama_penyewa;
    }
    public void setnama_penyewa(String nama_penyewa) {
        this.nama_penyewa = nama_penyewa;
    }
    public String getalamat() {
        return alamat;
    }
    public void setalamat(String alamat) {
        this.alamat = alamat;
    }
    public String getno_hp() {
        return no_hp;
    }
    public void setno_hp(String no_hp) {
        this.no_hp = no_hp;
    }

}
