package com.exercise.p.k_table.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.CET;
import com.exercise.p.k_table.model.Exam;

import java.util.ArrayList;

/**
 * Created by p on 2017/2/11.
 */

public class Query_CETFragment extends Fragment {
    ExpandableListView listView;
    ArrayList<Exam> exams;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cet_query,null);

        String stu_name = (String) getActivity().getIntent().getBundleExtra("info").getSerializable("name");
        TextView textView = (TextView) view.findViewById(R.id.cet_query_stu_name);
        textView.setText(stu_name);
        CET cet = (CET) getActivity().getIntent().getBundleExtra("info").getSerializable("value");
        textView = (TextView) view.findViewById(R.id.cet_query_all);
        textView.setText("总分：" + cet.getAll());
        textView = (TextView) view.findViewById(R.id.cet_query_listen);
        textView.setText("听力：" + cet.getListen());
        textView = (TextView) view.findViewById(R.id.cet_query_read);
        textView.setText("阅读：" + cet.getRead());
        textView = (TextView) view.findViewById(R.id.cet_query_write);
        textView.setText("写作和翻译：" + cet.getWrite());
        return view;
    }
}
