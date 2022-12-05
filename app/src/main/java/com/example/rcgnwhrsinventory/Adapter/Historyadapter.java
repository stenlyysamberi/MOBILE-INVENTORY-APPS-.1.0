package com.example.rcgnwhrsinventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcgnwhrsinventory.Model.Mactivity;
import com.example.rcgnwhrsinventory.Model.Mhistory;
import com.example.rcgnwhrsinventory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Historyadapter extends RecyclerView.Adapter<Historyadapter.activitiess> {

    List<Mhistory> mactivities;
    Context context;

    public Historyadapter(List<Mhistory> mactivities, Context context) {
        this.mactivities = mactivities;
        this.context = context;
    }

    @NonNull
    @Override
    public activitiess onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new activitiess(view);
    }
    @Override
    public void onBindViewHolder(@NonNull activitiess holder, int position) {
        holder.text1.setText(String.valueOf(mactivities.get(position).getNama()));
        holder.text2.setText(String.valueOf(mactivities.get(position).getMaterialName()));
        holder.text3.setText(String.valueOf(mactivities.get(position).getCreatedAt()));
        holder.text4.setText(String.valueOf(mactivities.get(position).getTotal()));
        holder.text5.setText(String.valueOf(mactivities.get(position).getStatus()));
        //Picasso.with(context).load("http://192.168.43.110:8000/image/"+mactivities.get(position).getFile()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return mactivities.size();
    }
    public class activitiess extends RecyclerView.ViewHolder {
        TextView text1,text2,text3,text4,text5;
        public activitiess(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.summery_name);
            text2 = itemView.findViewById(R.id.summer_name_produk);
            text3 = itemView.findViewById(R.id.summery_created);
            text4 = itemView.findViewById(R.id.summery_total);
            text5 = itemView.findViewById(R.id.summery_status);
        }
    }
}
