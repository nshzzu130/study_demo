package com.hooyee.androidxstudy.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.BaseObservable;

import java.util.Random;

public class PostalCodeRepository extends BaseObservable {
    private MutableLiveData<String> postCode = new MutableLiveData<>();

    public LiveData<String> getPostCode(String address) {
        postCode.postValue("address="+ address+",postcode="+new Random(System.currentTimeMillis()).nextInt(10000));
        return postCode;
    }
}
