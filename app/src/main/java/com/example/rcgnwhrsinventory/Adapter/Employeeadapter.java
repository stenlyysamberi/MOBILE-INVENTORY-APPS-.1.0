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
import com.example.rcgnwhrsinventory.Model.Memployee;
import com.example.rcgnwhrsinventory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Employeeadapter extends RecyclerView.Adapter<Employeeadapter.activitiess> {


    List<Memployee> mactivities;
    Context context;

    public Employeeadapter(List<Memployee> mactivities, Context context) {
        this.mactivities = mactivities;
        this.context = context;
    }

    @NonNull
    @Override
    public activitiess onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_employee, parent, false);
        return new activitiess(view);
    }

    @Override
    public void onBindViewHolder(@NonNull activitiess holder, int position) {

        Picasso.with(context).load(mactivities.get(position).getProfil()).into(holder.imageView);

//        Picasso.with(context).load("http://192.168.43.110:8000/image/"+mactivities.get(position).getProfil()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mactivities.size();
    }

    public class activitiess extends RecyclerView.ViewHolder {
        TextView text1,text2;
        ImageView imageView;
        public activitiess(@NonNull View itemView) {
            super(itemView);
//            text1 = itemView.findViewById(R.id.activity_name);
//            text2 = itemView.findViewById(R.id.activity_total);
            imageView = itemView.findViewById(R.id.img_user);
        }
    }
}
