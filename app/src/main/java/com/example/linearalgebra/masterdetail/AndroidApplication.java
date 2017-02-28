package com.example.linearalgebra.masterdetail;

import android.app.Application;

import com.example.linearalgebra.masterdetail.di.AppModule;
import com.example.linearalgebra.masterdetail.di.components.DaggerNetComponent;
import com.example.linearalgebra.masterdetail.di.components.NetComponent;
import com.example.linearalgebra.masterdetail.di.modules.NetModule;
import com.example.linearalgebra.masterdetail.networking.Endpoints;
import com.squareup.leakcanary.LeakCanary;


public class AndroidApplication extends Application {

    private NetComponent mNetComponent;

    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Endpoints.CARS))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    private void initializeLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
