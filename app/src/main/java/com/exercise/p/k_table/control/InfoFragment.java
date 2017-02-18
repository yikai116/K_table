package com.exercise.p.k_table.control;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    Button button_quit;
    Button setting;
    Button share;
    Button feedback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info,null);
        initView(view);
        return view;
    }

    public void initView(View view){
        TextView textView_name = (TextView) view.findViewById(R.id.name);
        textView_name.setText(Global_Info.getUser_name());

        setting = (Button) view.findViewById(R.id.setting);
        share = (Button) view.findViewById(R.id.share);
        feedback = (Button) view.findViewById(R.id.feedback);
        button_quit = (Button) view.findViewById(R.id.quitButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send = new Intent();
                intent_send.setAction(Intent.ACTION_SEND);
                intent_send.putExtra(Intent.EXTRA_TEXT,"【K_table】简单实用的自用课程表，O(∩_∩)O哈哈~");
                intent_send.setType("text/plain");
                startActivity(Intent.createChooser(intent_send,"选择分享"));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_feedback=new Intent(Intent.ACTION_SENDTO);
                intent_feedback.setData(Uri.parse("mailto:yyykkk@qq.com"));
                intent_feedback.putExtra(Intent.EXTRA_SUBJECT, "K_table反馈");
                intent_feedback.putExtra(Intent.EXTRA_TEXT, "反馈：");
                startActivity(intent_feedback);
            }
        });
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

    }

}
