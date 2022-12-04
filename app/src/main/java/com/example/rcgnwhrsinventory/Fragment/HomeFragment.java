package com.example.rcgnwhrsinventory.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcgnwhrsinventory.Adapter.Activityadapter;
import com.example.rcgnwhrsinventory.Adapter.Employeeadapter;
import com.example.rcgnwhrsinventory.Adapter.MaterialAdapter;
import com.example.rcgnwhrsinventory.BerandaActivity;
import com.example.rcgnwhrsinventory.FullActivity;
import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.MainActivity;
import com.example.rcgnwhrsinventory.Model.Mactivity;
import com.example.rcgnwhrsinventory.Model.Main;
import com.example.rcgnwhrsinventory.Model.Memployee;
import com.example.rcgnwhrsinventory.Model.Mmaterial;
import com.example.rcgnwhrsinventory.Model.Mtotal;
import com.example.rcgnwhrsinventory.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.w3c.dom.Text;

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
    ShimmerFrameLayout shimmeractivities,shimmerEmployee,shimer_material;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);

        TextView sell_material = v.findViewById(R.id.see_all_material);
        sell_material.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FullActivity.class);
            startActivity(intent);
        });

        activity();
        employee();
        material();



        return v;
    }

    void activity(){
        shimmeractivities = v.findViewById(R.id.shimer_activitis);
        shimmeractivities.setVisibility(View.VISIBLE);
        shimmeractivities.startShimmerAnimation();
        try {
//            TextView layout = v.findViewById(R.id.message_respone);
//            layout.setVisibility(View.INVISIBLE);
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Mactivity> info = response.body().getAktivitas();

//                    Log.e("data", String.valueOf(info.get(0).getUom()));

                    if (response.isSuccessful() && response.body()!=null){
                        shimmeractivities.stopShimmerAnimation();
                        shimmeractivities.setVisibility(View.GONE);
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
                    //Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                    Log.e("errors", t.toString());
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Catch Wisata" + e, Toast.LENGTH_SHORT).show();
            Log.e("wisata", String.valueOf(e));
        }
    }

    void employee(){
        shimmerEmployee = v.findViewById(R.id.shimmer_employee);
        shimmerEmployee.setVisibility(View.VISIBLE);
        shimmerEmployee.startShimmerAnimation();
        try {
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Memployee> users = response.body().getEmployee();
                    if (response.isSuccessful() && response.body()!=null){
                        shimmerEmployee.stopShimmerAnimation();
                        shimmerEmployee.setVisibility(View.GONE);
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
        shimer_material = v.findViewById(R.id.maerial_item_shimer);
        shimer_material.setVisibility(View.VISIBLE);
        shimer_material.startShimmerAnimation();
        try {
            TextView stok = v.findViewById(R.id.total_stok);
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.beranda();
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Mmaterial> material = response.body().getMaterial();
                    Mtotal mtotal = response.body().getTotal();
                    stok.setText(String.valueOf(mtotal.getStok()));
                    if (response.isSuccessful() && response.body()!=null){
                        shimer_material.stopShimmerAnimation();
                        shimer_material.setVisibility(View.GONE);
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
                    //Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                    Log.e("errors", t.toString());
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Catch Wisata" + e, Toast.LENGTH_SHORT).show();
            Log.e("wisata", String.valueOf(e));
        }
    }

}