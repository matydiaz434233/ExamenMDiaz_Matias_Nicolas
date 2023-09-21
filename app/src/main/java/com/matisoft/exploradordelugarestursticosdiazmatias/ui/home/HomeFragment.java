package com.matisoft.exploradordelugarestursticosdiazmatias.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.maps.SupportMapFragment;
import com.matisoft.exploradordelugarestursticosdiazmatias.R;
import com.matisoft.exploradordelugarestursticosdiazmatias.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.obtenerUltimaUbicacion();
        binding = FragmentHomeBinding.bind(root);
        homeViewModel.getMMapa().observe(getViewLifecycleOwner(), new Observer<HomeViewModel.mapaActual>() {
            @Override
            public void onChanged(HomeViewModel.mapaActual mapaActual) {
                SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (smf != null) {
                    smf.getMapAsync(mapaActual);
                }
            }
        });
        homeViewModel.obtenerMapa();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
