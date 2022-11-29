package com.example.rcgnwhrsinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.Model.ResponseJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void daftar(View view) {
        TextView email,nama,company,ccompany;
        String xmail,xname,xcompany,xccompany;

        email = findViewById(R.id.email);
        xmail = email.getText().toString();

        nama = findViewById(R.id.name);
        xname = nama.getText().toString();

        company = findViewById(R.id.company);
        xcompany = company.getText().toString();

        ccompany = findViewById(R.id.contrak_compony);
        xccompany = ccompany.getText().toString();
        try {
            Endpoints endpoints = APis.getRetrofitInstance().create(Endpoints.class);
            Call<ResponseJson> response = endpoints.registerd(xname,xccompany,xmail,xccompany);
            response.enqueue(new Callback<ResponseJson>() {
                @Override
                public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {

                    if (response.isSuccessful() && response.body() !=null){
                        if (response.body().getStatus().equals("401")){
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
                            intent.putExtra("surel",xmail);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseJson> call, Throwable t) {
                    Log.e("irror", String.valueOf(t));
                }
            });
        }catch (Exception e){
            Log.e("try", String.valueOf(e));
        }
    }
}