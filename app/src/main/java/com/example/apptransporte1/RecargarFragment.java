package com.example.apptransporte1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RecargarFragment extends Fragment {
    EditText txtMontoRecarga;
    Button btnRecarga;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_recargar, container, false);
        txtMontoRecarga = vista.findViewById(R.id.txtMontoRecarga);
        btnRecarga = vista.findViewById(R.id.btnRecarga);

        btnRecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecargarSaldo();

            }
        });

        return vista;

    }

    private void RecargarSaldo() {
        String recarga = txtMontoRecarga.getText().toString();
        if (recarga.equals("")) {
            validacion();
        } else {
            String URL = "http://aplicacionanyoza.atwebpages.com/index.php/saldo";
            StringRequest peticion = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getActivity(), "Se realizò la recarga", Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametro = new HashMap<>();
                    parametro.put("saldo", recarga);
                    return super.getParams();
                }
            };
            RequestQueue cola = Volley.newRequestQueue(getActivity());
            cola.add(peticion);
        }
    }
    private void validacion() {
        if (txtMontoRecarga.getText().toString().equals("")) {
            txtMontoRecarga.setError("Campo Obligatorio");
        }
        if (txtMontoRecarga.equals(" ") && txtMontoRecarga.equals(0) ) {

            Toast.makeText(getActivity(), "Inserta un numero", Toast.LENGTH_LONG).show();
        }
    }
}