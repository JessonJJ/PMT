package com.android.jj.pmt;

import android.content.Context;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by JessonJJ on 2018/3/14.
 */

public class FragmentPowerManager extends Fragment {
    private static final String TAG = "pmt+power";

    //about btn PARTIAL wakelock
    private Button mButtonPARTIALWL;
    //true means get PARTIAL wakelock
    private boolean mbGetPARTIALWL = false;
    private PowerManager.WakeLock mWakeLockPARTIAL = null;

    //about btn FULL wakelock
    private Button mButtonFULLWL;
    private boolean mbGetFULLWL = false;
    private PowerManager.WakeLock mWakeLockFULL = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_powermanager, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButtonPARTIALWL = (Button)getActivity().findViewById(R.id.button_id_PARTIAL_wakelock);
        mButtonFULLWL = (Button)getActivity().findViewById(R.id.button_id_FULL_wakelock);

        mButtonPARTIALWL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                button_click_PARTIAL_wakelock(view);
            }
        });

        mButtonFULLWL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                button_click_FULL_wakelock(view);
            }
        });
    }

    public void button_click_PARTIAL_wakelock(View v) {
        Log.w(TAG, "button_click_PARTIAL_wakelock: mbGetPARTIALWL=" + mbGetPARTIALWL);
        if (mbGetPARTIALWL == false) {
            mbGetPARTIALWL = !mbGetPARTIALWL;
            MainActivity mainActivity = (MainActivity)getActivity();
            PowerManager pm = (PowerManager)mainActivity.getSystemService(Context.POWER_SERVICE);
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
            MainActivity mainActivity = (MainActivity)getActivity();
            PowerManager pm = (PowerManager)mainActivity.getSystemService(Context.POWER_SERVICE);
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
