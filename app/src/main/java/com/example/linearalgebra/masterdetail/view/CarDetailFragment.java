package com.example.linearalgebra.masterdetail.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linearalgebra.masterdetail.IntentCreator;
import com.example.linearalgebra.masterdetail.R;
import com.example.linearalgebra.masterdetail.model.Car;

import butterknife.ButterKnife;

import static com.example.linearalgebra.masterdetail.R.id.date;


public class CarDetailFragment extends Fragment {

    public Car mCar;
    public TextView mTitle, mLocation, mPrice, mDate, mFuelType, mOwner, mPostingFor;

    public CarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(IntentCreator.ARG_ITEM_POSITION) && getArguments().containsKey(IntentCreator.ARG_MODEL_ITEM)) {
            mCar = getArguments().getParcelable(IntentCreator.ARG_MODEL_ITEM);
        } else {
            throw new IllegalStateException(getString(R.string.error_item_detail_fragment_should_have_item));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (mCar != null) {
            mTitle = ButterKnife.findById(rootView, R.id.title);
            mTitle.setText(mCar.getTitle());

            mLocation = ButterKnife.findById(rootView, R.id.location_text);
            mLocation.setText(mCar.getFullLocation());

            mPrice = ButterKnife.findById(rootView, R.id.price);
            mPrice.setText(mCar.getPrice());

            mDate = ButterKnife.findById(rootView, date);
            mDate.setText(mCar.getDate());

            mFuelType = ButterKnife.findById(rootView, R.id.fuel_type_value);
            mFuelType.setText(mCar.getFuelType());

            mOwner = ButterKnife.findById(rootView, R.id.owner);
            mOwner.setText(mCar.getOwner());

            mPostingFor = ButterKnife.findById(rootView, R.id.posting_for);
            mPostingFor.setText(mCar.getDate());

            CarDetailActivityListener listener = (CarDetailActivityListener) getActivity();
            ButterKnife.findById(rootView, R.id.button_call).setOnClickListener(v -> listener.call());
            ButterKnife.findById(rootView, R.id.button_sms).setOnClickListener(v -> listener.sms());
            ButterKnife.findById(rootView, R.id.button_message).setOnClickListener(v -> listener.message());
        }
        return rootView;
    }
}
