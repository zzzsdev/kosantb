package kelompok7.kosanretrofit.PutDelData;

/**
 * Created by zs on 11/24/2017.
 */

import com.google.gson.annotations.SerializedName;

import kelompok7.kosanretrofit.Data.Penyewa;

public class PostPutDelPenyewa {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Penyewa mPenyewa;
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
    public Penyewa getPenyewa() {
        return mPenyewa;
    }
    public void setPenyewa(Penyewa penyewa) {
        mPenyewa = penyewa;
    }
}
