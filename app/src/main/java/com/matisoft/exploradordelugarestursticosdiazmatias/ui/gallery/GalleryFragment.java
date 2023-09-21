package com.matisoft.exploradordelugarestursticosdiazmatias.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.matisoft.exploradordelugarestursticosdiazmatias.databinding.FragmentGalleryBinding;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private LugarAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView rv = binding.rvLugares;
        GridLayoutManager glm = new GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);
        adapter = new LugarAdapter(new ArrayList<>(), requireContext(), getLayoutInflater());
        rv.setAdapter(adapter);
        galleryViewModel.armarLista();
        galleryViewModel.getListaMutable().observe(getViewLifecycleOwner(), new Observer<List<Lugar>>() {
            @Override
            public void onChanged(List<Lugar> lugars) {


                adapter.setListaLugares(lugars);
                //adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
    private String convertirListaLugaresAJson(List<Lugar> lugares) {
        Gson gson = new Gson();
        String lugaresJson = gson.toJson(lugares);
        return lugaresJson;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



