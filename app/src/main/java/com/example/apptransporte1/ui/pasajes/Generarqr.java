package com.example.apptransporte1.ui.pasajes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptransporte1.R;

import java.util.HashMap;
import java.util.Map;


public class Generarqr extends Fragment {

    CheckBox checkboxconfirmar;
    EditText monto;

    public Generarqr() {
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
        View v = inflater.inflate(R.layout.fragment_generarqr, container, false);

        Bundle bundle =  getActivity().getIntent().getExtras();
        String id_usuario = getActivity().getIntent().getStringExtra("id_usuario");//este es el id del usuario iniciado

        //Toast.makeText(getActivity(), "monto: "+ montototal, Toast.LENGTH_SHORT).show();

        checkboxconfirmar = v.findViewById(R.id.checkboxconfirmar);
        checkboxconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarAlerta(id_usuario);
            }
        });

        return v;
    }

    private void   MostrarAlerta(String id_usuario){
        final EditText input = new EditText(getActivity());

        AlertDialog dialog = (new AlertDialog.Builder(getContext()))
                .setTitle("Confirmación"+id_usuario)
                .setMessage("Esta seguro de realizar la compra:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(getView()).navigate(R.id.nav_pasajes);
                        //actualizarSaldo(id_usuario);
                        Toast.makeText( getActivity(), "Compra Realizada Satisfactoriamente :)", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        //ialog.setView(input, (int)(19*dpi), (int)(5*dpi), (int)(14*dpi), (int)(5*dpi) );
        dialog.show();

        //alertDialog.show();
    }

    private void  actualizarSaldo(String id_usuario){
        String url = "http://aplicacionanyoza.atwebpages.com/index.php/usuario/recarga/origen";
        String montoimpor = monto.getText().toString();
        String usuario = id_usuario;
        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getActivity(), "Transferencia correcta", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idUsuario", usuario );
                params.put("saldo", ""+ montoimpor);
                return params;
            }};

        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);

    }


    private void actualizaMontoOrigen (String monto){

    }



}