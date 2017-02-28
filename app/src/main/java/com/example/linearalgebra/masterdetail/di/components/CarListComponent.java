package com.example.linearalgebra.masterdetail.di.components;

import com.example.linearalgebra.masterdetail.di.modules.CarListModule;
import com.example.linearalgebra.masterdetail.di.scopes.CustomScope;
import com.example.linearalgebra.masterdetail.view.CarListActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = NetComponent.class, modules = CarListModule.class)
public interface CarListComponent {
    void inject(CarListActivity carListActivity);
}
