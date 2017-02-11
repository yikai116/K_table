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
import com.exercise.p.k_table.model.Exam;

import java.util.ArrayList;

/**
 * Created by p on 2017/2/11.
 */

public class Query_ExamFragment extends Fragment {
    ExpandableListView listView;
    ArrayList<Exam> exams;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_exam_query,null);
        listView = (ExpandableListView) view.findViewById(R.id.data_show_exlist_view);

        exams = (ArrayList<Exam>) getActivity().getIntent().getBundleExtra("info").getSerializable("value");
        ArrayList<Exam> mid_term = new ArrayList<>();
        ArrayList<Exam> end_term = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++){
            Exam temp = exams.get(i);
            if (temp.getExam_name().equals("期中考试"))
                mid_term.add(temp);
            else
                end_term.add(temp);
        }
        MyExpandsAdapter adapter = new MyExpandsAdapter(mid_term,end_term);
        listView.setAdapter(adapter);

        String stu_name = (String) getActivity().getIntent().getBundleExtra("info").getSerializable("name");
        TextView textView = (TextView) view.findViewById(R.id.exam_query_stu_name);
        textView.setText(stu_name);
        return view;
    }


    private class MyExpandsAdapter extends BaseExpandableListAdapter {
        String[] group = {"期中考试","期末考试"};
        ArrayList<ArrayList<Exam>> child;

        public MyExpandsAdapter(ArrayList<Exam> child1,ArrayList<Exam> child2){
            child = new ArrayList<>();
            child.add(child1);
            child.add(child2);
        }
        @Override
        public int getGroupCount() {
            return group.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.group_item,null);
            TextView textView = (TextView) view.findViewById(R.id.child_text);
            textView.setText(group[groupPosition]);
            ImageView imageView = (ImageView) view.findViewById(R.id.group_image);
            if (isExpanded){
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_down);
            }
            else {
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_right);
            }
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.child_item,null);
            TextView textView = (TextView) view.findViewById(R.id.child_text);
            textView.setText(child.get(groupPosition).get(childPosition).getCourse_name());
            textView = (TextView) view.findViewById(R.id.child_time);
            textView.setText(child.get(groupPosition).get(childPosition).getTime());
            textView = (TextView) view.findViewById(R.id.child_place);
            textView.setText(child.get(groupPosition).get(childPosition).getPlace());
            textView = (TextView) view.findViewById(R.id.child_seat);
            textView.setText("座位号:" + child.get(groupPosition).get(childPosition).getSeat());
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
