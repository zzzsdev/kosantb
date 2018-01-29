package kelompok7.kosanretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kelompok7.kosanretrofit.Adapter.MyAdapterKamar;
import kelompok7.kosanretrofit.Create.InsertKamarActivity;
import kelompok7.kosanretrofit.Data.Kamar;
import kelompok7.kosanretrofit.GetData.GetKamar;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class KamarActivity extends AppCompatActivity {
    Button btGet, btInsert, btMenu;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);
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
                        InsertKamarActivity.class);
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
                Call<GetKamar> KamarCall = mApiInterface.getKamar();
                KamarCall.enqueue(new Callback<GetKamar>() {
                    @Override
                    public void onResponse(Call<GetKamar> call,
                                           Response<GetKamar> response) {
                        List<Kamar> KamarList =
                                response.body().getListDataKamar();
                        Log.d("Retrofit Get", "Jumlah data Kamar: " +
                                String.valueOf(KamarList.size()));
                        mAdapter = new MyAdapterKamar(KamarList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<GetKamar> call, Throwable t) {
// Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }
    }
