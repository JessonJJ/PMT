package com.android.jj.pmt;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by JessonJJ on 2018/3/14.
 */

public class FragmentAlarmManager extends Fragment {
    private static final String TAG = "pmt+alarm";

    private Button mbtnAlarmManager;

    //alarm fun set
    private Button mbtnSetAlarm;
    private EditText meditSetAlarm;

    @Override
    public String toString() {
        return super.toString();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_alarmmanager, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        meditSetAlarm = (EditText)getActivity().findViewById(R.id.edittext_set_alarm);
        mbtnAlarmManager = (Button)getActivity().findViewById(R.id.fragment_alarm_manager_btn);
        mbtnAlarmManager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mbtnSetAlarm = (Button)getActivity().findViewById(R.id.btn_set_alarm);
        mbtnSetAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager am = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                //am.set(AlarmManager.);
            }
        });
    }


}
