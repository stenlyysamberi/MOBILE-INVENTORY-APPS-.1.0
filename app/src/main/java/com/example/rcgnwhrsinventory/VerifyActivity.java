package com.example.rcgnwhrsinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.Model.ResponseJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
    }

    public void verify(View view) {
        EditText token;
        token = findViewById(R.id.token_very);
        String tokens = token.getText().toString();

        TextView msg = findViewById(R.id.code_text_email);
        msg.setText(String.format(
                "Enter the 6 digit code sent to \\nyou at ", getIntent().getStringExtra("surel")
        ));

        try {
            Endpoints endpoints = APis.getRetrofitInstance().create(Endpoints.class);
            Call<ResponseJson> response = endpoints.verify(getIntent().getStringExtra("surel"),tokens);
            response.enqueue(new Callback<ResponseJson>() {
                @Override
                public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {

                    if (response.isSuccessful() && response.body() !=null){
                        if (response.body().getStatus().equals("401")){
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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