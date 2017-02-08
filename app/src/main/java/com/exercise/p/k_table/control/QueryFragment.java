package com.exercise.p.k_table.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;
import com.exercise.p.k_table.model.GradeLoginModel;
import com.exercise.p.k_table.model.MyLoginListener;

import java.util.ArrayList;

/**
 * Created by p on 2017/2/3.
 */

public class QueryFragment extends Fragment implements MyLoginListener{
    TextView cet;
    TextView grade;
    TextView exam;
    EditText editText_id;
    EditText editText_psw;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_query,null);
        cet = (TextView) view.findViewById(R.id.query_cet);
        grade = (TextView) view.findViewById(R.id.query_cj);
        exam = (TextView) view.findViewById(R.id.query_exam);
        editText_id = (EditText) view.findViewById(R.id.query_idEdit);
        editText_psw = (EditText) view.findViewById(R.id.query_pswEdit);
        QueryListener listener_temp = new QueryListener();
        cet.setOnClickListener(listener_temp);
        grade.setOnClickListener(listener_temp);
        exam.setOnClickListener(listener_temp);
        return view;
    }

    public class QueryListener implements View.OnClickListener
    {

        @Override
        public void onClick (View v){
            switch (v.getId()) {
            case R.id.query_cet:
                Toast.makeText(getContext(), "CET", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_cj:
                Toast.makeText(getContext(), "Grade", Toast.LENGTH_SHORT).show();
                String id = editText_id.getText().toString().trim();
                String psw = editText_psw.getText().toString().trim();
                GradeLoginModel model = new GradeLoginModel(id,psw,QueryFragment.this);
                model.run();
                break;
            case R.id.query_exam:
                Toast.makeText(getContext(), "Exam", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    @Override
    public void showDialog() {
        progressDialog = ProgressDialog.show(getActivity(),"登录","正在登录...");
    }

    @Override
    public void closeDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showCourseData(ArrayList<Course> arrayList, String id, String psw) {

    }

    @Override
    public void showGradeData() {

    }

    @Override
    public void showErrorDialog(String notice) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("错误").setMessage(notice).setNegativeButton("确定",null).show();
    }

    @Override
    public void toastError(String notice) {
        Toast.makeText(getActivity(), notice, Toast.LENGTH_SHORT).show();
    }
}
