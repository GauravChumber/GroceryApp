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

public class MainActivity extends AppCompatActivity {

    ImageView burgerIcon;
    DrawerLayout drawer;

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
    }
    @Override
    public void onBackPressed() {
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
            adb.setNegativeButton("No",null);
            AlertDialog alertDialog=adb.create();
            alertDialog.show();
        }
    }
}