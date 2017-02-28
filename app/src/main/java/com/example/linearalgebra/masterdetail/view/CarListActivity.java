package com.example.linearalgebra.masterdetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.linearalgebra.masterdetail.AndroidApplication;
import com.example.linearalgebra.masterdetail.CarListScreen;
import com.example.linearalgebra.masterdetail.IntentCreator;
import com.example.linearalgebra.masterdetail.R;
import com.example.linearalgebra.masterdetail.di.components.DaggerCarListComponent;
import com.example.linearalgebra.masterdetail.di.modules.CarListModule;
import com.example.linearalgebra.masterdetail.model.Car;
import com.example.linearalgebra.masterdetail.model.Cars;
import com.example.linearalgebra.masterdetail.presenter.CarListPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class CarListActivity extends BaseActivity implements CarListScreen.View{

    private boolean mTwoPane = false;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressBar spinner;

    @Inject
    CarListPresenter presenter;

    private int mLastSelected = 0;

    @Override
    public int resId() {
        return R.layout.activity_item_list;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(getTitle());
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        DaggerCarListComponent.builder()
                .netComponent(((AndroidApplication) getApplicationContext()).getNetComponent())
                .carListModule(new CarListModule(this))
                .build().inject(this);
        presenter.loadCars();
    }

    @Override
    protected void onResumeCalled() {

    }

    private MyOnClickListener getListener(Cars cars) {
        return position -> goToDetailScreen(position, cars);
    }

    public Cars createMock2() {
        ArrayList<Car> list = new ArrayList<>();




        list.add(createItem("Koenigsegg CCXR Trevita", "Texas", "http://icdn2.digitaltrends.com/image/4-8-million-koenigsegg-ccxr-trevita-970x647-c.jpg", "$115482","2016-04-10", "366 Dank Court, Sexton, Texas, 1391" ));
        list.add(createItem("Lamborghini Veneno", "Washington", "http://icdn3.digitaltrends.com/image/4-5-million-lamborghini-veneno-970x647-c.jpg", "$259156", "2014-06-13","207 Brighton Court, Sanborn, Washington, 107" ));
        list.add(createItem("W Motors Lykan Hypersport", "Nevada", "http://icdn4.digitaltrends.com/image/3-4-million-w-motors-lykan-hypersport-970x647-c.jpg", "$347591", "2014-05-29", "445 Reed Street, Fairlee, Nevada, 5435"));
        list.add(createItem("Limited Edition Bugatti Veyron by Mansory Vivere", "American Samoa", "http://icdn5.digitaltrends.com/image/3-4-million-limited-edition-bugatti-veyron-by-mansory-vivere_008-970x647-c.jpg","$227410", "2016-11-17", "590 Kansas Place, Garfield, American Samoa, 7785" ));
        list.add(createItem("Ferrari Pininfarina Sergio", "Texas", "http://icdn6.digitaltrends.com/image/01-19-970x647-c.jpg", "$115482","2016-04-10", "366 Dank Court, Sexton, Texas, 1391"));
        list.add(createItem("Pagani Huayra BC", "Washington", "http://icdn7.digitaltrends.com/image/2-6-million-pagani-huayra-bc_0016-970x647-c.jpg", "$259156", "2014-06-13","207 Brighton Court, Sanborn, Washington, 107"));
        list.add(createItem("Ferrari F60 America", "Nevada", "http://icdn8.digitaltrends.com/image/2-5-million-ferrari-f60-america_001-970x647-c.jpg", "$347591", "2014-05-29", "445 Reed Street, Fairlee, Nevada, 5435"));
        list.add(createItem("Bugatti Veyron", "Texas", "http://icdn9.digitaltrends.com/image/2-5-million-bugatti-chiron_003-970x647-c.jpg", "$115482","2016-04-10", "366 Dank Court, Sexton, Texas, 1391"));
        list.add(createItem("Koenigsegg One:1", "Washington", "http://icdn1.digitaltrends.com/image/2-0-million-koenigsegg-one-1_008-970x647-c.jpg", "$259156", "2014-06-13","207 Brighton Court, Sanborn, Washington, 107"));
        list.add(createItem("Koenigsegg Regera", "Nevada", "http://icdn2.digitaltrends.com/image/2-0-million-koenigsegg-regera_0011-970x647-c.jpg", "$347591", "2014-05-29", "445 Reed Street, Fairlee, Nevada, 5435"));
        list.add(createItem("Mercedes-Benz Maybach Exelero", "Texas", "http://icdn3.digitaltrends.com/image/maybach_exelero_img_227-970x647-c.jpg", "$115482","2016-04-10", "366 Dank Court, Sexton, Texas, 1391"));
        list.add(createItem("LaFerrari FXX K", "Washington", "http://icdn4.digitaltrends.com/image/1400443_car-ferrari-fxx-970x647-c.jpg", "$259156", "2014-06-13","207 Brighton Court, Sanborn, Washington, 107"));
        list.add(createItem("Lamborghini Sesto Elemento", "Nevada", "http://icdn5.digitaltrends.com/image/2-2-million-lamborghini-sesto-elemento_002-970x647-c.jpg", "$347591", "2014-05-29", "445 Reed Street, Fairlee, Nevada, 5435"));
        return new Cars().withCars(list);
    }

    @NonNull
    private Car createItem(String title, String location, String photo, String price, String date, String fullLocation) {
        String owner = "Bob";
        String fuelType = "Nitro";
        String phoneNumber = "020202020";
        String message = "mesgsgsgsg";
        return new Car()
                .withTitle(title).withLocation(location).withPhoto(photo)
                .withOwner(owner).withFuelType(fuelType).withFullLocation(fullLocation).withPhoneNumber(phoneNumber)
                .withEmail(message).withPrice(price).withDate(date);
    }

    private int lastSelected() {
        return mLastSelected;
    }

    private void goToDetailScreen(int position, Cars cars) {
        mLastSelected = position;
        startActivity(IntentCreator.newCarDetailActivity(this, position, cars.getCars().get(position)));
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
    public void showCars(Cars cars) {
        spinner.setVisibility(View.GONE);
        setupRecyclerView(cars);
    }

    private void setupRecyclerView(Cars cars) {
        mRecyclerView = (RecyclerView) findViewById(R.id.item_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(CarListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CarListActivityAdapter(cars, CarListActivity.this, CarListActivity.this.getListener(cars));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String message) {
        spinner.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), R.string.error_network_failure, Toast.LENGTH_LONG).show();
        setupRecyclerView(createMock2());
    }

    @Override
    public void showComplete() {

    }

    public interface MyOnClickListener {
        void onClick(int position);
    }
}
