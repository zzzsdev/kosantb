package kelompok7.kosanretrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kelompok7.kosanretrofit.PutDelData.PostPutDelKamar;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kamar2Activity extends AppCompatActivity {
    EditText edtIdKamar, edtNamaKamar, edtHarga, edtDeskripsi, edtFasilitas;
    Button  btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;
    ImageView imgPreview;
    ImageView mPhotoid;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar2);

        edtIdKamar = (EditText) findViewById(R.id.edtIdKamar);
        edtNamaKamar = (EditText) findViewById(R.id.edtNamaKamar);
        edtHarga = (EditText) findViewById(R.id.edtHarga);
        edtDeskripsi = (EditText) findViewById(R.id.edtDeskripsi);
        edtFasilitas = (EditText) findViewById(R.id.edtFasilitas);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);


        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdKamar.setText(mIntent.getStringExtra("idkamar"));
        edtNamaKamar.setText(mIntent.getStringExtra("namakamar"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtFasilitas.setText(mIntent.getStringExtra("fasilitas"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        imgPreview  = (ImageView) findViewById(R.id.imagePreview);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelKamar> updateKamarCall =
                        mApiInterface.putKamar(
                                edtIdKamar.getText().toString(),
                                edtNamaKamar.getText().toString(),
                                edtHarga.getText().toString(),
                                edtFasilitas.getText().toString(),
                                edtDeskripsi.getText().toString());
                updateKamarCall.enqueue(new Callback<PostPutDelKamar>() {
                    @Override
                    public void onResponse(Call<PostPutDelKamar> call,
                                           Response<PostPutDelKamar> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : "
                                + response.body().getStatus() +
                                "\n " + " Message Update : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelKamar> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :" +
                                t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtIdKamar.getText().toString().trim().isEmpty() == false) {
                    Call<PostPutDelKamar> deleteKamar =
                            mApiInterface.deleteKamar(edtIdKamar.getText().toString());
                    deleteKamar.enqueue(new Callback<PostPutDelKamar>() {
                        @Override
                        public void onResponse(Call<PostPutDelKamar> call,
                                               Response<PostPutDelKamar> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : "
                                    + response.body().getStatus() +
                                    "\n " + " Message Delete : " +
                                    response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelKamar> call,
                                              Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :" +
                                    t.getMessage());
                        }
                    });
                } else {
                    tvMessage.setText("id_kamar harus diisi");
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),
                        KamarActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
