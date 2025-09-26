package com.julian.tpo3.ui.producto;

import static com.julian.tpo3.MainActivity.productos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.julian.tpo3.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductoViewModel extends AndroidViewModel {



    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> productosVaciosLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorVisibleLiveData = new MutableLiveData<>();

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Producto>> getProductosLiveData() {
        return productosLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getProductosVaciosLiveData() {
        return productosVaciosLiveData;
    }

    public LiveData<Boolean> getErrorVisibleLiveData() {
        return errorVisibleLiveData;
    }

    // validaciones
    public void agregarProducto(String codigo, String descripcion, double precio) {
        if (codigo.isEmpty() || descripcion.isEmpty()) {
            setError("No puede haber campos vacios");
            return;
        }
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                setError("El codigo ya existe");
                return;
            }
        }
        // Validar precio
        if (precio <= 0) {
            setError("El precio debe ser mayor a 0");
            return;
        }
        Producto nuevo = new Producto(codigo, descripcion, precio);
        productos.add(nuevo);
        actualizarProductosYEstadoVacio(new ArrayList<>(productos));
        setError(""); // Limpiar
    }
    // actualizar lista
    public void actualizarLista() {
        actualizarProductosYEstadoVacio(new ArrayList<>(productos));
    }

    public void setError(String error) {
        errorLiveData.setValue(error);
        errorVisibleLiveData.setValue(error != null && !error.isEmpty());
    }

    // listar productos ordenados
    public void listarProductosPorDescripcion() {
        List<Producto> productosOrdenados = new ArrayList<>(productos);
        Collections.sort(productosOrdenados, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
            }
        });
        actualizarProductosYEstadoVacio(productosOrdenados);
    }

    private void actualizarProductosYEstadoVacio(List<Producto> lista) {
        productosLiveData.setValue(lista);
        productosVaciosLiveData.setValue(lista == null || lista.isEmpty());
    }

    public double parsePrecio(String precioStr) {
        try {
            return Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
