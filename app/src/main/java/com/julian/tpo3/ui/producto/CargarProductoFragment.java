package com.julian.tpo3.ui.producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.julian.tpo3.databinding.FragmentCargarProductoBinding;

public class CargarProductoFragment extends Fragment {
    private ProductoViewModel productoViewModel;
    private FragmentCargarProductoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarProductoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    /// ytrabajar binding

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productoViewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        productoViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), error -> {
            binding.textViewError.setText(error);
            binding.textViewError.setVisibility(error != null && !error.isEmpty() ? View.VISIBLE : View.GONE);
        });

        binding.editTextCodigo.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        binding.editTextPrecio.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        binding.btnAgregarProducto.setOnClickListener(v -> {
            String codigo = binding.editTextCodigo.getText().toString().trim();
            String descripcion = binding.editTextDescripcion.getText().toString().trim();
            String precioStr = binding.editTextPrecio.getText().toString().trim();
            double precio = precioStr.isEmpty() ? 0 : parsePrecio(precioStr);
            productoViewModel.agregarProducto(codigo, descripcion, precio);
            limpiarFormulario();
        });
    }

    private double parsePrecio(String precioStr) {
        try {
            return Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            productoViewModel.setError("Precio NO Valido");
            return 0;
        }
    }

    private void limpiarFormulario() {
        binding.editTextCodigo.setText("");
        binding.editTextDescripcion.setText("");
        binding.editTextPrecio.setText("");
    }
}
