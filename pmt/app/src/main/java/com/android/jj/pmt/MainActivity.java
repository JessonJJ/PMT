package com.android.jj.pmt;

import android.content.Context;
import android.graphics.Color;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "pmt";

    private ViewPager mviewPager;
    private List<Fragment> mlistFragment;
    private TabFragmentPagerAdapter mtabFragmentPagerAdapter;

    private TextView tv_item_power;
    private TextView tv_item_alarm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
    //TextView tv = (TextView) findViewById(R.id.power_manager);
    //tv.setText(stringFromJNI());
        tv_item_power = (TextView)findViewById(R.id.main_activity_item_power_manager);
        tv_item_alarm = (TextView)findViewById(R.id.main_activity_item_alarm_manager);
        mviewPager = (ViewPager)findViewById(R.id.main_activity_viewpager);
        mviewPager.setOnPageChangeListener(new MainActivityPageChangeListen());
        mlistFragment = new ArrayList<>();
        mlistFragment.add(new FragmentPowerManager());
        mlistFragment.add(new FragmentAlarmManager());
        mtabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mlistFragment);
        mviewPager.setAdapter(mtabFragmentPagerAdapter);
        mviewPager.setCurrentItem(0);
        Log.i(TAG, "onCreate: end");
    }

    public class MainActivityPageChangeListen implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0 :
                    tv_item_power.setBackgroundColor(Color.RED);
                    tv_item_alarm.setBackgroundColor(Color.WHITE);
                    break;
                case 1 :
                    tv_item_power.setBackgroundColor(Color.WHITE);
                    tv_item_alarm.setBackgroundColor(Color.RED);
                    break;
                default:
                    Log.e(TAG, "onPageSelected: position=" + position);
                    tv_item_power.setBackgroundColor(Color.RED);
                    tv_item_alarm.setBackgroundColor(Color.WHITE);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public void main_activity_item_tv_click_power_manager(View v) {
        Log.i(TAG, "main_activity_item_tv_click_power_manager: ");
        mviewPager.setCurrentItem(0);
    }

    public void main_activity_item_tv_click_alarm_manager(View v) {
        Log.i(TAG, "main_activity_item_tv_click_alarm_manager: ");
        mviewPager.setCurrentItem(1);
    }


}
