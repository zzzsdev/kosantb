package kelompok7.kosanretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kelompok7.kosanretrofit.PutDelData.PostPutDelSewa;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sewa2Activity extends AppCompatActivity {

    EditText edtIdSewa, edtTglMulai, edtTglSewa, edtIdKamar, edtIdPenyewa;
    Button  btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa2);

        edtIdSewa = (EditText) findViewById(R.id.edtIdSewa);
        edtTglMulai = (EditText) findViewById(R.id.edtTglMulai);
        edtTglSewa = (EditText) findViewById(R.id.edtTglSewa);
        edtIdKamar = (EditText) findViewById(R.id.edtIdKamars);
        edtIdPenyewa = (EditText) findViewById(R.id.edtIdPenyewas);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);


        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdSewa.setText(mIntent.getStringExtra("id_sewa"));
        edtTglMulai.setText(mIntent.getStringExtra("tgl_mulai"));
        edtTglSewa.setText(mIntent.getStringExtra("tgl_sewa"));
        edtIdPenyewa.setText(mIntent.getStringExtra("id_penyewa"));
        edtIdKamar.setText(mIntent.getStringExtra("id_kamar"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelSewa> updateSewaCall =
                        mApiInterface.putSewa(
                                edtIdSewa.getText().toString(),
                                edtTglMulai.getText().toString(),
                                edtTglSewa.getText().toString(),
                                edtIdPenyewa.getText().toString(),
                                edtIdKamar.getText().toString());
                updateSewaCall.enqueue(new Callback<PostPutDelSewa>() {
                    @Override
                    public void onResponse(Call<PostPutDelSewa> call,
                                           Response<PostPutDelSewa> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : "
                                + response.body().getStatus() +
                                "\n " + " Message Update : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelSewa> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :" +
                                t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtIdSewa.getText().toString().trim().isEmpty() == false) {
                    Call<PostPutDelSewa> deleteSewa =
                            mApiInterface.deleteSewa(edtIdSewa.getText().toString());
                    deleteSewa.enqueue(new Callback<PostPutDelSewa>() {
                        @Override
                        public void onResponse(Call<PostPutDelSewa> call,
                                               Response<PostPutDelSewa> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : "
                                    + response.body().getStatus() +
                                    "\n " + " Message Delete : " +
                                    response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelSewa> call,
                                              Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :" +
                                    t.getMessage());
                        }
                    });
                } else {
                    tvMessage.setText("id_sewa harus diisi");
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),
                        SewaActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
