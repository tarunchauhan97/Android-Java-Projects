package com.example.androidjavaprojects;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();
    MainRepository mMainRepository;

    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mMainRepository = new MainRepository();
    }

    public void suggestNewDrink() {
        mProgressMutableData.postValue(View.VISIBLE);
        mMainRepository.suggestNewDrink(new MainRepository.IDrinkCallback() {
            @Override
            public void onDrinkSuggested(String drinkName) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mDrinksMutableData.postValue(drinkName);
            }

            @Override
            public void onErrorOccurred() {
                mProgressMutableData.postValue(View.INVISIBLE);
                //show toast
            }
        });

    }

    public LiveData<Integer> getProgress() {
        return mProgressMutableData;
    }

    public LiveData<String> getDrink() {
        return mDrinksMutableData;
    }

}
