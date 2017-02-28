package com.example.linearalgebra.masterdetail.presenter;


import com.example.linearalgebra.masterdetail.CarListScreen;
import com.example.linearalgebra.masterdetail.model.Cars;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CarListPresenter implements CarListScreen.Presenter {
    Retrofit retrofit;
    CarListScreen.View mView;

    @Inject
    public CarListPresenter(Retrofit retrofit, CarListScreen.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void loadCars() {
        retrofit.create(CarService.class).getCarList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Cars>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Cars cars) {
                        mView.showCars(cars);
                    }
                });
    }

    private interface CarService {
        @GET("/cars")
        Observable<Cars> getCarList();
    }
}
