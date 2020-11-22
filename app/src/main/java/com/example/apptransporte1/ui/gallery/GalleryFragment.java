package com.example.apptransporte1.ui.gallery;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.apptransporte1.MainActivity;
import com.example.apptransporte1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    ProgressDialog progressDoalog;

    EditText txtCodigo;
    EditText txtMonto;
    Button btnTransferir;
    Button btnBuscar;
    TextView txtnombre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.editTextNumber);
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
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarcodigo();
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
                    txtnombre.setText("nombre: "+objeto.getString("nombres"));
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

    }

    private void showdialog() {

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.show();
    }


}



