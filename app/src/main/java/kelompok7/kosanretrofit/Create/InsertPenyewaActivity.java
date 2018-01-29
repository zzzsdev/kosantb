package kelompok7.kosanretrofit.Create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kelompok7.kosanretrofit.PenyewaActivity;
import kelompok7.kosanretrofit.PutDelData.PostPutDelPenyewa;
import kelompok7.kosanretrofit.R;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertPenyewaActivity extends AppCompatActivity {
    EditText edtidpenyewa,edtnamapenyewa,edtAlamat, edtNoHp;
    Button btInsert, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_penyewa);

        edtidpenyewa = (EditText) findViewById(R.id.edtidpenyewa);
        edtnamapenyewa = (EditText) findViewById(R.id.edtnamapenyewa);
        edtAlamat = (EditText) findViewById(R.id.edtAlamat);
        edtNoHp = (EditText) findViewById(R.id.edtNoHp);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtidpenyewa.setText(mIntent.getStringExtra("id_penyewa"));
        edtnamapenyewa.setText(mIntent.getStringExtra("nama_penyewa"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtNoHp.setText(mIntent.getStringExtra("no_hp"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPenyewa> postPenyewaCall =
                        mApiInterface.postPenyewa(
                                edtidpenyewa.getText().toString(),
                                edtnamapenyewa.getText().toString(),
                                edtAlamat.getText().toString(),
                                edtNoHp.getText().toString()
                        );
                postPenyewaCall.enqueue(new Callback<PostPutDelPenyewa>() {
                    @Override
                    public void onResponse(Call<PostPutDelPenyewa> call,
                                           Response<PostPutDelPenyewa> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : "
                                + response.body().getStatus() +
                                "\n " + " Message Insert : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPenyewa> call, Throwable t) {
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
                        PenyewaActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
