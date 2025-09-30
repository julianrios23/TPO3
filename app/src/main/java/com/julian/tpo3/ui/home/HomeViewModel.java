package com.julian.tpo3.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("TPO 3\nDesarrollo Aplicaciones Móviles");
    }

    public LiveData<String> getText() {
        return mText;
    }
}