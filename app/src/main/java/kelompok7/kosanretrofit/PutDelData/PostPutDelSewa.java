package kelompok7.kosanretrofit.PutDelData;

import com.google.gson.annotations.SerializedName;

import kelompok7.kosanretrofit.Data.Sewa;

/**
 * Created by zs on 12/1/2017.
 */

public class PostPutDelSewa {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Sewa mSewa;
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
    public Sewa getSewa() {
        return mSewa;
    }
    public void setSewa(Sewa sewa) {
        mSewa = sewa;
    }
}
