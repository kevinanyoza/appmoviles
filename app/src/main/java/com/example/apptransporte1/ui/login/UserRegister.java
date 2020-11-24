package com.example.apptransporte1.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.example.apptransporte1.R;

import java.util.HashMap;
import java.util.Map;

public class UserRegister extends AppCompatActivity {

    EditText txtnombre, txtapepater ,txtapemater, txtcorreo,txtpass;
    Button btnaceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        asignarReferencias();

    }

    private void asignarReferencias() {

        txtnombre = findViewById(R.id.txtnombresreg);
        txtapepater = findViewById(R.id.txtapepaterreg);
        txtapemater = findViewById(R.id.txtapematerreg);
        txtcorreo = findViewById(R.id.txtcorreoreg);
        txtpass = findViewById(R.id.txtcontraseñareg);

        btnaceptar =findViewById(R.id.btnregistraruser);

        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarUsuario();

            }
        });

    }

    private  void registrarUsuario(){

        String nombre = txtnombre.getText().toString();
        String paterno = txtapepater.getText().toString();
        String materno = txtapemater.getText().toString();
        String correo = txtcorreo.getText().toString();
        String clave = txtpass.getText().toString();


        if (nombre.equals("") ||  paterno.equals("") || materno.equals("") || correo.equals("") || clave.equals("")  ){
            Validacion();
        }else {



        //String texto = usuario.getText().toString();
        String url = "http://aplicacionanyoza.atwebpages.com/index.php/registrarusuario";

        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(UserRegister.this, "Se inserto Correctamente", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserRegister.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombre",nombre);
                parametros.put("paterno",paterno);
                parametros.put("materno",materno);
                parametros.put("correo",correo);
                parametros.put("clave",clave);
                return  parametros;
            }
        };

        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(peticion);
        }
    }

    private void Validacion() {

        Toast.makeText(this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();

    }

}