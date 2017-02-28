package com.example.linearalgebra.masterdetail;

import android.content.Context;
import android.content.Intent;

import com.example.linearalgebra.masterdetail.model.Car;
import com.example.linearalgebra.masterdetail.view.CarDetailActivity;

public class IntentCreator {
    public static final String ARG_ITEM_POSITION = "CarDetailFragment.ARG_ITEM_POSITION";
    public static final String ARG_MODEL_ITEM = "CarDetailFragment.ARG_MODEL_ITEM";

    public static Intent newCarDetailActivity(Context context, int position, Car car) {
        Intent intent = new Intent(context, CarDetailActivity.class);
        intent.putExtra(ARG_ITEM_POSITION, position);
        intent.putExtra(ARG_MODEL_ITEM, car);
        return intent;
    }
}
