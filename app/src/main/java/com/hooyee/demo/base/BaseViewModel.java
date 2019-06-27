package com.hooyee.demo.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hooyee.demo.BR;

public class BaseViewModel extends BaseObservable {
    String title;

    public BaseViewModel(String title){
        setTitle(title);
    }
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
