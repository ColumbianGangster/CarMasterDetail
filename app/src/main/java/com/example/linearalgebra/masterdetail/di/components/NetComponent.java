package com.example.linearalgebra.masterdetail.di.components;

import android.content.SharedPreferences;

import com.example.linearalgebra.masterdetail.di.AppModule;
import com.example.linearalgebra.masterdetail.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    SharedPreferences sharedPreferences();
}
