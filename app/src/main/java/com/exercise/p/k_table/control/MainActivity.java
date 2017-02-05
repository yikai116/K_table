package com.exercise.p.k_table.control;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exercise.p.k_table.R;
import com.exercise.p.k_table.model.Course;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainFragment mainFragment;
    QueryFragment queryFragment;
    InfoFragment infoFragment;
    ActionBar actionBar;

    ArrayList<Course> courses;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        courses = (ArrayList<Course>) getIntent().getBundleExtra("courses").get("courses");
        initActionBar();
        initTabView();
        Bundle bundle = new Bundle();
        bundle.putSerializable("courses",courses);
        setFragment(0,bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fragment_main_menu_exit){
            MainActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.colorWhiteBG)));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom_view);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(getResources().getDrawable(R.drawable.ic_logo_small));
    }

    public void initTabView() {
        LinearLayout tab1 = (LinearLayout) findViewById(R.id.tab1);
        ImageView imageView = (ImageView) tab1.findViewById(R.id.tab_image);
        TextView textView = (TextView) tab1.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_tab1);
        textView.setText("课程表");
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("courses",courses);
                setFragment(0,bundle);
            }
        });
        LinearLayout tab2= (LinearLayout) findViewById(R.id.tab2);
        imageView = (ImageView) tab2.findViewById(R.id.tab_image);
        textView = (TextView) tab2.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_tab2);
        textView.setText("查询");
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(1,null);
            }
        });
        LinearLayout tab3 = (LinearLayout) findViewById(R.id.tab3);
        imageView = (ImageView) tab3.findViewById(R.id.tab_image);
        textView = (TextView) tab3.findViewById(R.id.tab_text);
        imageView.setImageResource(R.drawable.ic_login_id);
        textView.setText("个人");
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(2,null);
            }
        });
    }

    public void setFragment(int x,Bundle bundle){
        TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.actionbar_title);
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (x){
            case 0:
                if (mainFragment == null){
                    mainFragment = new MainFragment();
                    Log.i("MainFragment","new");
                    mainFragment.setArguments(bundle);
                }
                textView.setText("第X周");
                fragmentTransaction.replace(R.id.viewPager,mainFragment);
                break;
            case 1:
                if (queryFragment == null){
                    queryFragment = new QueryFragment();
                    Log.i("QueryFragment","new");
                    queryFragment.setArguments(bundle);
                }
                textView.setText("查询");
                fragmentTransaction.replace(R.id.viewPager,queryFragment);
                break;
            case 2:
                if (infoFragment == null){
                    infoFragment = new InfoFragment();
                    Log.i("InfoFragment","new");
                    infoFragment.setArguments(bundle);
                }
                textView.setText("个人中心");
                fragmentTransaction.replace(R.id.viewPager,infoFragment);
                break;
        }
        fragmentTransaction.commit();
    }

}
