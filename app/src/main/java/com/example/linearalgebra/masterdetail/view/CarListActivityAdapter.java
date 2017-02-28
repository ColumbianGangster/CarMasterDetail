package com.example.linearalgebra.masterdetail.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.linearalgebra.masterdetail.R;
import com.example.linearalgebra.masterdetail.model.Cars;

import butterknife.ButterKnife;

public class CarListActivityAdapter extends RecyclerView.Adapter<CarListActivityAdapter.ViewHolder> {
    private Cars mCars;
    private Context mContext;
    private CarListActivity.MyOnClickListener  mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle;
        public TextView mLocation;
        public TextView mPrice;
        public TextView mDate;
        public ImageView mPhoto;
        CarListActivity.MyOnClickListener mListener;

        public ViewHolder(View view, CarListActivity.MyOnClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            mTitle = ButterKnife.findById(view, R.id.title);
            mLocation = ButterKnife.findById(view, R.id.location);
            mPhoto = ButterKnife.findById(view, R.id.photo);
            mPrice = ButterKnife.findById(view, R.id.price);
            mDate = ButterKnife.findById(view, R.id.date);
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }
    }

    public CarListActivityAdapter(Cars cars, Context context, CarListActivity.MyOnClickListener listener) {
        mCars = cars;
        mContext = context;
        mListener = listener;
    }

    @Override
    public CarListActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mCars.getCars().get(position).getTitle());
        holder.mLocation.setText(mCars.getCars().get(position).getLocation());
        holder.mPrice.setText(mCars.getCars().get(position).getPrice());
        holder.mDate.setText(mCars.getCars().get(position).getDate());
        Glide.with(mContext).load(mCars.getCars().get(position).getPhoto()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mCars.getCars().size();
    }
}
