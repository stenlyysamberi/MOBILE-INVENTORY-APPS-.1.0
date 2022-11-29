package com.example.rcgnwhrsinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

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
}