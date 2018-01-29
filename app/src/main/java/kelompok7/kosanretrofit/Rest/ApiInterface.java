package kelompok7.kosanretrofit.Rest;

/**
 * Created by zs on 11/24/2017.
 */
import kelompok7.kosanretrofit.GetData.GetPenyewa;
import kelompok7.kosanretrofit.PutDelData.PostPutDelPenyewa;
import kelompok7.kosanretrofit.GetData.GetKamar;
import kelompok7.kosanretrofit.PutDelData.PostPutDelKamar;
import kelompok7.kosanretrofit.GetData.GetSewa;
import kelompok7.kosanretrofit.PutDelData.PostPutDelSewa;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("penyewa")
    Call<GetPenyewa> getPenyewa();
    @FormUrlEncoded
    @POST("penyewa")
    Call<PostPutDelPenyewa> postPenyewa(@Field("id_penyewa") String idPenyewa,
                                            @Field("nama_penyewa") String namaPenyewa,
                                            @Field("alamat") String alamat,
                                            @Field("no_hp") String noHp);
    @FormUrlEncoded
    @PUT("penyewa")
    Call<PostPutDelPenyewa> putPenyewa(@Field("id_penyewa") String idPenyewa,
                                       @Field("nama_penyewa") String namaPenyewa,
                                       @Field("alamat") String alamat,
                                       @Field("no_hp") String noHp);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "penyewa", hasBody = true)
    Call<PostPutDelPenyewa> deletePenyewa(@Field("id_penyewa") String idPenyewa);

    @GET("kamar")
    Call<GetKamar> getKamar();

    @Multipart
    @POST("kamar")
    Call<PostPutDelKamar> postKamar(@Part("id_kamar") RequestBody idKamar,
                                    @Part("nama_kamar") RequestBody namaKamar,
                                    @Part("harga") RequestBody Harga,
                                    @Part("fasilitas") RequestBody Fasilitas,
                                    @Part("deskripsi") RequestBody Deskripsi,
                                    @Part MultipartBody.Part file);


    @FormUrlEncoded
    @PUT("kamar")
    Call<PostPutDelKamar> putKamar(@Field("id_kamar") String idKamar,
                                     @Field("nama_kamar") String namaKamar,
                                   @Field("harga") String Harga,
                                     @Field("fasilitas") String Fasilitas,
                                     @Field("deskripsi") String Deskripsi                                    )            ;
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kamar", hasBody = true)
    Call<PostPutDelKamar> deleteKamar(@Field("id_kamar") String idKamar);

    @GET("sewa")
    Call<GetSewa> getSewa();
    @FormUrlEncoded
    @POST("sewa")
    Call<PostPutDelSewa> postSewa(@Field("id_sewa") String idSewa,
                                    @Field("tgl_mulai") String tglMulai,
                                    @Field("tgl_sewa") String tglSewa,
                                    @Field("id_kamar") String idKamar,
                                    @Field("id_penyewa") String idPenyewa)            ;

    @FormUrlEncoded
    @PUT("sewa")
    Call<PostPutDelSewa> putSewa(@Field("id_sewa") String idSewa,
                                   @Field("tgl_mulai") String tglMulai,
                                   @Field("tgl_sewa") String tglSewa,
                                   @Field("id_kamar") String idKamar,
                                   @Field("id_penyewa") String idPenyewa)            ;
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "sewa", hasBody = true)
    Call<PostPutDelSewa> deleteSewa(@Field("id_sewa") String idSewa);

}
