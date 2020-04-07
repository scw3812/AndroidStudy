package com.example.part6_18;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Lab18_2Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    ViewPager viewPager;
    FloatingActionButton fab;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab18_2);

        relativeLayout = (RelativeLayout)findViewById(R.id.lab2_container);
        viewPager = (ViewPager)findViewById(R.id.lab2_viewpager);
        fab = (FloatingActionButton)findViewById(R.id.lab2_fab);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.lab2_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        fab.setOnClickListener(this);
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments = new ArrayList<Fragment>();
        private String titles[] = new String[]{"TAB1", "TAB2", "TAB3"};

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            fragments.add(new OneFragment());
            fragments.add(new TwoFragment());
            fragments.add(new ThreeFragment());
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
    @Override
    public void onClick(View view) {
        Snackbar.make(relativeLayout, "I am SnackBar", Snackbar.LENGTH_LONG).setAction("MoreAction", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
