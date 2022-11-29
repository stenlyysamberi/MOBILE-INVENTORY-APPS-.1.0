package com.example.rcgnwhrsinventory.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Adapter.Activityadapter;
import com.example.rcgnwhrsinventory.Adapter.Employeeadapter;
import com.example.rcgnwhrsinventory.Adapter.MaterialAdapter;
import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.MainActivity;
import com.example.rcgnwhrsinventory.Model.Mactivity;
import com.example.rcgnwhrsinventory.Model.Main;
import com.example.rcgnwhrsinventory.Model.Memployee;
import com.example.rcgnwhrsinventory.Model.Mmaterial;
import com.example.rcgnwhrsinventory.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    View v;
    MainActivity mainActivity;
    RecyclerView activity,employee,materials;
    private Activityadapter activityadapter;
    private Employeeadapter employeeadapter;
    private MaterialAdapter materialadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);

        activity();
        employee();
        material();



        return v;
    }

    void activity(){
        try {
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Mactivity> info = response.body().getAktivitas();

                    if (response.isSuccessful() && response.body()!=null){
                        activity = v.findViewById(R.id.recy_activity);
                        activity.setEnabled(false);
                        activityadapter = new Activityadapter(info,getActivity());
                        activity.setAdapter(activityadapter);
                        activity.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
                    }else{
                       Log.e("error", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Main> call, Throwable t) {
                    Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                    Log.e("errors", t.toString());
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Catch Wisata" + e, Toast.LENGTH_SHORT).show();
            Log.e("wisata", String.valueOf(e));
        }
    }

    void employee(){
        try {
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Memployee> users = response.body().getEmployee();

                    if (response.isSuccessful() && response.body()!=null){
                        employee = v.findViewById(R.id.recy_employee);
                        employee.setEnabled(false);
                        employeeadapter = new Employeeadapter(users,getActivity());
                        employee.setAdapter(employeeadapter);
                        employee.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
                    }else{
                        Log.e("error", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Main> call, Throwable t) {
                    Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                    Log.e("errors", t.toString());
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Catch Wisata" + e, Toast.LENGTH_SHORT).show();
            Log.e("wisata", String.valueOf(e));
        }
    }

    void material(){
        try {
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Mmaterial> material = response.body().getMaterial();

                    if (response.isSuccessful() && response.body()!=null){
                        materials = v.findViewById(R.id.recy_material);
                        materials.setEnabled(false);
                        materialadapter = new MaterialAdapter(material,getActivity());
                        materials.setAdapter(materialadapter);
                        materials.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
                    }else{
                        Log.e("error", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Main> call, Throwable t) {
                    Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                    Log.e("errors", t.toString());
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Catch Wisata" + e, Toast.LENGTH_SHORT).show();
            Log.e("wisata", String.valueOf(e));
        }
    }


}