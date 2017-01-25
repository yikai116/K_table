package com.exercise.p.k_table.control;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exercise.p.k_table.R;

/**
 * Created by p on 2017/1/25.
 */

public class MainFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, null);
//        ImageView linearLayout = (ImageView) view.findViewById(R.id.content_week3).findViewById(R.id.ttttt);
//        if (linearLayout==null)
//            Log.i("Main","null");
//        linearLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.main_color)));
        setHasOptionsMenu(true);
        return view;
    }
}
