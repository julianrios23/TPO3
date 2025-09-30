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
    private CargarProductoViewModel cargarProductoViewModel;
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
        cargarProductoViewModel = new ViewModelProvider(requireActivity()).get(CargarProductoViewModel.class);
        cargarProductoViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), error -> {
            android.widget.Toast.makeText(requireContext(), error, android.widget.Toast.LENGTH_LONG).show();
        });
        cargarProductoViewModel.getProductoAgregadoLiveData().observe(getViewLifecycleOwner(), agregado -> {
            if (Boolean.TRUE.equals(agregado)) {
                android.widget.Toast.makeText(requireContext(), "Producto agregado con éxito", android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        binding.editTextCodigo.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        binding.editTextPrecio.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        binding.btnAgregarProducto.setOnClickListener(v -> {
            String codigo = binding.editTextCodigo.getText().toString().trim();
            String descripcion = binding.editTextDescripcion.getText().toString().trim();
            String precioStr = binding.editTextPrecio.getText().toString().trim();
            double precio = 0;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                // Si el campo está vacío o no es válido, precio queda en 0
            }
            cargarProductoViewModel.agregarProducto(codigo, descripcion, precio);
            limpiarFormulario();
        });
    }
    //hacer logica en view model

    private void limpiarFormulario() {
        binding.editTextCodigo.setText("");
        binding.editTextDescripcion.setText("");
        binding.editTextPrecio.setText("");
    }
}
