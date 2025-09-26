package com.julian.tpo3.ui.producto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.julian.tpo3.R;
import com.julian.tpo3.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos = new ArrayList<>();

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.textViewNombre.setText(producto.getDescripcion()); // Usar descripción como nombre
        holder.textViewPrecio.setText("Precio: $" + producto.getPrecio());
        holder.textViewDescripcion.setText(producto.getCodigo()); // Mostrar código como descripción secundaria
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewPrecio, textViewDescripcion;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreProducto);
            textViewPrecio = itemView.findViewById(R.id.textViewPrecioProducto);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcionProducto);
        }
    }
}
