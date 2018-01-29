package kelompok7.kosanretrofit.Create;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kelompok7.kosanretrofit.KamarActivity;
import kelompok7.kosanretrofit.PutDelData.PostPutDelKamar;
import kelompok7.kosanretrofit.R;
import kelompok7.kosanretrofit.Rest.ApiClient;
import kelompok7.kosanretrofit.Rest.ApiInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertKamarActivity extends AppCompatActivity {
    EditText edtIdKamar, edtNamaKamar, edtHarga, edtDeskripsi, edtFasilitas;
    Button btInsert, btBack, btBrowse;
    TextView tvMessage;
    ImageView imgPreview;
    ApiInterface mApiInterface;


    String imagePath = "";
    File mFileUri;

    Bitmap originalImage;
    int width;
    int height;
    int newWidth = 200;
    int newHeight = 200;
    Matrix matrix;
    Bitmap resizedBitmap;
    float scaleWidth ;
    float scaleHeight;
    ByteArrayOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_kamar);

        edtIdKamar = (EditText) findViewById(R.id.edtIdKamar);
        edtNamaKamar = (EditText) findViewById(R.id.edtNamaKamar);
        edtHarga = (EditText) findViewById(R.id.edtHarga);
        edtDeskripsi = (EditText) findViewById(R.id.edtDeskripsi);
        edtFasilitas = (EditText) findViewById(R.id.edtFasilitas);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btBrowse = (Button) findViewById(R.id.btnBrowse);
        btInsert = (Button) findViewById(R.id.btInsert2);
        btBack = (Button) findViewById(R.id.btBack);

        imgPreview  = (ImageView) findViewById(R.id.imagePreview);

        Intent mIntent = getIntent();
        edtIdKamar.setText(mIntent.getStringExtra("idkamar"));
        edtNamaKamar.setText(mIntent.getStringExtra("namakamar"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtFasilitas.setText(mIntent.getStringExtra("fasilitas"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;

                if(!imagePath.isEmpty()){
                    File file = new File(imagePath);

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);

                    body = MultipartBody.Part.createFormData("gambar",file.getName(),requestFile);
                }

                RequestBody idkamar = MultipartBody.create(MediaType.parse("multipart/form-data"),(edtIdKamar.getText().toString().isEmpty()== true)?"":edtIdKamar.getText().toString());
                RequestBody namaKamar = MultipartBody.create(MediaType.parse("multipart/form-data"),(edtNamaKamar.getText().toString().isEmpty() == true)?"":edtNamaKamar.getText().toString());
                RequestBody harga = MultipartBody.create(MediaType.parse("multipart/form-data"),(edtHarga.getText().toString().isEmpty()==true)?"":edtHarga.getText().toString());
                RequestBody fasilitas = MultipartBody.create(MediaType.parse("multipart/form-data"),(edtFasilitas.getText().toString().isEmpty()==true)?"":edtFasilitas.getText().toString());
                RequestBody deskripsi = MultipartBody.create(MediaType.parse("multipart/form-data"),(edtDeskripsi.getText().toString().isEmpty()==true)?"":edtDeskripsi.getText().toString());


                Call<PostPutDelKamar> postKamarCall =
                        mApiInterface.postKamar(idkamar,namaKamar,harga,fasilitas,deskripsi,body);
                postKamarCall.enqueue(new Callback<PostPutDelKamar>() {
                    @Override
                    public void onResponse(Call<PostPutDelKamar> call,
                                           Response<PostPutDelKamar> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : "
                                + response.body().getStatus() +
                                "\n " + " Message Insert : " +
                                response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelKamar> call, Throwable t) {
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
                        KamarActivity.class);
                startActivity(mIntent);
            }
        });

        btBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Batal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(InsertKamarActivity.this);
        builder.setTitle("Upload Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")){

                    captureImage();

                }else if (items[i].equals("Gallery")){

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Pilih Gambar"),2);

                }else if (items[i].equals("Batal")){

                    dialogInterface.dismiss();
                }
            }
        }).show();
    }

    private void captureImage() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            mFileUri = getMediaFileName();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, CalendarContract.CalendarCache.URI.fromFile(mFileUri));
            startActivityForResult(takePictureIntent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                originalImage = BitmapFactory.decodeFile(mFileUri.getPath());

                width = originalImage.getWidth();
                height = originalImage.getHeight();

                matrix = new Matrix();

                scaleWidth = ((float) newWidth) / width;

                scaleHeight = ((float) newHeight) / height;

                matrix.postScale(scaleWidth, scaleHeight);

                matrix.postRotate(0);

                resizedBitmap = Bitmap.createBitmap(originalImage, 0, 0, width, height, matrix, true);
//            outputStream = new ByteArrayOutputStream();

                FileOutputStream out = null;
                String filename = mFileUri.getPath();

                try {
                    out = new FileOutputStream(filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

                Picasso.with(getApplicationContext()).load(new File(mFileUri.getPath())).into(imgPreview);
                Log.d("cek lokasi : ",""+mFileUri.getPath());

                imagePath = mFileUri.getPath();

            }else if(requestCode == 2){
                if (data == null){
                    Toast.makeText(getApplicationContext(), "Gambar Gagal Di load",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);

                if (cursor != null){
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);

                    Picasso.with(getApplicationContext()).load(new File(imagePath)).fit().into(imgPreview);
                    cursor.close();
                }else {
                    Toast.makeText(getApplicationContext(), "Gambar Gagal Di load",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private static File getMediaFileName() {
// Lokasi External sdcard
        File mediaStorageDir = new
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CameraDemo");
// Buat directori tidak direktori tidak eksis
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("CameraDemo", "Gagal membuat directory"+ "CameraDemo");
                return null;
            }
        }
// Membuat nama file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile = null;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp
                + ".jpg");
        return mediaFile;
    }
}
