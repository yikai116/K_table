package com.exercise.p.k_table.control;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by p on 2017/1/25.
 */

public class MainFragment extends Fragment{
    LinearLayout[] week_context_layouts;
    LinearLayout[] weeks;
    ArrayList<Course> courses;
    LinearLayout container;
    int[] Bgcolors = {R.drawable.shape_course_1,R.drawable.shape_course_2,R.drawable.shape_course_3,
            R.drawable.shape_course_4,R.drawable.shape_course_5,R.drawable.shape_course_6,
            R.drawable.shape_course_7,R.drawable.shape_course_8,R.drawable.shape_course_9};
    int[] string_weeks = {R.string.monday,R.string.tuesday,R.string.wednesday,R.string.thursday,
                            R.string.friday,R.string.saturday,R.string.sunday};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, null);
        initView(view);
        showCourse(courses, week_context_layouts);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        courses = (ArrayList<Course>) getArguments().getSerializable("courses");
    }

    public void initView(View view){
        week_context_layouts = new LinearLayout[7];
        week_context_layouts[0] = (LinearLayout) view.findViewById(R.id.content_week1);
        week_context_layouts[1] = (LinearLayout) view.findViewById(R.id.content_week2);
        week_context_layouts[2] = (LinearLayout) view.findViewById(R.id.content_week3);
        week_context_layouts[3] = (LinearLayout) view.findViewById(R.id.content_week4);
        week_context_layouts[4] = (LinearLayout) view.findViewById(R.id.content_week5);
        week_context_layouts[5] = (LinearLayout) view.findViewById(R.id.content_week6);
        week_context_layouts[6] = (LinearLayout) view.findViewById(R.id.content_week7);
        weeks = new LinearLayout[7];
        LinearLayout linearLayout_top_week = (LinearLayout) view.findViewById(R.id.top_week_include);
        weeks[0] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week1);
        weeks[1] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week2);
        weeks[2] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week3);
        weeks[3] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week4);
        weeks[4] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week5);
        weeks[5] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week6);
        weeks[6] = (LinearLayout) linearLayout_top_week.findViewById(R.id.top_week7);
        container = (LinearLayout) view.findViewById(R.id.fragment_main);
        initTime();
    }
    
    private void initTime(){
        //week得到当前周几
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0)
            week = 7;
        Log.i("Main","week" + calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DATE,-(week-1));
        setDefaultWeight(week - 1);
        for (int i = 0; i < weeks.length; i++){
            TextView day = (TextView)(weeks[i].findViewById(R.id.top_week_tab_day));
            day.setText(getResources().getString(string_weeks[i]));
            TextView date = (TextView)(weeks[i].findViewById(R.id.top_week_tab_date));
            int month = (calendar.get(Calendar.MONTH) + 1);
            if (month < 10)
                date.setText("0" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
            else
                date.setText((calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
            calendar.add(calendar.DATE,1);
            final int x = i;
            weeks[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDefaultWeight(x);
                    v.findViewById(R.id.top_tab_line).setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void setDefaultWeight(int x){
        for (int i = 0; i < weeks.length; i++){
            if (i == x) {
                LinearLayout.LayoutParams linear_params = (LinearLayout.LayoutParams) weeks[i].getLayoutParams();
                linear_params.weight = 2f;
                weeks[i].setLayoutParams(linear_params);
                linear_params = (LinearLayout.LayoutParams) week_context_layouts[i].getLayoutParams();
                linear_params.weight = 2f;
                week_context_layouts[i].setLayoutParams(linear_params);
                weeks[i].findViewById(R.id.top_tab_line).setVisibility(View.VISIBLE);
            }
            else {
                LinearLayout.LayoutParams linear_params = (LinearLayout.LayoutParams) weeks[i].getLayoutParams();
                linear_params.weight = 1f;
                weeks[i].setLayoutParams(linear_params);
                linear_params = (LinearLayout.LayoutParams) week_context_layouts[i].getLayoutParams();
                linear_params.weight = 1f;
                week_context_layouts[i].setLayoutParams(linear_params);
                weeks[i].findViewById(R.id.top_tab_line).setVisibility(View.GONE);
            }
        }
    }

    public void showCourse(ArrayList<Course> courses, LinearLayout[] container){
        int height = (int)getResources().getDimension(R.dimen.activity_course_content_height);
        for (Course course : courses){
            int size = course.getSize();
            int start = course.getStart();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, size * height + size - 1);
            if (start > 4)
                params.setMargins(0,start * height - height + start - 1 ,0,0);
            else
                params.setMargins(0,start * height - height + start - 1,0,0);
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            final View view = layoutInflater.inflate(R.layout.course_view,null);
            TextView textView_name = (TextView) view.findViewById(R.id.course_name);
            TextView textView_place = (TextView) view.findViewById(R.id.course_place);
            textView_name.setText(course.getName());
            textView_place.setText(course.getPlace());
            if (size == 1) {
                textView_name.setMaxLines(1);
            }
            if (size == 2) {
                textView_name.setMaxLines(3);
            }
            else {
                textView_name.setMaxLines(6);
            }
            view.setBackground(getResources().getDrawable(Bgcolors[course.getColor()]));
            View popup_view = getPopupView(layoutInflater,course);
            final PopupWindow popupWindow = new PopupWindow(popup_view, ViewGroup.LayoutParams.WRAP_CONTENT, 3*height);
            popupWindow.setOutsideTouchable(true);
            popup_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Main",popupWindow.isShowing() + "");
                    popupWindow.showAsDropDown(view);
                }
            });
            RelativeLayout relativeLayout = (RelativeLayout)(container[course.getDay()-1].findViewById(R.id.include));
            relativeLayout.addView(view,params);
        }
    }

    private View getPopupView(LayoutInflater inflater,Course course){
        View popup_view = inflater.inflate(R.layout.course_popu_window,null);
        TextView name = (TextView) popup_view.findViewById(R.id.popup_window_text_name);
        name.setText("课名：" + course.getName());
        TextView place = (TextView) popup_view.findViewById(R.id.popup_window_text_place);
        place.setText("地点：" + course.getPlace());
        TextView time = (TextView) popup_view.findViewById(R.id.popup_window_text_time);
        time.setText("时间：" + course.getWeek_of_class());
        TextView credit = (TextView) popup_view.findViewById(R.id.popup_window_text_credit);
        credit.setText("学分：" + course.getCredit());
        TextView type = (TextView) popup_view.findViewById(R.id.popup_window_text_type);
        type.setText("类型：" + course.getType());
        TextView teacher = (TextView) popup_view.findViewById(R.id.popup_window_text_teacher);
        teacher.setText("老师：" + course.getTeacher());
        return popup_view;
    }


}
