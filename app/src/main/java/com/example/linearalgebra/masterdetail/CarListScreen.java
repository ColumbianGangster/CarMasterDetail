package com.example.linearalgebra.masterdetail;

import com.example.linearalgebra.masterdetail.model.Cars;

public interface CarListScreen {
    interface View {
        void showCars(Cars cars);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadCars();
    }
}
