package com.example.androidjavaprojects;

import android.view.View;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {

    String[] drinksListRemote = {"Mint Margarita", "Spiking coffee", "Sweet Bananas",
            "Tomato Tang", "Apple Berry Smoothie",
            "Coding Reel Coffee"
    };

    public MainRepository() {

    }

    private void suggestNewDrink() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //Before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                try {
                    Thread.sleep(1000); // Mimic server request / long execution
                    String drinkName = drinksListRemote[new Random().nextInt(drinksListRemote.length)];


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
