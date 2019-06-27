package com.hooyee.androidxstudy.livedata;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.view.View;

import com.hooyee.androidxstudy.BR;

import java.util.Random;

public class LiveDataViewModel extends BaseObservable {
    private String title;
    private MutableLiveData<String> currentName;

    private PostalCodeRepository repository;
    private final MutableLiveData<String> addressInput = new MutableLiveData();
    public final LiveData<String> postalCode =
            Transformations.switchMap(addressInput, new Function<String, LiveData<String>>() {
                @Override
                public LiveData<String> apply(String address) {
                    return repository.getPostCode(address);
                }
            });

    public LiveDataViewModel(PostalCodeRepository repository) {
        this.repository = repository;
    }

    private void setInput(String address) {
        addressInput.setValue(address);
    }

    public LiveData<String> getPostalCode(String address) {
        // DON'T DO THIS
        return repository.getPostCode(address);
    }


    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void onClick(View view) {
        currentName.setValue("zhangsan" + new Random(System.currentTimeMillis()).nextInt(10));
    }

    public void onClick2() {
        //currentName.setValue("zhangsan"+new Random(System.currentTimeMillis()).nextInt(10));
        setInput("" + new Random(System.currentTimeMillis()).nextInt(10));
    }


}
