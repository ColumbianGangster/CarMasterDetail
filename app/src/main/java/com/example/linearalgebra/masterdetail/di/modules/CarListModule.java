package com.example.linearalgebra.masterdetail.di.modules;

import com.example.linearalgebra.masterdetail.CarListScreen;
import com.example.linearalgebra.masterdetail.di.scopes.CustomScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CarListModule {
    private final CarListScreen.View mView;

    public CarListModule(CarListScreen.View view) {
        mView = view;
    }

    @Provides
    @CustomScope
    CarListScreen.View providesMainScreenContractView() {
        return mView;
    }
}
