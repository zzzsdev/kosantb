package kelompok7.kosanretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kelompok7.kosanretrofit.PutDelData.PostPutDelPenyewa;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Penyewa2Activity extends AppCompatActivity {
    EditText edtidpenyewa,edtnamapenyewa,edtAlamat, edtNoHp;
    Button  btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyewa2);

        edtidpenyewa = (EditText) findViewById(R.id.edtidpenyewa);
       edtnamapenyewa = (EditText) findViewById(R.id.edtnamapenyewa);
       edtAlamat = (EditText) findViewById(R.id.edtAlamat);
       edtNoHp = (EditText) findViewById(R.id.edtNoHp);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);


        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtidpenyewa.setText(mIntent.getStringExtra("id_penyewa"));
       edtnamapenyewa.setText(mIntent.getStringExtra("nama_penyewa"));
       edtAlamat.setText(mIntent.getStringExtra("alamat"));
       edtNoHp.setText(mIntent.getStringExtra("no_hp"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPenyewa> updatePenyewaCall =
                        mApiInterface.putPenyewa(
                                edtidpenyewa.getText().toString(),
                               edtnamapenyewa.getText().toString(),
                               edtAlamat.getText().toString(),
                               edtNoHp.getText().toString());

                updatePenyewaCall.enqueue(new Callback<PostPutDelPenyewa>() {
                    @Override
                    public void onResponse(Call<PostPutDelPenyewa> call,
                                           Response<PostPutDelPenyewa> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : "
                                + response.body().getStatus() +
                                "\n " + " Message Update : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPenyewa> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :" +
                                t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtidpenyewa.getText().toString().trim().isEmpty() == false) {
                    Call<PostPutDelPenyewa> deletePenyewa =
                            mApiInterface.deletePenyewa(edtidpenyewa.getText().toString());
                    deletePenyewa.enqueue(new Callback<PostPutDelPenyewa>() {
                        @Override
                        public void onResponse(Call<PostPutDelPenyewa> call,
                                               Response<PostPutDelPenyewa> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : "
                                    + response.body().getStatus() +
                                    "\n " + " Message Delete : " +
                                    response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPenyewa> call,
                                              Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :" +
                                    t.getMessage());
                        }
                    });
                } else {
                    tvMessage.setText("id_penyewa harus diisi");
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),
                        PenyewaActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
