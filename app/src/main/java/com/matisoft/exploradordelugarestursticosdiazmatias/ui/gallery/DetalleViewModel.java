package com.matisoft.exploradordelugarestursticosdiazmatias.ui.gallery;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.lang.reflect.Type;
import java.util.List;

public class DetalleViewModel extends ViewModel {
    private MutableLiveData<List<Lugar>> mDetalle = new MutableLiveData<>();
    public MutableLiveData<List<Lugar>> getMResultado(){
        if (mDetalle==null){
            mDetalle= new MutableLiveData<>();
        }
        return mDetalle;
    }
    public void obtenerDetalles(Bundle bundle) {
        if (bundle != null) {
            // Obtén la cadena JSON del Bundle
            String lugaresJson = bundle.getString("detalle");

            if (lugaresJson != null) {
                // Convierte la cadena JSON de nuevo a una lista de objetos Lugar
                List<Lugar> lugares = convertirJsonAListaLugares(lugaresJson);
                // Ahora puedes usar la lista de lugares en este método
                mDetalle.setValue(lugares);
            }
        }
    }
    private List<Lugar> convertirJsonAListaLugares(String lugaresJson) {
        Gson gson = new Gson();
        Type listaLugaresType = new TypeToken<List<Lugar>>() {}.getType();
        List<Lugar> lugares = gson.fromJson(lugaresJson, listaLugaresType);
        return lugares;
    }

}
