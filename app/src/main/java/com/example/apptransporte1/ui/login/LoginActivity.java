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

import com.example.apptransporte1.MainActivity;
import com.example.apptransporte1.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

   EditText usuario, password;
   Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarReferencias();
    }

    private void asignarReferencias() {
        usuario = findViewById(R.id.txtusuario);
        password = findViewById(R.id.txtpassword);
        btnlogin = findViewById(R.id.btningresar);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "hi", Toast.LENGTH_SHORT).show();
                validarUsuario();

            }
        });

    }

    private  void  validarUsuario(){

        String texto = usuario.getText().toString();
        String URL = "http://aplicacionanyoza.atwebpages.com/index.php/logeos/"+texto;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              try {


                  JSONArray arreglo = new JSONArray(response);

                  if(arreglo.length() > 0){
                      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                      startActivity(intent);
                      //Toast.makeText(LoginActivity.this, "buena", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(LoginActivity.this, "User o passs imvalido", Toast.LENGTH_SHORT).show();
                  }


              }catch (JSONException e){
                  Toast.makeText(LoginActivity.this, "Error de conexcion", Toast.LENGTH_SHORT).show();
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("password",password.getText().toString());
              return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}