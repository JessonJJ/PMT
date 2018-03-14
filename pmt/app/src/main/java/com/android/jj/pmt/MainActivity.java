package com.android.jj.pmt;

import android.content.Context;
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

    //about btn PARTIAL wakelock
    private Button mButtonPARTIALWL;
    //true means get PARTIAL wakelock
    private boolean mbGetPARTIALWL = false;
    private PowerManager.WakeLock mWakeLockPARTIAL = null;

    //about btn FULL wakelock
    private Button mButtonFULLWL;
    private boolean mbGetFULLWL = false;
    private PowerManager.WakeLock mWakeLockFULL = null;

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
        mButtonPARTIALWL = (Button)findViewById(R.id.button_id_PARTIAL_wakelock);
        mButtonFULLWL    = (Button)findViewById(R.id.button_id_FULL_wakelock);
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

    public void button_click_PARTIAL_wakelock(View v) {
        Log.w(TAG, "button_click_PARTIAL_wakelock: mbGetPARTIALWL=" + mbGetPARTIALWL);
        if (mbGetPARTIALWL == false) {
            mbGetPARTIALWL = !mbGetPARTIALWL;
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            mWakeLockPARTIAL = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
            if (mWakeLockPARTIAL != null) {
                mWakeLockPARTIAL.acquire();
                mButtonPARTIALWL.setText("get PARTIAL wl");
            }

        } else {
            mbGetPARTIALWL = !mbGetPARTIALWL;
            release_PARTIAL_wakelock();
            mButtonPARTIALWL.setText("release PARTIAL wl");
        }
    }

    private void release_PARTIAL_wakelock() {
        if (mWakeLockPARTIAL != null) {
            mWakeLockPARTIAL.release();
            mWakeLockPARTIAL = null;
        }
    }

    public void button_click_FULL_wakelock(View v) {
        Log.i(TAG, "button_click_FULL_wakelock: mbGetFULLWL=" + mbGetFULLWL);
        if (mbGetFULLWL == false) {
            mbGetFULLWL = !mbGetFULLWL;
            PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
            mWakeLockFULL = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, TAG);
            if (mWakeLockFULL != null) {
                mWakeLockFULL.acquire();
                mButtonFULLWL.setText("get FULL wakelock");
            }
        } else {
            mbGetFULLWL = !mbGetFULLWL;
            releaseFULL_wakelock();
            mButtonFULLWL.setText("release FULL wakelock");
        }
    }

    private void releaseFULL_wakelock() {
        if (mWakeLockFULL != null) {
            mWakeLockFULL.release();
            mWakeLockFULL = null;
        }
    }
}
