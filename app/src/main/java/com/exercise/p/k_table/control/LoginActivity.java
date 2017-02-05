package com.exercise.p.k_table.control;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.p.k_table.model.Course;
import com.exercise.p.k_table.model.Global_Info;
import com.exercise.p.k_table.model.LoginModel;
import com.exercise.p.k_table.model.MyLoginListener;
import com.exercise.p.k_table.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements MyLoginListener {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("登录");
        initView();
    }

    public void initView(){
        Button login = (Button) findViewById(R.id.loginButton);
        final EditText idEdit = (EditText) findViewById(R.id.idEdit);
        final EditText pswEdit = (EditText) findViewById(R.id.pswEdit);
        TextView forgetPsw = (TextView) findViewById(R.id.forgetPsw);
        TextView signUp = (TextView) findViewById(R.id.signUp);
        CheckBox serviceClause = (CheckBox) findViewById(R.id.serviceClause);
        TextView showClause = (TextView) findViewById(R.id.showClause);
        CheckBox showPsw = (CheckBox) findViewById(R.id.showPswCheckBox);
        showPsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pswEdit.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else
                    pswEdit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
        showClause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "显示条款", Toast.LENGTH_SHORT).show();
            }
        });
        serviceClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(LoginActivity.this, "同意", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "不同意", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
            }
        });
        forgetPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "找回密码", Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEdit.getText().toString().trim();
                String psw = pswEdit.getText().toString().trim();

                LoginModel loginModel = new LoginModel(id,psw,LoginActivity.this);
                loginModel.run();
            }
        });

    }

    @Override
    public void showDialog() {
        progressDialog = ProgressDialog.show(LoginActivity.this,"登录","正在登录...");
    }

    @Override
    public void closeDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showCourseData(ArrayList<Course> arrayList, String id, String psw) {
        Global_Info.setUser_id(id);
        Global_Info.setUser_psw(psw);
        Global_Info.savaInfo(LoginActivity.this);
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,MainActivity.class);
//        ArrayList<String> courseString = new ArrayList<>();
//        for (Course course : arrayList){
//            courseString.add(course.toString());
//        }
//        intent.putStringArrayListExtra("courses",courseString);
        Bundle bundle = new Bundle();
        bundle.putSerializable("courses",arrayList);
        intent.putExtra("courses",bundle);
        LoginActivity.this.startActivity(intent);
        SharedPreferences sharedPreferences = getSharedPreferences("IsFirstAndIsLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        editor.apply();
        LoginActivity.this.finish();
    }

    @Override
    public void showErrorDialog(String notice) {
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("错误").setMessage(notice).setNegativeButton("确定",null).show();
    }

    @Override
    public void toastError(String notice) {
        Toast.makeText(this, notice, Toast.LENGTH_SHORT).show();
    }
}
