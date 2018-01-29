package kelompok7.kosanretrofit.PutDelData;

import com.google.gson.annotations.SerializedName;

import kelompok7.kosanretrofit.Data.Kamar;

/**
 * Created by zs on 11/28/2017.
 */

public class PostPutDelKamar {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kamar mKamar;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Kamar getKamar() {
        return mKamar;
    }
    public void setKamar(Kamar kamar) {
        mKamar = kamar;
    }
}
