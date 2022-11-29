package com.example.rcgnwhrsinventory.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rcgnwhrsinventory.MainActivity;
import com.example.rcgnwhrsinventory.R;

public class ScanFragment extends Fragment {
    MainActivity mainActivity;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_scan, container, false);

        return v;
    }
}