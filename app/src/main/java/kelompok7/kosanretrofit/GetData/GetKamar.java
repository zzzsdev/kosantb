package kelompok7.kosanretrofit.GetData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kelompok7.kosanretrofit.Data.Kamar;

/**
 * Created by zs on 11/28/2017.
 */

public class GetKamar {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kamar> listDataKamar;
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
    public List<Kamar> getListDataKamar() {
        return listDataKamar;
    }
    public void setListDataKamar(List<Kamar> listDataKamar) {
        this.listDataKamar = listDataKamar;
    }

}
