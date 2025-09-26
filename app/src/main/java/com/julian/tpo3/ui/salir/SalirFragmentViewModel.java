package com.julian.tpo3.ui.salir;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirFragmentViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> mostrarDialogo = new MutableLiveData<>();
    private final MutableLiveData<Boolean> cerrarApp = new MutableLiveData<>();
    private final MutableLiveData<Boolean> navegarAtras = new MutableLiveData<>();

    public SalirFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getMostrarDialogo() {
        return mostrarDialogo;
    }
    public LiveData<Boolean> getCerrarApp() {
        return cerrarApp;
    }
    public LiveData<Boolean> getNavegarAtras() {
        return navegarAtras;
    }

    public void solicitarSalir() {
        mostrarDialogo.setValue(true);
    }
    public void confirmarSalir() {
        cerrarApp.setValue(true);
    }
    public void cancelarSalir() {
        navegarAtras.setValue(true);
    }
}

