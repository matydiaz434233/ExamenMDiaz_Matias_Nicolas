package com.matisoft.exploradordelugarestursticosdiazmatias.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.matisoft.exploradordelugarestursticosdiazmatias.R;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.io.Closeable;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<mapaActual> mMapa;
    private FusedLocationProviderClient fused;
    private MutableLiveData<Location> mLocation;
    private LatLng localizacion;
    Lugar potrero = crearLugar(-33.22031024931343, -66.22719325381014, "Potrero", "Un lugar bello lleno de paisajes", String.valueOf(R.drawable.potrero), "10 a 22");
    Lugar laPunta = crearLugar(-33.182374885755735, -66.30686187760851, "La Punta", "Un lugar bello, con una gran Universidad", String.valueOf(R.drawable.lapunta), "10 a 22");
    Lugar trapiche = crearLugar(-33.10746066622134, -66.07068807832395, "Trapiche", "Personas muy buenas", String.valueOf(R.drawable.trapiche), "10 a 22");
    Lugar merlo = crearLugar(-32.31540557049725, -65.02018597199452, "Merlo", "Bellisimo paisaje , gran cultura", String.valueOf(R.drawable.merlo), "10 a 22");

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        fused = LocationServices.getFusedLocationProviderClient(application);
        mLocation = new MutableLiveData<>(); // Inicializar mLocation aquí
        obtenerUltimaUbicacion();
    }
    public LiveData<mapaActual> getMMapa() {
        if (mMapa == null) {
            mMapa = new MutableLiveData<>();
            obtenerMapa(); // Llama a obtenerMapa() solo si es la primera vez
        }
        return mMapa;
    }
    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }
    public class mapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            if (localizacion != null) {
                googleMap.addMarker(new MarkerOptions().position(potrero.getLocalizacion()).title("Potrero"));
                googleMap.addMarker(new MarkerOptions().position(laPunta.getLocalizacion()).title("La Punta"));
                googleMap.addMarker(new MarkerOptions().position(trapiche.getLocalizacion()).title("Trapiche"));
                googleMap.addMarker(new MarkerOptions().position(merlo.getLocalizacion()).title("Merlo"));
                googleMap.addMarker(new MarkerOptions().position(localizacion).title("Ubicacion Actual"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(localizacion).zoom(19).bearing(45).tilt(70).build();
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
                googleMap.animateCamera(update);
            } else {
                Toast.makeText(context,"ESTE ES EL MAPA",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLocation.postValue(location);
                    localizacion = new LatLng(location.getLatitude(), location.getLongitude());
                    obtenerMapa();
                }
            }
        });
    }
    public void obtenerMapa() {
        mapaActual ma = new mapaActual();
        mMapa.setValue(ma);
    }
    public static Lugar crearLugar(double latitud, double longitud, String nombre, String descripcion, String foto, String horariosApertura) {
        LatLng ubicacion = new LatLng(latitud, longitud);
        return new Lugar(ubicacion, nombre, descripcion, foto, horariosApertura);
    }
}
