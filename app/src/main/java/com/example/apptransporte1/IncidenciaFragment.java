package com.example.apptransporte1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apptransporte1.ui.login.UserRegister;


public class IncidenciaFragment extends Fragment {
    Button btnreportarinc ,btnlistinc;

    public IncidenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_incidencia, container, false);
        btnreportarinc = v.findViewById(R.id.btnreportinc);
        btnreportarinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.reportarIncidencias);
            }
        });

        btnlistinc = v.findViewById(R.id.btnlistinc);
        btnlistinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ListarIncidencia.class);
                startActivity(intent);
            }
        });


        return v;
    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);




        }
}