package com.example.rcgnwhrsinventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcgnwhrsinventory.Model.Mactivity;
import com.example.rcgnwhrsinventory.Model.Mviewall;
import com.example.rcgnwhrsinventory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewallAdapter extends RecyclerView.Adapter<ViewallAdapter.activitiess> {

    Context context;
    List<Mviewall> mviewalls;


    public ViewallAdapter(Context context, List<Mviewall> mviewalls) {
        this.context = context;
        this.mviewalls = mviewalls;
    }

    @NonNull
    @Override
    public activitiess onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_material_full, parent, false);
        return new activitiess(view);
    }

    @Override
    public void onBindViewHolder(@NonNull activitiess holder, int position) {
        holder.text1.setText(String.valueOf(mviewalls.get(position).getMaterial_name()));
        holder.text2.setText(String.valueOf(mviewalls.get(position).getCreated()));
        holder.text3.setText(String.valueOf(mviewalls.get(position).getContainer() + " - " + "Container"));
        Picasso.with(context).load("http://192.168.43.110:8000/image/"+mviewalls.get(position).getFile()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mviewalls.size();
    }

    public class activitiess extends RecyclerView.ViewHolder {
        TextView text1,text2,text3;
        ImageView imageView;
        public activitiess(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.full_name);
            text2 = itemView.findViewById(R.id.tgl_full);
            text3 = itemView.findViewById(R.id.total_full);
            imageView = itemView.findViewById(R.id.img_full);
        }
    }
}
