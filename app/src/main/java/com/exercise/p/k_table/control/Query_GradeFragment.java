package com.exercise.p.k_table.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.CheckableLinearLayout;
import com.exercise.p.k_table.model.Grade;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p on 2017/2/9.
 */

public class Query_GradeFragment extends Fragment {
    ListView listView;
    ArrayList<Grade> grades;
    Button button_chooseAll;
    Button button_chooseRequired;
    Button button_cal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_grade_query,null);
        listView = (ListView) view.findViewById(R.id.data_show_list_view);
        button_chooseAll = (Button) view.findViewById(R.id.grade_chooseAll);
        button_chooseRequired = (Button) view.findViewById(R.id.grade_chooseRequired);
        button_cal = (Button) view.findViewById(R.id.grade_cal);
        grades = (ArrayList<Grade>) getActivity().getIntent().getBundleExtra("info").getSerializable("value");

        TextView textView = (TextView) view.findViewById(R.id.grade_query_stu_name);
        String stu_name = (String) getActivity().getIntent().getBundleExtra("info").getSerializable("name");
        textView.setText(stu_name);

        initListView();
        initButton();
        return view;
    }

    private void initListView(){
        final List<Map<String,String>> grades_mapList = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++){
            Map<String,String> map_temp = new HashMap<>();
            map_temp.put("name",grades.get(i).getName());
            map_temp.put("credit",grades.get(i).getCredit() + "");
            map_temp.put("type",grades.get(i).getType());
            map_temp.put("grade",grades.get(i).getmGrade() + "");
            grades_mapList.add(map_temp);
        }
        MyAdapter adapter = new MyAdapter(grades_mapList);
        listView.setAdapter(adapter);
    }

    private void initButton(){
        button_chooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < grades.size(); i++){
                    listView.setItemChecked(i,true);
                }
            }
        });
        button_chooseRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < grades.size(); i++){
                    if (grades.get(i).getType().equals("必修"))
                        listView.setItemChecked(i,true);
                }
            }
        });
        button_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double sumOfGradeMulCredit = 0;
                double sumOfGPAMulCredit = 0;
                double sumOfCredit = 0;
                for (int i = 0; i < grades.size(); i++){
                    if (listView.isItemChecked(i)) {
                        int credit = grades.get(i).getCredit();
                        int grade = grades.get(i).getmGrade();
                        double GPA = grades.get(i).getGPA();
                        sumOfCredit += credit;
                        sumOfGradeMulCredit += credit*grade;
                        sumOfGPAMulCredit += credit*GPA;
                    }
                }
                double ave_GPA = sumOfGPAMulCredit / sumOfCredit;
                double ave_Grade = sumOfGradeMulCredit / sumOfCredit;
                DecimalFormat    df   = new DecimalFormat("0.00");
                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("结果")
                        .setMessage("平均成绩" + df.format(ave_Grade) + "\n"
                                + "平均绩点" + df.format(ave_GPA))
                        .setNegativeButton("确定",null).show();
            }
        });
    }

    public class MyAdapter extends BaseAdapter{
        List<Map<String,String>> datas;
        public MyAdapter(List<Map<String,String>> data){
            this.datas = data;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckableLinearLayout view1 = (CheckableLinearLayout)
                    (getActivity().getLayoutInflater()).inflate(R.layout.grade_list_item,null);
//            Log.i("Res","position" + position);
            if (position%2 == 0)
                view1.setColor(getResources().getColor(R.color.list_item_deep));
            else
                view1.setColor(getResources().getColor(R.color.list_item_light));
            TextView course_name = (TextView) view1.findViewById(R.id.grade_list_item_courseName);
            TextView credit = (TextView) view1.findViewById(R.id.grade_list_item_credit);
            TextView type = (TextView) view1.findViewById(R.id.grade_list_item_type);
            TextView grade = (TextView) view1.findViewById(R.id.grade_list_item_grade);
            Map<String,String> temp = datas.get(position);
            course_name.setText(temp.get("name"));
            credit.setText(temp.get("credit"));
            type.setText(temp.get("type"));
            grade.setText(temp.get("grade"));
            return view1;
        }
    }
}
