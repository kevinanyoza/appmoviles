package com.example.apptransporte1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        FusedLocationProviderClient fusedLocationProviderClient;
        double latitud = 0, longitud = 0;

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng Metropolitano1 = new LatLng(-11.98138,-77.05888);
            LatLng Metropolitano2 = new LatLng(-11.98946,-77.05702);
            LatLng Metropolitano3 = new LatLng(-11.99472,-77.05602);
            LatLng Metropolitano4 = new LatLng(-11.99826,-77.05525);
            LatLng Metropolitano5 = new LatLng(-12.00171,-77.05475);
            LatLng Metropolitano6 = new LatLng(-12.00580,-77.05398);
            LatLng Metropolitano7 = new LatLng(-12.01142,-77.05290);
            LatLng Metropolitano8 = new LatLng(-12.01726,-77.05153);
            LatLng Metropolitano9 = new LatLng(-12.02402,-77.04889);
            LatLng Metropolitano10 = new LatLng(-12.02937,-77.04419);
            LatLng Metropolitano11 = new LatLng(-12.03616,-77.04368);
            LatLng Metropolitano12 = new LatLng(-12.04588,-77.03701);
            LatLng Metropolitano13 = new LatLng(-12.04856,-77.03296);
            LatLng Metropolitano14 = new LatLng(-12.05169,-77.03268);
            LatLng Metropolitano15 = new LatLng(-12.04707,-77.04269);
            LatLng Metropolitano16 = new LatLng(-12.05178,-77.04232);
            LatLng Metropolitano17 = new LatLng(-12.05688,-77.04175);
            LatLng Metropolitano18 = new LatLng(-12.05736,-77.03606);
            LatLng Metropolitano19 = new LatLng(-12.06806,-77.03216);
            LatLng Metropolitano20 = new LatLng(-12.07652,-77.02897);
            LatLng Metropolitano21 = new LatLng(-12.08092,-77.02644);
            LatLng Metropolitano22 = new LatLng(-12.08879,-77.02360);
            LatLng Metropolitano23 = new LatLng(-12.09669,-77.02513);
            LatLng Metropolitano24 = new LatLng(-12.10211,-77.02725);
            LatLng Metropolitano25 = new LatLng(-12.10824,-77.02642);
            LatLng Metropolitano26 = new LatLng(-12.11328,-77.02521);
            LatLng Metropolitano27 = new LatLng(-12.11893,-77.02596);
            LatLng Metropolitano28 = new LatLng(-12.12476,-77.02425);
            LatLng Metropolitano29 = new LatLng(-12.12941,-77.02283);
            LatLng Metropolitano30 = new LatLng(-12.13566,-77.01870);
            LatLng Metropolitano31 = new LatLng(-12.14113,-77.01771);
            LatLng Metropolitano32 = new LatLng(-12.14832,-77.02013);
            LatLng Metropolitano33 = new LatLng(-12.15196,-77.01964);
            LatLng Metropolitano34 = new LatLng(-12.15854,-77.01894);
            LatLng Metropolitano35 = new LatLng(-12.16735,-77.01866);
            LatLng Metropolitano36 = new LatLng(-12.17368,-77.01462);
            LatLng Metropolitano37 = new LatLng(-12.17745,-77.00981);
            LatLng Tren1 = new LatLng(-11.95956,-76.98754);
            LatLng Tren2 = new LatLng(-11.96786,-76.99349);
            LatLng Tren3 = new LatLng(-11.97457,-76.99996);
            LatLng Tren4 = new LatLng(-11.98455,-77.00669);
            LatLng Tren5 = new LatLng(-11.99625,-77.00996);
            LatLng Tren6 = new LatLng(-12.00610,-77.00541);
            LatLng Tren7 = new LatLng(-12.01809,-77.00280);
            LatLng Tren8 = new LatLng(-12.02755,-77.01120);
            LatLng Tren9 = new LatLng(-12.04128,-77.01171);
            LatLng Tren10 = new LatLng(-12.04609,-77.00972);
            LatLng Tren11 = new LatLng(-12.05401,-77.01296);
            LatLng Tren12 = new LatLng(-12.06526,-77.01235);
            LatLng Tren13 = new LatLng(-12.07507,-77.01107);
            LatLng Tren14 = new LatLng(-12.08632,-77.00380);
            LatLng Tren15 = new LatLng(-12.09992,-77.00191);
            LatLng Tren16 = new LatLng(-12.10986,-77.00045);
            LatLng Tren17 = new LatLng(-12.12715,-77.00038);
            LatLng Tren18 = new LatLng(-12.13470,-76.99697);
            LatLng Tren19 = new LatLng(-12.14248,-76.99100);
            LatLng Tren20 = new LatLng(-12.15084,-76.97948);
            LatLng Tren21 = new LatLng(-12.15647,-76.96569);
            LatLng Tren22 = new LatLng(-12.16073,-76.95663);
            LatLng Tren23 = new LatLng(-12.16922,-76.95041);
            LatLng Tren24 = new LatLng(-12.18226,-76.94692);
            LatLng Tren25 = new LatLng(-12.19595,-76.94014);
            LatLng Tren26 = new LatLng(-12.20697,-76.93358);
            googleMap.addMarker(new MarkerOptions().position(Metropolitano1).title("Metropolitano-Est.Naranjal"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano2).title("Metropolitano-Est.Izaguirre"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano3).title("Metropolitano-Est.Pacifico"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano4).title("Metropolitano-Est.Independencia"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano5).title("Metropolitano-Est.Los Jazmines"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano6).title("Metropolitano-Est.Tomas Valle"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano7).title("Metropolitano-Est.El Milagro"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano8).title("Metropolitano-Est.Honorio Delgado"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano9).title("Metropolitano-Est.UNI"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano10).title("Metropolitano-Est.Parque del Trabajo"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano11).title("Metropolitano-Est.Caqueta"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano12).title("Metropolitano-Est.Tacna"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano13).title("Metropolitano-Est.Jiron de la Union"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano14).title("Metropolitano-Est.Colmena"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano15).title("Metropolitano-Est.Dos de Mayo"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano16).title("Metropolitano-Est.Quilca"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano17).title("Metropolitano-Est.España"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano18).title("Metropolitano-Est.Estacion Central"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano19).title("Metropolitano-Est.Estadio Nacional"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano20).title("Metropolitano-Est.Mexico"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano21).title("Metropolitano-Est.Canada"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano22).title("Metropolitano-Est.Javier Prado"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano23).title("Metropolitano-Est.Carnaval Moreyra"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano24).title("Metropolitano-Est.Aramburu"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano25).title("Metropolitano-Est.Domingo Orue"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano26).title("Metropolitano-Est.Angamos"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano27).title("Metropolitano-Est.Ricardo Palma"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano28).title("Metropolitano-Est.Benavides"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano29).title("Metropolitano-Est.28 de julio"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano30).title("Metropolitano-Est.Plaza Flores"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano31).title("Metropolitano-Est.Balta"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano32).title("Metropolitano-Est.Bulevar"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano33).title("Metropolitano-Est.Estadio Union"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano34).title("Metropolitano-Est.Escuela Militar"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano35).title("Metropolitano-Est.Teran"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano36).title("Metropolitano-Est.Rosario de Villa"));
            googleMap.addMarker(new MarkerOptions().position(Metropolitano37).title("Metropolitano-Est.Matellini"));
            googleMap.addMarker(new MarkerOptions().position(Tren1).title("Tren-Est.Bayovar"));
            googleMap.addMarker(new MarkerOptions().position(Tren2).title("Tren-Est.Santa Rosa"));
            googleMap.addMarker(new MarkerOptions().position(Tren3).title("Tren-Est.San Martin"));
            googleMap.addMarker(new MarkerOptions().position(Tren4).title("Tren-Est.San Carlos"));
            googleMap.addMarker(new MarkerOptions().position(Tren5).title("Tren-Est.Los Postes"));
            googleMap.addMarker(new MarkerOptions().position(Tren6).title("Tren-Est.Los Jardines"));
            googleMap.addMarker(new MarkerOptions().position(Tren7).title("Tren-Est.Piramide del Sol"));
            googleMap.addMarker(new MarkerOptions().position(Tren8).title("Tren-Est.Caja de Agua"));
            googleMap.addMarker(new MarkerOptions().position(Tren9).title("Tren-Est.Presbitero Maestro"));
            googleMap.addMarker(new MarkerOptions().position(Tren10).title("Tren-Est.El Angel"));
            googleMap.addMarker(new MarkerOptions().position(Tren11).title("Tren-Est.Miguel Grau"));
            googleMap.addMarker(new MarkerOptions().position(Tren12).title("Tren-Est.Gamarra"));
            googleMap.addMarker(new MarkerOptions().position(Tren13).title("Tren-Est.Arriola"));
            googleMap.addMarker(new MarkerOptions().position(Tren14).title("Tren-Est.La Cultura"));
            googleMap.addMarker(new MarkerOptions().position(Tren15).title("Tren-Est.San Borja Sur"));
            googleMap.addMarker(new MarkerOptions().position(Tren16).title("Tren-Est.Angamos"));
            googleMap.addMarker(new MarkerOptions().position(Tren17).title("Tren-Est.Cabitos"));
            googleMap.addMarker(new MarkerOptions().position(Tren18).title("Tren-Est.Ayacucho"));
            googleMap.addMarker(new MarkerOptions().position(Tren19).title("Tren-Est.Jorge Chavez"));
            googleMap.addMarker(new MarkerOptions().position(Tren20).title("Tren-Est.Atocongo"));
            googleMap.addMarker(new MarkerOptions().position(Tren21).title("Tren-Est.San Juan"));
            googleMap.addMarker(new MarkerOptions().position(Tren22).title("Tren-Est.Maria Auxiliadora"));
            googleMap.addMarker(new MarkerOptions().position(Tren23).title("Tren-Est.Villa Maria"));
            googleMap.addMarker(new MarkerOptions().position(Tren24).title("Tren-Est.Pumacahua"));
            googleMap.addMarker(new MarkerOptions().position(Tren25).title("Tren-Est.Parque Industrial"));
            googleMap.addMarker(new MarkerOptions().position(Tren26).title("Tren-Est.Villa el Salvador"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Metropolitano18));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Metropolitano18,50));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
/*
    private void UbicacionActual(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }else{
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            Tasks<Location> tarea = fusedLocationProviderClient.getLastLocation();
            tarea.addOnSuccessListener({
            @override
            public void onSuccess(Location location){

            }
            })
        }
    }*/
}