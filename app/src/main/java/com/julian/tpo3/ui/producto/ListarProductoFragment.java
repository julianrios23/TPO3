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
import com.julian.tpo3.model.Producto;
import java.util.List;

public class ListarProductoFragment extends Fragment {
    private ProductoViewModel productoViewModel;
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private TextView textViewVacio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_producto, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewProductos);
        textViewVacio = view.findViewById(R.id.textViewVacio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productoAdapter = new ProductoAdapter();
        recyclerView.setAdapter(productoAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productoViewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);
        productoViewModel.getProductosLiveData().observe(getViewLifecycleOwner(), productos -> {
            productoAdapter.setProductos(productos);
            if (productos == null || productos.isEmpty()) {
                textViewVacio.setVisibility(View.VISIBLE);
            } else {
                textViewVacio.setVisibility(View.GONE);
            }
        });
        // Mostrar la lista ordenada alfabéticamente por descripción
        productoViewModel.listarProductosPorDescripcion();
    }

    // Adaptador interno para el RecyclerView
    private static class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
        private List<Producto> productos;

        public void setProductos(List<Producto> productos) {
            this.productos = productos;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
            Producto producto = productos.get(position);
            holder.bind(producto);
        }

        @Override
        public int getItemCount() {
            return productos == null ? 0 : productos.size();
        }

        static class ProductoViewHolder extends RecyclerView.ViewHolder {
            private final TextView text1;
            private final TextView text2;

            public ProductoViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
                text2 = itemView.findViewById(android.R.id.text2);
            }

            public void bind(Producto producto) {
                text1.setText(producto.getDescripcion());
                text2.setText("Código: " + producto.getCodigo() + " | Precio: $" + producto.getPrecio());
            }
        }
    }
}
