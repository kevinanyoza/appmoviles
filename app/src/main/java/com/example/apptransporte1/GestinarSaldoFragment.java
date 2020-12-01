package com.example.apptransporte1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.apptransporte1.R.id.recargarFragment;


public class GestinarSaldoFragment extends Fragment {

    public GestinarSaldoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gestinar_saldo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnRecargar= view.findViewById(R.id.btnRecargar);
        Button btnTransferir= view.findViewById(R.id.btnTransferir);
        Button btnMetodos=view.findViewById(R.id.btnMetodos);

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(recargarFragment);
            }
        });
        btnTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_gallery);
            }
        });
        btnMetodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.metodosdePagoFragment);
            }
        });

    }
}