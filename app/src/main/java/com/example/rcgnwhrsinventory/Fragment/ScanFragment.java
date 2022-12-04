package com.example.rcgnwhrsinventory.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.BerandaActivity;
import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.MainActivity;
import com.example.rcgnwhrsinventory.Model.ResponseJson;
import com.example.rcgnwhrsinventory.R;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanFragment extends Fragment{
    MainActivity mainActivity;
    View v;
    TextView btn_created,open_glerry;
    Context mcontext;


    private final int GALLERY = 1;
    ImageView imageView;
    Bitmap bitmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_scan, container, false);
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(ScanFragment.this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scan new barcode material");
        integrator.setBeepEnabled(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.initiateScan();

        btn_created = v.findViewById(R.id.btn_created);
        btn_created.setOnClickListener(v->{
            created();
        });

        open_glerry = v.findViewById(R.id.newopengalery);
        open_glerry.setOnClickListener(v->{
            galery();
        });

        imageView = v.findViewById(R.id.img_new);

        return v;
    }




   public void galery(){
       Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       intent.setType("image/*");
       startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY);
   }



    private void created() {

        TextView number = v.findViewById(R.id.newbarcode);
        EditText nama = v.findViewById(R.id.newname);
        EditText container = v.findViewById(R.id.newcontainer);
        EditText uom = v.findViewById(R.id.newuom);

        String numbers = number.getText().toString();
        String namas = nama.getText().toString();
        String containers = container.getText().toString();
        String uoms = uom.getText().toString();


        if (numbers.isEmpty() || namas.isEmpty() || containers.isEmpty() || uoms.isEmpty()) {
            Toast.makeText(getActivity(), "" + "Data Belum Lengkap!", Toast.LENGTH_SHORT).show();
        }else{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String string = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            try {
                Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
                Call<ResponseJson> x = endpoint.store(namas,numbers,containers,uoms,string);
                x.enqueue(new Callback<ResponseJson>() {
                    @Override
                    public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                        if (response.isSuccessful() && response.body() !=null){
                            if (response.body().getStatus().equals("200")){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseJson> call, Throwable t) {
                        Toast.makeText(getContext(), "" + String.valueOf(t), Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){
                Log.e("error",String.valueOf(e));
            }
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                TextView code = v.findViewById(R.id.newbarcode);
                code.setText(String.valueOf(result.getContents()));
            }
        }

        //Source code untuk Open Gallery
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed to select image!", Toast.LENGTH_SHORT).show();
                }
            }
        //End Open Gallery

        }

    }
}