package com.example.rcgnwhrsinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Fragment.HomeFragment;
import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.Model.ResponseJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button bnt_login = findViewById(R.id.bnt_login);
        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView email = findViewById(R.id.email);
                TextView password = findViewById(R.id.password);
                String smail = email.getText().toString();
                String spassword = password.getText().toString();

                if (smail.isEmpty() || spassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data tidak lengkap!", Toast.LENGTH_SHORT).show();
                }else{
                   login(smail,spassword);
                }
            }
        });

    }

    private void login(String xmail, String xpassword) {
            try {
                Endpoints endpoints = APis.getRetrofitInstance().create(Endpoints.class);
                Call<ResponseJson> response = endpoints.login(xmail,xpassword);
                response.enqueue(new Callback<ResponseJson>() {
                    @Override
                    public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {

                        if (response.isSuccessful() && response.body() !=null){
                            if (response.body().getStatus().equals("401")){
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }else{
                                Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
//                                intent.putExtra("surel",xmail);
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

    public void registered(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}