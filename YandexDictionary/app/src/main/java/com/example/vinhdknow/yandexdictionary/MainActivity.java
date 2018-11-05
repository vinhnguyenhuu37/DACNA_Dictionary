package com.example.vinhdknow.yandexdictionary;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.vinhdknow.yandexdictionary.Fragment.Fragment_History;
import com.example.vinhdknow.yandexdictionary.Fragment.Fragment_Settings;
import com.example.vinhdknow.yandexdictionary.Fragment.Fragment_Translate;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView botNAV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Radiation();
        ButtonControl();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.item_tranlate:
                    selectedFragment =new Fragment_Translate();
                    break;
                case R.id.item_history:
                    selectedFragment=new Fragment_History();
                    break;
                case R.id.item_settings:
                    selectedFragment=new Fragment_Settings();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    private void Radiation() {
        botNAV =findViewById(R.id.bottom_navigation);
    }
    private void ButtonControl() {
        botNAV.setOnNavigationItemSelectedListener(navListener);
    }
}
