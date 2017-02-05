package com.exercise.p.k_table.control;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.p.k_table.R;

/**
 * Created by p on 2017/2/3.
 */

public class InfoFragment extends Fragment {
    ActionBar actionBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info,null);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actionBar = ((MainActivity)context).actionBar;
    }
}
