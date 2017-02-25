package com.exercise.p.k_table.control;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.CET;
import com.exercise.p.k_table.model.CETLoginModel;
import com.exercise.p.k_table.model.Exam;
import com.exercise.p.k_table.model.ExamLoginModel;
import com.exercise.p.k_table.model.Grade;
import com.exercise.p.k_table.model.GradeLoginModel;
import com.exercise.p.k_table.model.MyLoginListener_Query;

import java.util.ArrayList;

/**
 * Created by p on 2017/2/3.
 */

public class QueryFragment extends Fragment implements MyLoginListener_Query{
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
        CheckBox showPsw = (CheckBox) view.findViewById(R.id.query_showPswCheckBox);
        showPsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editText_psw.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else
                    editText_psw.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
        return view;
    }

    public class QueryListener implements View.OnClickListener
    {
        @Override
        public void onClick (View v){
            String id = editText_id.getText().toString().trim();
            String psw = editText_psw.getText().toString().trim();
            switch (v.getId()) {
            case R.id.query_cet:
                CETLoginModel cetLoginModel = new CETLoginModel(id,psw,QueryFragment.this);
                cetLoginModel.run();
                break;
            case R.id.query_cj:
                GradeLoginModel model_grade = new GradeLoginModel(id,psw,QueryFragment.this);
                model_grade.run();
                break;
            case R.id.query_exam:
                ExamLoginModel model_exam = new ExamLoginModel(id,psw,QueryFragment.this);
                model_exam.run();
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
    public void showGradeData(ArrayList<Grade> grades, String name) {
//        Log.i("Query",grades.toString() + name);
        Intent intent = new Intent();
        intent.setClass(getActivity(),Query_ResActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag","grade");
        bundle.putSerializable("value",grades);
        bundle.putString("name",name);
        intent.putExtra("info",bundle);
        startActivity(intent);
    }

    @Override
    public void showExamData(ArrayList<Exam> exams, String name) {
        Log.i("Query",exams.toString() + name);
        Intent intent = new Intent();
        intent.setClass(getActivity(),Query_ResActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag","exam");
        bundle.putSerializable("value",exams);
        bundle.putString("name",name);
        intent.putExtra("info",bundle);
        startActivity(intent);
    }

    @Override
    public void showCETData(CET cet, String name) {
        Log.i("Query",cet.toString() + name);
        Intent intent = new Intent();
        intent.setClass(getActivity(),Query_ResActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag","cet");
        bundle.putSerializable("value",cet);
        bundle.putString("name",name);
        intent.putExtra("info",bundle);
        startActivity(intent);
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
