package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateAppComponentsViewModel : ViewModel() {

    val components: LiveData<VisualComponents> get() = _components

    private var _components: MutableLiveData<VisualComponents> =
            MutableLiveData<VisualComponents>().also {
                it.value = havCoponent
            }

    var havCoponent: VisualComponents = VisualComponents()
        set(value) {
            field = value
            _components.value = value
        }

}

class VisualComponents(
        val bottomNavigation: Boolean = false
)