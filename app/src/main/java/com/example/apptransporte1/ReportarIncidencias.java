package com.example.apptransporte1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    Button btnenviarinc ,foto ;
    Spinner spincidencias, sptransporte;
    ImageView picture;


    private static final int REQUEST_PERMISSION_CAMERA = 101;
    private static final int REQUEST_IMAGE_CAMERA = 101;

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
        picture = v.findViewById(R.id.picture);
        foto = v.findViewById(R.id.btncamera);
        String id_usuario = getActivity().getIntent().getStringExtra("id_usuario");//este es el id del usuario iniciado
        Toast.makeText(getActivity(), "hola"+id_usuario, Toast.LENGTH_SHORT).show();


        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        gocamera();
                    } else {

                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                    }
                } else {
                    gocamera();
                }
                //registrarIncidencia();
            }

        });



        btnenviarinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarIncidencia();
            }
        });



        //registrarIncidencia();
        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CAMERA){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                gocamera();
            }else{
                Toast.makeText(getActivity(), "You need to enable permissions", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAMERA){
            if(resultCode == Activity.RESULT_OK){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                picture.setImageBitmap(bitmap);
                Log.i("TAG","RESULT=>"+ bitmap);
            }else{

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gocamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(cameraIntent,REQUEST_IMAGE_CAMERA);
        }
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