package net.altunyay.myunittestapp.utils

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("visibility")
fun View.setVisibility(visibility: Boolean?) {
    isVisible = visibility ?: false
}

@BindingAdapter("errorText")
fun TextInputLayout.setErrorText(@StringRes error: Int?) {
    this.error = error?.let {
        context.getString(error)
    }
}

@BindingAdapter("onEditorActionDoneListener")
fun EditText.setOnEditorActionListener(listener: View.OnClickListener?) {
    setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE ->
                listener?.onClick(this)
        }
        false
    }
}