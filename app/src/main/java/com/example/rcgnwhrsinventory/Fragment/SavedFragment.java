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
import com.example.rcgnwhrsinventory.Adapter.Historyadapter;
import com.example.rcgnwhrsinventory.Internet.APis;
import com.example.rcgnwhrsinventory.Internet.Endpoints;
import com.example.rcgnwhrsinventory.MainActivity;
import com.example.rcgnwhrsinventory.Model.Mactivity;
import com.example.rcgnwhrsinventory.Model.Main;
import com.example.rcgnwhrsinventory.Model.Mhistory;
import com.example.rcgnwhrsinventory.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedFragment extends Fragment {

    MainActivity mainActivity;
    Historyadapter historyadapter;
    RecyclerView recyclerView;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_saved, container, false);

        String id = "2";
        summery(id);
        return v;

    }

    void summery(String id){
//        shimmeractivities = v.findViewById(R.id.shimer_activitis);
//        shimmeractivities.setVisibility(View.VISIBLE);
//        shimmeractivities.startShimmerAnimation();
        try {
            Endpoints endpoint = APis.getRetrofitInstance().create(Endpoints.class);
            Call<Main> info = endpoint.history(id);
            info.enqueue(new Callback<Main>() {
                @Override
                public void onResponse(Call<Main> call, Response<Main> response) {
                    List<Mhistory> info = response.body().getHistory();

                    if (response.isSuccessful() && response.body()!=null){
//                      shimmeractivities.stopShimmerAnimation();
//                      shimmeractivities.setVisibility(View.GONE);
                        recyclerView = v.findViewById(R.id.recy_summery);
                        recyclerView.setEnabled(false);
                        historyadapter = new Historyadapter(info,getActivity());
                        recyclerView.setAdapter(historyadapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
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