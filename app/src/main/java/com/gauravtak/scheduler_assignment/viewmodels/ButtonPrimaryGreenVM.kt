package com.gauravtak.scheduler_assignment.viewmodels

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.gauravtak.scheduler_assignment.utils_classes.SingleLiveEvent

class ButtonPrimaryGreenVM : BaseObservable() {
    var btnTextValue = ObservableField<String>()
    var btnClick: MutableLiveData<String>? = null

    val btnClickEvent: SingleLiveEvent<Void>? = SingleLiveEvent()
    fun init(btnTextValue: String?) {
        this.btnTextValue.set(btnTextValue);
    }

    fun performClick(view: View) {
        btnClick?.value = (view as AppCompatButton).text.toString();
        btnClickEvent?.call()
    }

}