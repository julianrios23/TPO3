package com.julian.tpo3.ui.producto;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import com.julian.tpo3.model.Producto;
import static com.julian.tpo3.MainActivity.productos;
import java.util.ArrayList;

public class CargarProductoViewModel extends AndroidViewModel {
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> productoAgregadoLiveData = new MutableLiveData<>();

    public CargarProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getProductoAgregadoLiveData() {
        return productoAgregadoLiveData;
    }

    public void setError(String error) {
        if (error != null && !error.isEmpty()) {
            errorLiveData.setValue(error);
        }
    }

    public void agregarProducto(String codigo, String descripcion, double precio) {
        boolean faltaCampo = codigo.isEmpty() || descripcion.isEmpty();
        if (faltaCampo) {
            setError("No puede haber campos vacios");
            productoAgregadoLiveData.setValue(false);
            return;
        }
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                setError("El codigo ya existe");
                productoAgregadoLiveData.setValue(false);
                return;
            }
        }
        if (precio <= 0) {
            setError("El precio debe ser mayor a 0");
            productoAgregadoLiveData.setValue(false);
            return;
        }
        Producto nuevo = new Producto(codigo, descripcion, precio);
        productos.add(nuevo);
        productoAgregadoLiveData.setValue(true);
        // No emitir error si todo salió bien
    }
}
