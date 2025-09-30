package com.julian.tpo3.ui.producto;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import com.julian.tpo3.model.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.julian.tpo3.MainActivity.productos;

public class ListarProductoViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> productosVaciosLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorVisibleLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorParaMostrarLiveData = new MutableLiveData<>();

    public ListarProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Producto>> getProductosLiveData() {
        return productosLiveData;
    }

    public LiveData<Boolean> getProductosVaciosLiveData() {
        return productosVaciosLiveData;
    }

    public LiveData<Boolean> getErrorVisibleLiveData() {
        return errorVisibleLiveData;
    }

    public LiveData<String> getErrorParaMostrarLiveData() {
        return errorParaMostrarLiveData;
    }

    public void actualizarLista() {
        actualizarProductosYEstadoVacio(new ArrayList<>(productos));
    }

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
        productosVaciosLiveData.setValue(lista.isEmpty());
    }

    public void setError(String error) {
        errorVisibleLiveData.setValue(error != null && !error.isEmpty());
        if (error != null && !error.isEmpty()) {
            errorParaMostrarLiveData.setValue(error);
        }
    }

    // Aquí irá la lógica de listar productos y mostrar errores/lista vacía
}
