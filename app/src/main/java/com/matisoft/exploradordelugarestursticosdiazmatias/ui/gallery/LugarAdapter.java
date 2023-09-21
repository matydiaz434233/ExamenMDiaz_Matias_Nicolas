package com.matisoft.exploradordelugarestursticosdiazmatias.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.matisoft.exploradordelugarestursticosdiazmatias.R;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.util.ArrayList;
import java.util.List;
public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {
    private List<Lugar> listaLugares;
    private Context context;
    private LayoutInflater li;
    public LugarAdapter(List<Lugar> listaLugares, Context context, LayoutInflater li) {
        this.listaLugares = listaLugares;
        this.context = context;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.carta_lugares,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lugar lugar = listaLugares.get(position);
        if (lugar != null) {
            holder.descripcion.setText(lugar.getDescripcion());
            holder.nombre.setText(lugar.getNombreLugar());
        }
    }
    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public void setListaLugares(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descripcion;
        private TextView nombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            nombre = itemView.findViewById(R.id.tvNombre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Bundle bundle = new Bundle();
                    //bundle.putParcelableArrayList("listaLugares", (ArrayList<Lugar>) listaLugares);
                    //Navigation.findNavController(view).navigate(R.id.nav_detalle, bundle);
                    Navigation.findNavController(view).navigate(R.id.nav_detalle);
                }
            });
        }
    }


    private String convertirListaLugaresAJson(List<Lugar> lugares) {
        Gson gson = new Gson();
        String lugaresJson = gson.toJson(lugares);
        return lugaresJson;
    }
}