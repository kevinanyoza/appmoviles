package com.example.apptransporte1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.apptransporte1.ui.gallery.GalleryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.VisibleForTesting;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    //String id_usuario = getIntent().getStringExtra("id_usuario");
    //String correo = getIntent().getStringExtra("correo");
    //String nombres = getIntent().getStringExtra("nombres");

    TextView txtcorreo, txtnombre ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //final String ejemplo = id_usuario;
        String id_usuario = getIntent().getStringExtra("id_usuario");
        String correo = getIntent().getStringExtra("correo");
        String nombres = getIntent().getStringExtra("nombres");
        //Toast.makeText(this, id_usuario, Toast.LENGTH_SHORT).show();
        //txtcorreo = (TextView) findViewById(R.id.txtcorreomenu);

        //txtcorreo.setText("hola");

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txtcorreomenu);
        TextView navnombres = (TextView) headerView.findViewById(R.id.txtnombresmenu);
        navUsername.setText(correo);
        navnombres.setText(nombres);
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_home, R.id.nav_gallery, R.id.incidenciaFragment, R.id.gestinarSaldoFragment,R.id.nav_pasajes)


                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent intent = new Intent(getApplicationContext(), RecargarFragment.class);
        intent.putExtra("id_usuario",id_usuario );
        Intent intent2 = new Intent(getApplicationContext(), ReportarIncidencias.class);
        intent2.putExtra("id_usuario",id_usuario );
        Intent intent3 = new Intent(getApplicationContext(), GalleryFragment.class);
        intent3.putExtra("id_usuario",id_usuario );

        //startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}