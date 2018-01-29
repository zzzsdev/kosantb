package kelompok7.kosanretrofit.GetData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kelompok7.kosanretrofit.Data.Sewa;

/**
 * Created by zs on 12/1/2017.
 */

public class GetSewa {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Sewa> listDataSewa;
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
    public List<Sewa> getListDataSewa() {
        return listDataSewa;
    }
    public void setListDataKamar(List<Sewa> listDataSewa) {
        this.listDataSewa = listDataSewa;
    }

}
