package com.matisoft.exploradordelugarestursticosdiazmatias.ui.gallery;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matisoft.exploradordelugarestursticosdiazmatias.R;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends AndroidViewModel {
    private MutableLiveData<List<Lugar>> listaMutable;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        listaMutable = new MutableLiveData<>(); // Inicializa listaMutable aquí
    }

    public LiveData<List<Lugar>> getListaMutable(){
        return listaMutable;
    }

    public void armarLista(){
        List<Lugar> lista=new ArrayList<>();
        lista.add(new Lugar("Potrero","Un lugar bello lleno de paisajes"));
        lista.add(new Lugar("La Punta","Un lugar bello, con una gran Universidad"));
        lista.add(new Lugar("Trapiche","Personas muy buenas"));
        lista.add(new Lugar("Merlo","Bellisimo paisaje , gran cultura"));
        listaMutable.setValue(lista);
    }
}
