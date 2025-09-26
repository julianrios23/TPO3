package com.julian.tpo3.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.julian.tpo3.R;

public class SalirFragment extends Fragment {
    private SalirFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_salir, container, false);
        viewModel = new ViewModelProvider(this).get(SalirFragmentViewModel.class);
        observarViewModel();
        viewModel.solicitarSalir();
        return root;
    }

    private void observarViewModel() {
        viewModel.getMostrarDialogo().observe(getViewLifecycleOwner(), mostrar -> {
            if (mostrar != null && mostrar) mostrarDialogoSalir();
        });
        viewModel.getCerrarApp().observe(getViewLifecycleOwner(), cerrar -> {
            if (cerrar != null && cerrar && getActivity() != null) getActivity().finish();
        });
        viewModel.getNavegarAtras().observe(getViewLifecycleOwner(), atras -> {
            if (atras != null && atras && getActivity() != null) getActivity().onBackPressed();
        });
    }

    private void mostrarDialogoSalir() {
        if (getActivity() == null) return;
        new AlertDialog.Builder(getActivity())
                .setTitle("Salir")
                .setMessage("¿Desea cerrar la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> viewModel.confirmarSalir())
                .setNegativeButton("No", (dialog, which) -> viewModel.cancelarSalir())
                .setCancelable(false)
                .show();
    }
}
