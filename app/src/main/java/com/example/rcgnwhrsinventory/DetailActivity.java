package com.example.rcgnwhrsinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.Model.ResponseJson;
import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView nama,container,totals,serial;
    ImageView imageView;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.detail_barcode);

        nama = findViewById(R.id.detail_nama);
        nama.setText(String.format(getIntent().getStringExtra("nama")));

        container = findViewById(R.id.detail_container);
        container.setText(String.format(getIntent().getStringExtra("container")) + " - container");

        serial = findViewById(R.id.detail_serial);
        serial.setText(String.format(getIntent().getStringExtra("serial")));

        totals = findViewById(R.id.detail_total);
        totals.setText(String.valueOf(getIntent().getStringExtra("jumlah")));

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int w = point.x;
        int h = point.y;
        int dimen = w<h? w:h;
        dimen = dimen * 3/4;

        String code = String.valueOf(getIntent().getStringExtra("serial"));

        qrgEncoder = new QRGEncoder(code, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

    }

    public void tamba_jumlah(View view) {

    }

    public void hapus_material(View view) {
            TextView serial = findViewById(R.id.detail_serial);
            String numbers = serial.getText().toString();


            if (numbers.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Serial not found!", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
                    Call<ResponseJson> x = endpoint.deleted(numbers);
                    x.enqueue(new Callback<ResponseJson>() {
                        @Override
                        public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                            if (response.isSuccessful() && response.body() !=null){
                                if (response.body().getStatus().equals("200")){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseJson> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "" + String.valueOf(t), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    Log.e("error",String.valueOf(e));
                }
            }

    }
}