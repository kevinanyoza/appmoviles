package com.example.apptransporte1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptransporte1.ui.login.UserRegister;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportarIncidencias extends Fragment {

    EditText txtdescrip;
    Button btnenviarinc ;
    Spinner spincidencias, sptransporte;

    public ReportarIncidencias() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reportar_incidencias, container, false);

        txtdescrip = v.findViewById(R.id.txtdescripcioninc);
        txtdescrip = v.findViewById(R.id.txtdescripcioninc);
        spincidencias = v.findViewById(R.id.spincidencia);
        sptransporte =v.findViewById(R.id.sptransporte);
        btnenviarinc = v.findViewById(R.id.btnenviarinci);
        String id_usuario = getActivity().getIntent().getStringExtra("id_usuario");//este es el id del usuario iniciado
        Toast.makeText(getActivity(), "hola"+id_usuario, Toast.LENGTH_SHORT).show();

        btnenviarinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarIncidencia();
            }
        });

        //registrarIncidencia();
        return v;
    }

    private void  registrarIncidencia(){

        String date = "24/11/2020";
        String transporte = sptransporte.getSelectedItem().toString();
        String incidencias = spincidencias.getSelectedItem().toString();
        String descripcion = txtdescrip.getText().toString();
        String usuario = "1";

        String url = "http://aplicacionanyoza.atwebpages.com/index.php/registrarincidencia";

        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("fecha",date);
                parametros.put("tipotransporte",transporte);
                parametros.put("tipoincidencia",incidencias);
                parametros.put("descripcion",descripcion);
                parametros.put("ususario",usuario);
                return  parametros;
            }
        };

        RequestQueue cola = Volley.newRequestQueue(getContext());
        cola.add(peticion);

    }

}