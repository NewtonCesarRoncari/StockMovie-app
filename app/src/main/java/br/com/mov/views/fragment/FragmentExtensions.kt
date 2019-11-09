package br.com.mov.views.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showMessageError(mensagem: String) {
    Toast.makeText(
        context,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}