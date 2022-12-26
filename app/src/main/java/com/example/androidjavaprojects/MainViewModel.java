package com.example.androidjavaprojects;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();
    MutableLiveData<String> mLoginResultMutableData = new MutableLiveData<>();
    MainRepository mMainRepository;


    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mLoginResultMutableData.postValue("Not logged in");
        mMainRepository = new MainRepository();
    }

    public void login(String email, String password) {
        mProgressMutableData.postValue(View.VISIBLE);
        mLoginResultMutableData.postValue("Checking");
        mMainRepository.loginRemote(new LoginBody(email, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                Log.i("myloginapi", "---tar---"+loginResponse.getToken());
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Success");
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressMutableData.postValue(View.INVISIBLE);
                Log.i("myloginapi", "----tar--"+t.getLocalizedMessage());
                Log.i("myloginapi", "---tar---"+t.getMessage());
                Log.i("myloginapi", "----tar--"+t.toString());


                mLoginResultMutableData.postValue("Login failure: " + t.getLocalizedMessage() + "-");
            }
        });
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

    public LiveData<String> getLoginResult() {
        return mLoginResultMutableData;
    }

    public LiveData<Integer> getProgress() {
        return mProgressMutableData;
    }

    public LiveData<String> getDrink() {
        return mDrinksMutableData;
    }

}
