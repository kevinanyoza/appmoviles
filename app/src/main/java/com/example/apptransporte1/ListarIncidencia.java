package com.example.apptransporte1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarIncidencia extends AppCompatActivity {

EditText txtInc;
Button btnbuscar;
ListView lstInc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_incidencia);
        asignarReferencias();
    }
    private void asignarReferencias(){
        txtInc = findViewById(R.id.txtInc);
        btnbuscar = findViewById(R.id.btnBuscar);
        lstInc = findViewById(R.id.lstInc);
        //String id_usuario = getActivity().getIntent().getStringExtra("id_usuario");
        //Toast.makeText(getActivity(), "hola"+id_usuario, Toast.LENGTH_SHORT).show();
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListarIncidencia();
            }
        });
    }
    private void ListarIncidencia(){
       String texto = txtInc.getText().toString();
       String url = "http://aplicacionanyoza.atwebpages.com/index.php/incidencias/";

        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arreglo = new JSONArray(response);
                    List<String> items= new ArrayList<>();
                    for (int i=0; i<arreglo.length();i++){

                        JSONObject objecto = arreglo.getJSONObject(i);
                        items.add(objecto.getString("fecha")+ "---"+ objecto.getString("tipo_incidencia")+"--"+objecto.getString("descripcion"));
                    }
                    ArrayAdapter<String>adapter = new ArrayAdapter<>(ListarIncidencia.this, android.R.layout.simple_expandable_list_item_1,items);
                    lstInc.setAdapter(adapter);
                }catch (JSONException e){
                    Toast.makeText(ListarIncidencia.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListarIncidencia.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue cola= Volley.newRequestQueue(this);
        cola.add(peticion);
    }
}