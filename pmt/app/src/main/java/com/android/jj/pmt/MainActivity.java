package com.android.jj.pmt;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "pmt";

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());

        mButtonPARTIALWL = (Button)findViewById(R.id.button_id_PARTIAL_wakelock);
        mButtonFULLWL    = (Button)findViewById(R.id.button_id_FULL_wakelock);
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
