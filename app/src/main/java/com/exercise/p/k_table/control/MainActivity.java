package com.exercise.p.k_table.control;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();

        ArrayList<String> coursesString = getIntent().getStringArrayListExtra("courses");
//        Log.i("Main",coursesString.toString());
        courses = new ArrayList<>();

        for (String s : coursesString){
            courses.add(new Course(s));
        }
//        Log.i("Main",courses.toString());
//        Toast.makeText(this, Global_Info.getUser_name(), Toast.LENGTH_SHORT).show();
    }

    public void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhiteBG)));
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setCustomView(R.layout.actionbar_custom_view);
//        Button button_search = (Button) actionBar.getCustomView().findViewById(R.id.button_search);
//        button_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this,SearchActivity.class);
//                MainActivity.this.startActivity(intent);
//            }
//        });
    }

}
