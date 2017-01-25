package com.exercise.p.k_table.control;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private Class fragmentClass[] = {MainFragment.class,MainFragment.class,MainFragment.class,MainFragment.class};

    ArrayList<Course> courses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initTabView();
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
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.colorWhiteBG)));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom_view);
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

    public void initTabView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        final FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        tabHost.setup(this, getSupportFragmentManager(), R.id.viewPager);
        for (int i = 0; i < fragmentClass.length; i++) {
            View view = layoutInflater.inflate(R.layout.tab_content,null);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec("" + i).setIndicator(view);
            tabHost.addTab(tabSpec, fragmentClass[i], null);
//            View tab_view = tabHost.getTabWidget().getChildTabViewAt(i);
//            ImageView imageView = (ImageView) tab_view.findViewById(R.id.tab_image);
//            imageView.setImageResource(resource_normal[i]);
//            TextView textView = (TextView) tab_view.findViewById(R.id.tab_text);
//            textView.setText(names[i]);
//            textView.setTextColor(getColor(R.color.colorGreyHint));
        }
//        ImageView imageView = (ImageView) tabHost.getTabWidget().getChildTabViewAt(0)
//                .findViewById(R.id.tab_image);
//        imageView.setImageResource(resource_click[0]);
//        TextView textView = (TextView) tabHost.getTabWidget().getChildTabViewAt(0)
//                .findViewById(R.id.tab_text);
//        textView.setTextColor(getColor(R.color.icon_color));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int x = Integer.valueOf(tabId);
                for (int i = 0; i < fragmentClass.length; i++){
                    View view = tabHost.getTabWidget().getChildTabViewAt(i);
                    ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
                    TextView textView = (TextView) view.findViewById(R.id.tab_text);
                    if (x != i) {
//                        imageView.setImageResource(resource_normal[i]);
                        textView.setTextColor(getColor(R.color.colorGreyHint));
                    }
                    else {
//                        imageView.setImageResource(resource_click[i]);
                        textView.setTextColor(getColor(R.color.icon_color));
                    }
                }
            }
        });
    }

}
