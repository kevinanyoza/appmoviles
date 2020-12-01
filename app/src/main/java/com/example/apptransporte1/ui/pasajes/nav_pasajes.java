package com.example.apptransporte1.ui.pasajes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.apptransporte1.MainActivity;
import com.example.apptransporte1.R;


public class nav_pasajes extends Fragment {

    Button btngenerarqr;
    TableLayout menutabcorredor, menutabtren,menutabmetropoli, tableLayout;
    TextView txtpreuni;
    Spinner spcantidad;
    EditText multimonto;

    public nav_pasajes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_pasajes, container, false);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btngenerarqr = view.findViewById(R.id.btngenerarqr);
        menutabcorredor = view.findViewById(R.id.tabcorredor);
        menutabmetropoli = view.findViewById(R.id.tabmetropo);
        menutabtren = view.findViewById(R.id.tabmetrolima);
        txtpreuni = view.findViewById(R.id.txtpreunitario);
        spcantidad = view.findViewById(R.id.spcantidad);
        multimonto = view.findViewById(R.id.multxtmonto);



    /*    menutabtren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtpreuni.setText("1.50");
            }
        });

        menutabmetropoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtpreuni.setText("2.50");
            }
        });*/

        spcantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cant = spcantidad.getSelectedItem().toString();
                String prec = txtpreuni.getText().toString();
                double cantidad, precio, total;
                String importe;

                cantidad = Double.parseDouble(cant);
                precio = Double.parseDouble(prec);

                total = cantidad * precio;
                //importe =
                multimonto.setText(Double.toString(total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment);

        btngenerarqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.generarqr);
            }
        });

    }
}