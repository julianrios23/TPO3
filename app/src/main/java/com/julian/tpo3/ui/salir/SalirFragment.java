package com.julian.tpo3.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.julian.tpo3.R;

public class SalirFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_salir, container, false);
        mostrarDialogoSalir();
        return root;
    }

    private void mostrarDialogoSalir() {
        if (getActivity() == null) return;
        new AlertDialog.Builder(getActivity())
                .setTitle("Salir")
                .setMessage("¿Desea cerrar la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> getActivity().finish())
                .setNegativeButton("No", (dialog, which) -> {
                    // navegar atrás si el usuario cancela
                    if (getActivity() != null) getActivity().onBackPressed();
                })
                .setCancelable(false)
                .show();
    }
}

