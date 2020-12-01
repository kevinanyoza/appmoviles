package com.example.apptransporte1.ui.gallery;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptransporte1.MainActivity;
import com.example.apptransporte1.R;
import com.example.apptransporte1.domain.Usuario;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    ProgressDialog progressDoalog;

    EditText txtCodigo;
    EditText txtMonto;
    Button btnTransferir;
    Button btnBuscar;
    TextView txtnombre;
    TextView txtsaldo;

    //Cambiar usuario logueado en este string .......
    // Validar que el saldo de usuario logueado debe ser menor al monto ingresado.
    // Lo mejor es que siempre haya una tabla "recarga" para tener un historial.
    //String usuarioLogueado = "1";
    Usuario usuarioTransferir;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.editTextNumber);
        String id_usuario = getActivity().getIntent().getStringExtra("id_usuario");//este es el id del usuario iniciado
        //Toast.makeText(getActivity(), "hola"+id_usuario, Toast.LENGTH_SHORT).show();


        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        txtCodigo = root.findViewById(R.id.txtCodigo);
        txtMonto = root.findViewById(R.id.txtMonto);
        btnTransferir = root.findViewById(R.id.btnTransferir);
        btnBuscar = root.findViewById(R.id.btnBuscar);
        txtnombre = root.findViewById(R.id.txtnombre);
/*    txtsaldo  = root.findViewById(R.id.txtsaldo);*/
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarcodigo();
            }
        });

        btnTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAlerta(id_usuario);
            }
        });

        return root;

    }


    private void buscarcodigo() {
        String codigo = txtCodigo.getText().toString();
        String url = "http://aplicacionanyoza.atwebpages.com/index.php/usuario/" + codigo;
        showdialog();
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arreglo = new JSONArray(response);
                    List<String> items = new ArrayList<>();
                    for (int i = 0; i < arreglo.length(); i++) {
                        JSONObject objeto = arreglo.getJSONObject(i);
                        Log.i("galeria fragment",objeto.toString());
                        usuarioTransferir = new Gson().fromJson(objeto.toString(), Usuario.class);
                        txtnombre.setText(usuarioTransferir.getNombres()+" "+usuarioTransferir.getPaterno()+" "+usuarioTransferir.getMaterno());
                       /* txtsaldo.setText("S/. " + usuario.getSaldo());*/
                    }



                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDoalog.dismiss();
            }

        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);
    }

    private void showdialog() {
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Obteniendo Datos");
        progressDoalog.setTitle("Espere por favor");
        progressDoalog.show();
    }

     private void   MostrarAlerta(String id_usuario){
         // get alert_dialog.xml view
         LayoutInflater li = LayoutInflater.from(getActivity());
         View promptsView = li.inflate(R.layout.alerta, null);

         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                 getActivity());

         // set alert_dialog.xml to alertdialog builder
         alertDialogBuilder.setView(promptsView);

         final EditText userInput = (EditText) promptsView.findViewById(R.id.etUserInput);

         // set dialog message
         alertDialogBuilder
                 .setCancelable(false)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                         // get user input and set it to result
                         // edit text
                         validarContrasena(userInput.getText().toString(),id_usuario);
                     }
                 })
                 .setNegativeButton("Cancel",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 dialog.cancel();
                             }
                         });

         // create alert dialog
         AlertDialog alertDialog = alertDialogBuilder.create();

         // show it
         alertDialog.show();
     }

    private void validarContrasena(String value, String id_usuario) {
       String url = "http://aplicacionanyoza.atwebpages.com/index.php/usuario";
        showdialog();
        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arreglo = new JSONArray(response);
                    List<String> items = new ArrayList<>();
                    if (arreglo.length() > 0) {
                        progressDoalog.dismiss();
                        actualizaMontoRecarga (txtMonto.getText().toString(), id_usuario);
                    }else {
                        progressDoalog.dismiss();
                        Toast.makeText (getActivity(), "Contraseña incorrecta, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
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
            params.put("idUsuario", id_usuario);
            params.put("clave",value);
            return params;
        }};

        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);


    }



    private void actualizaMontoRecarga (String monto ,String id_usuario){
        int montoRecarga = Integer.parseInt(monto);
        int montoActual = Integer.parseInt(usuarioTransferir.getSaldo());

        int resultado =  montoRecarga + montoActual;

        String url = "http://aplicacionanyoza.atwebpages.com/index.php/usuario/recarga";
        showdialog();
        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDoalog.dismiss();
                    actualizaMontoOrigen (txtMonto.getText().toString(), id_usuario);
                } catch (Exception e) {
                    progressDoalog.dismiss();
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
                params.put("idUsuario", usuarioTransferir.getIdUsuario());
                params.put("saldo", ""+resultado);
                return params;
            }};

        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);

    }


    private void actualizaMontoOrigen (String monto, String id_usuario){
        String url = "http://aplicacionanyoza.atwebpages.com/index.php/usuario/recarga/origen";
        showdialog();
        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getActivity(), "Transferencia correcta", Toast.LENGTH_SHORT).show();
                    progressDoalog.dismiss();
                } catch (Exception e) {
                    progressDoalog.dismiss();
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
                params.put("idUsuario", id_usuario);
                params.put("saldo", ""+ monto);
                return params;
            }};

        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);

    }

}



