package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.OfferAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Model.OfferModel;
import com.example.myapplication.Model.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView burgerIcon;
    DrawerLayout drawer;
    RecyclerView rvOffers;
    private List<OfferModel> offerModelList = new ArrayList<>();
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        burgerIcon = findViewById(R.id.iv_burgerIcon);
        burgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            drawer.openDrawer(Gravity.LEFT);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        offerModelList.add(new OfferModel(R.drawable.veg2,"$20 cashback", "Get 10% off on order above $50"));
        offerModelList.add(new OfferModel(R.drawable.vegetable3,"$20 cashback", "Get 10% off on order above $50"));
        offerModelList.add(new OfferModel(R.drawable.vegetables_all,"$20 cashback", "Get 10% off on order above $50"));
        offerModelList.add(new OfferModel(R.drawable.vegetable4,"$2 cashback", "buy 3 get cashback"));

        rvOffers= findViewById(R.id.rv_offers);
        OfferAdapter offerAdapter=new OfferAdapter(this,offerModelList);
        rvOffers.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this,1,RecyclerView.HORIZONTAL,false);
        rvOffers.setLayoutManager(manager);
        rvOffers.setItemAnimator(new DefaultItemAnimator());
        rvOffers.setAdapter(offerAdapter);

        SliderModel[] sliderModels = new SliderModel[]{
                new SliderModel(R.drawable.combo5),
                new SliderModel(R.drawable.vegtables2_combo),
                new SliderModel(R.drawable.vegtables3_combo),
                new SliderModel(R.drawable.vegtables4_combo),
                new SliderModel(R.drawable.fruitscombo),
        };
        sliderView=findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(sliderModels,this);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(Gravity.LEFT);
        } else {

            //ALERT DIALOG WHEN EXIT THE APP

            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Confirm Exit?");
            adb.setMessage("are you sure you want to exit?");
            adb.setCancelable(false);

            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    finish();
                }
            });
            adb.setNegativeButton("No", null);
            AlertDialog alertDialog = adb.create();
            alertDialog.show();
        }
    }
}