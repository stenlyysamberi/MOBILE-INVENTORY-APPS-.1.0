package com.example.rcgnwhrsinventory.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcgnwhrsinventory.DetailActivity;
import com.example.rcgnwhrsinventory.Model.Mmaterial;
import com.example.rcgnwhrsinventory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.viewHolder> {
    List<Mmaterial> mmaterials;
    Context context;

    public MaterialAdapter(List<Mmaterial> mmaterials, Context context) {
        this.mmaterials = mmaterials;
        this.context = context;
    }

    @NonNull
    @Override
    public MaterialAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_material, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.a.setText(String.valueOf(mmaterials.get(position).getMaterial_name()));
        holder.b.setText(String.valueOf(mmaterials.get(position).getCreated()));
        holder.c.setText(String.valueOf(mmaterials.get(position).getContainer() + " - CONTAINER"));
        holder.d.setText(String.valueOf(mmaterials.get(position).getTotal() + " - "+ mmaterials.get(position).getUom()));

        Picasso.with(context).load("http://192.168.43.110:8000/image/"+mmaterials.get(position).getFile()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("nama", mmaterials.get(position).getMaterial_name());
                i.putExtra("container",mmaterials.get(position).getContainer());
                i.putExtra("serial", mmaterials.get(position).getMaterial_number());

                String angkah = String.valueOf(mmaterials.get(position).getTotal());
                i.putExtra("jumlah", angkah);

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mmaterials.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView a,b,c,d;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_material);
            a = itemView.findViewById(R.id.nama_material);
            b = itemView.findViewById(R.id.tgl_material);
            c = itemView.findViewById(R.id.container);
            d = itemView.findViewById(R.id.material_total);
        }
    }
}
