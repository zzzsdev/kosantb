package kelompok7.kosanretrofit.Rest;

/**
 * Created by zs on 11/24/2017.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
/*    public static final String BASE_URL = "http://tugaskutugasmu.xyz/index.php/";*/
    public static final String BASE_URL = "http://tugaskutugasmu.xyz/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
