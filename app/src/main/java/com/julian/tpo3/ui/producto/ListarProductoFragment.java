package com.julian.tpo3.ui.producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.julian.tpo3.R;
import com.julian.tpo3.databinding.FragmentListarProductoBinding;
import com.julian.tpo3.model.Producto;
import java.util.List;

public class ListarProductoFragment extends Fragment {
    private ProductoViewModel productoViewModel;
    private RecyclerView recyclerView;
    private TextView textViewVacio;
    private FragmentListarProductoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListarProductoBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerViewProductos;
        textViewVacio = binding.textViewVacio;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //usar solo la clase externa ProductoAdapter
        recyclerView.setAdapter(new ProductoAdapter());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productoViewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);
        productoViewModel.getProductosLiveData().observe(getViewLifecycleOwner(), productos -> {
            //actualizar lista
            ((ProductoAdapter)recyclerView.getAdapter()).setProductos(productos);
        });
        productoViewModel.getProductosVaciosLiveData().observe(getViewLifecycleOwner(), vacio -> {
            textViewVacio.setVisibility(vacio ? View.VISIBLE : View.GONE);
        });
        // lista ordenada
        productoViewModel.listarProductosPorDescripcion();
    }
}
