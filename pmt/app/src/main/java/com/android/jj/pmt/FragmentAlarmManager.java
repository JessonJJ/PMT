package com.android.jj.pmt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JessonJJ on 2018/3/14.
 */

public class FragmentAlarmManager extends Fragment {
    @Override
    public String toString() {
        return super.toString();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_alarmmanager, null);
        return view;
    }
}
