package com.example.linearalgebra.masterdetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.linearalgebra.masterdetail.IntentCreator;
import com.example.linearalgebra.masterdetail.R;
import com.example.linearalgebra.masterdetail.model.Car;

public class CarDetailActivity extends AppCompatActivity implements CarDetailActivityListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ImageView toolbarImage = (ImageView) findViewById(R.id.photo);
        Car car = getIntent().getParcelableExtra(IntentCreator.ARG_MODEL_ITEM);
        Glide.with(this).load(car.getPhoto()).into(toolbarImage);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            CarDetailFragment fragment = new CarDetailFragment();
            fragment.setArguments(createBundle());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @NonNull
    private Bundle createBundle() {
        Bundle arguments = new Bundle();
        arguments.putInt(IntentCreator.ARG_ITEM_POSITION,
                getIntent().getIntExtra(IntentCreator.ARG_ITEM_POSITION, 0));
        arguments.putParcelable(IntentCreator.ARG_MODEL_ITEM, getIntent().getParcelableExtra(IntentCreator.ARG_MODEL_ITEM));
        return arguments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, CarListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void call() {
        Toast.makeText(getApplicationContext(), R.string.placeholder_call_button_pressed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void sms() {
        Toast.makeText(getApplicationContext(), R.string.placeholder_sms_button_pressed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void message() {
        Toast.makeText(getApplicationContext(), R.string.placeholder_message_button_pressed, Toast.LENGTH_LONG).show();
    }
}
