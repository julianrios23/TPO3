package com.julian.tpo3.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julian.tpo3.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSalirBinding binding = FragmentSalirBinding.inflate(inflater, container, false);
        mostrarDialogoSalir();
        return binding.getRoot();
    }

    private void mostrarDialogoSalir() {
        if (getActivity() == null) return;
        new AlertDialog.Builder(getActivity())
            .setTitle("Cerrar sesión")
            .setMessage("¿Está seguro que desea salir?")
            .setPositiveButton("Sí", (dialog, which) -> {
                getActivity().finish();
            })
            .setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            })
            .setCancelable(false)
            .show();
    }
}
