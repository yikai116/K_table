package com.exercise.p.k_table.control;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Global_Info;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by p on 2017/2/3.
 */

public class InfoFragment extends Fragment {
    ActionBar actionBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info,null);
        TextView textView_name = (TextView) view.findViewById(R.id.name);
        textView_name.setText(Global_Info.getUser_name());

        Button button_quit = (Button) view.findViewById(R.id.quitButton);
        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("IsFirstAndIsLogin",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin",false);
                editor.apply();
                Intent intent = new Intent();
                intent.setClass(getActivity(),LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
