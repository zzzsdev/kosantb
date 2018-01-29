package kelompok7.kosanretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton penyewa,kamar,sewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sewa = (ImageButton) findViewById(R.id.sewa);
        penyewa = (ImageButton) findViewById(R.id.penyewa);
        kamar = (ImageButton) findViewById(R.id.kamar);


        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SewaActivity.class);
                startActivity(i);
            }
        });

        penyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PenyewaActivity.class);
                startActivity(i);
            }
        });

        kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), KamarActivity.class);
                startActivity(i);
            }
        });



    }
}