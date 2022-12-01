package com.example.rcgnwhrsinventory.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanFragment extends Fragment{
    MainActivity mainActivity;
    View v;
    TextView btn_created,open_glerry;
    private static int LOAD_IMAGE_RESULT=1;

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
            open_file();
        });

        return v;
    }

    private void open_file() {
        Intent i=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, LOAD_IMAGE_RESULT);
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
            try {
                Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
                Call<ResponseJson> x = endpoint.created(numbers,namas,containers,uoms,"1669876182600-spatu.PNG");
                x.enqueue(new Callback<ResponseJson>() {
                    @Override
                    public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                        if (response.isSuccessful() && response.body() !=null){
                            if (response.body().getStatus().equals("200")){
                                TextView message = v.findViewById(R.id.newmessage);
                                message.setVisibility(View.VISIBLE);
                                message.setText(response.body().getMessage());
                                //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }else{
                                TextView message = v.findViewById(R.id.newmessage);
                                message.setVisibility(View.VISIBLE);
                                message.setText(response.body().getMessage());
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

    }


}