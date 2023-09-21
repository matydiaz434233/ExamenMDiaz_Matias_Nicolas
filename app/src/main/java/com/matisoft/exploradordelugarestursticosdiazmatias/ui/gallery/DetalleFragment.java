package com.matisoft.exploradordelugarestursticosdiazmatias.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matisoft.exploradordelugarestursticosdiazmatias.R;
import com.matisoft.exploradordelugarestursticosdiazmatias.databinding.FragmentDetalleBinding;
import com.matisoft.exploradordelugarestursticosdiazmatias.modelos.Lugar;

import java.util.List;


public class DetalleFragment extends Fragment {

    private DetalleViewModel mViewModel;
    private FragmentDetalleBinding binding;

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMResultado().observe(getViewLifecycleOwner(), new Observer<List<Lugar>>() {
            @Override
            public void onChanged(List<Lugar> lugares) {
                if (lugares != null && !lugares.isEmpty()) {
                    Lugar lugar = lugares.get(0); // Obtén el primer lugar (puedes ajustar esto según tu lógica)
                    String horario = lugar.getHorariosApertura(); // Suponiendo que Lugar tiene un método getHorario()
                    binding.tvHorario.setText(horario);
                }
            }
        });

        mViewModel.obtenerDetalles(getArguments());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
