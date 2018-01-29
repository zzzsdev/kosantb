package kelompok7.kosanretrofit.GetData;

/**
 * Created by zs on 11/24/2017.
 */
import com.google.gson.annotations.SerializedName;
import java.util.List;

import kelompok7.kosanretrofit.Data.Penyewa;

public class GetPenyewa {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Penyewa> listDataPenyewa;
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
    public List<Penyewa> getListDataPenyewa() {
        return listDataPenyewa;
    }
    public void setListDataPenyewa(List<Penyewa> listDataPenyewa) {
        this.listDataPenyewa = listDataPenyewa;
    }


}
