package kelompok7.kosanretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kelompok7.kosanretrofit.Adapter.MyAdapter;
import kelompok7.kosanretrofit.Create.InsertPenyewaActivity;
import kelompok7.kosanretrofit.Data.Penyewa;
import kelompok7.kosanretrofit.GetData.GetPenyewa;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class PenyewaActivity extends AppCompatActivity {
    Button btGet, btInsert, btMenu;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout swipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyewa);
        btGet = (Button) findViewById(R.id.btGet);
        btInsert= (Button) findViewById(R.id.btInsert2);
        btMenu = (Button) findViewById(R.id.btMenu);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),
                        InsertPenyewaActivity.class);
                startActivity(mIntent);
            }
        });
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mIntent);
            }
        });
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetPenyewa> penyewaCall = mApiInterface.getPenyewa();
                penyewaCall.enqueue(new Callback<GetPenyewa>() {
                    @Override
                    public void onResponse(Call<GetPenyewa> call,
                                           Response<GetPenyewa> response) {
                        List<Penyewa> penyewaList =
                                response.body().getListDataPenyewa();
                        Log.d("Retrofit Get", "Jumlah data penyewa: " +
                                String.valueOf(penyewaList.size()));
                        mAdapter = new MyAdapter(penyewaList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<GetPenyewa> call, Throwable t) {
// Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }



}