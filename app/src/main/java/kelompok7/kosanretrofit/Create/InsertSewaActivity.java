package kelompok7.kosanretrofit.Create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kelompok7.kosanretrofit.PutDelData.PostPutDelSewa;
import kelompok7.kosanretrofit.R;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;
import kelompok7.kosanretrofit.SewaActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertSewaActivity extends AppCompatActivity {

    EditText edtIdSewa, edtTglMulai, edtTglSewa, edtIdKamar, edtIdPenyewa;
    Button btInsert, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sewa);

        edtIdSewa = (EditText) findViewById(R.id.edtIdSewa);
        edtTglMulai = (EditText) findViewById(R.id.edtTglMulai);
        edtTglSewa = (EditText) findViewById(R.id.edtTglSewa);
        edtIdKamar = (EditText) findViewById(R.id.edtIdKamars);
        edtIdPenyewa = (EditText) findViewById(R.id.edtIdPenyewas);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);

        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdSewa.setText(mIntent.getStringExtra("id_sewa"));
        edtTglMulai.setText(mIntent.getStringExtra("tgl_mulai"));
        edtTglSewa.setText(mIntent.getStringExtra("tgl_sewa"));
        edtIdPenyewa.setText(mIntent.getStringExtra("id_penyewa"));
        edtIdKamar.setText(mIntent.getStringExtra("id_kamar"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelSewa> postSewaCall =
                        mApiInterface.postSewa(
                                edtIdSewa.getText().toString(),
                                edtTglMulai.getText().toString(),
                                edtTglSewa.getText().toString(),
                                edtIdPenyewa.getText().toString(),
                                edtIdKamar.getText().toString());
                postSewaCall.enqueue(new Callback<PostPutDelSewa>() {
                    @Override
                    public void onResponse(Call<PostPutDelSewa> call,
                                           Response<PostPutDelSewa> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : "
                                + response.body().getStatus() +
                                "\n " + " Message Insert : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelSewa> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :" +
                                t.getMessage());
                    }
                });
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

