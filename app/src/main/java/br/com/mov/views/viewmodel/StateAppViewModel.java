package br.com.mov.views.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StateAppViewModel extends ViewModel {

    private LiveData<Boolean> appBar;
    private MutableLiveData<Boolean> _ppBar = new MutableLiveData<>();

    public StateAppViewModel() {
        this._ppBar.setValue(false);
    }

    public LiveData<Boolean> getAppBar() {
        return appBar = _ppBar;
    }

    public void changeAppBar(Boolean b) {
        _ppBar.setValue(b);
        getAppBar();
    }
}
