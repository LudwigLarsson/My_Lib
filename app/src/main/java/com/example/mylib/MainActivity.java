package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mylib.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainFragment mainFragment = new MainFragment();
    WishFragment wishFragment = new WishFragment();
    UserFragment userFragment = new UserFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initFragments();
        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, BookActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void initFragments(){
        getSupportFragmentManager().beginTransaction().
                replace(binding.main.getId(), mainFragment).
                replace(binding.wish.getId(), wishFragment).
                replace(binding.user.getId(), userFragment).
                replace(binding.statistics.getId(), statisticsFragment).
                commit();
        makeVisible(binding.main);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.main:
                    makeVisible(binding.main);
                    return true;
                case R.id.wish:
                    makeVisible(binding.wish);
                    return true;
                case R.id.user:
                    makeVisible(binding.user);
                    return true;
                case R.id.statistics:
                    makeVisible(binding.statistics);
                    return true;
            }
            return false;
        });
    }

    private void makeVisible(FrameLayout id){
        binding.main.setVisibility(View.GONE);
        binding.wish.setVisibility(View.GONE);
        binding.user.setVisibility(View.GONE);
        binding.statistics.setVisibility(View.GONE);
        binding.user.setVisibility(View.GONE);
        id.setVisibility(View.VISIBLE);
    }
}