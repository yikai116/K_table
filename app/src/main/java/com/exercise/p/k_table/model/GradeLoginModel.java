package com.exercise.p.k_table.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by p on 2017/2/8.
 */

public class GradeLoginModel extends AbstractLogin {
    private ArrayList<Grade> gradeList;
    private MyLoginListener_Query listener_query;
    public GradeLoginModel(String user_id, String user_psw, MyLoginListener_Query listener) {
        super(user_id, user_psw);
        listener_query = listener;
    }

    private boolean getNeedData(){
        String grade_data_string = "";
        try {
            URL bxqcj_url = new URL("http://zhjw.scu.edu.cn/bxqcjcxAction.do");
            HttpURLConnection connection = (HttpURLConnection) bxqcj_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            connection.setRequestProperty("Host","202.115.47.141");
            connection.connect();
            BufferedReader kb_reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GB2312"));
            String line;
            boolean isNeed = false;
            while ((line = kb_reader.readLine()) != null) {
                if (isNeed)
                    grade_data_string += line;
                else if (line.contains(">成绩"))
                    isNeed = true;
            }

            Pattern pattern = Pattern.compile("&nbsp|\\s|\"|;|\'");
            Matcher matcher = pattern.matcher(grade_data_string);
            grade_data_string = matcher.replaceAll("");
            connection.disconnect();
            gradeList = analystGradeData(grade_data_string);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            internet = false;
            return false;
        }
        return true;
    }

    private ArrayList<Grade> analystGradeData(String string_data){
        ArrayList<Grade> grades = new ArrayList<>();
        Pattern pattern_section = Pattern.compile("<trclass=oddonMouseOut=this.className=" +
                "evenonMouseOver=this.className=evenfocus><tdalign=center>.*?</td><tdalign=" +
                "center>.*?</td><tdalign=center>(.*?)</td><tdalign=center>.*?</td><tdalign=" +
                "center>(.*?)</td><tdalign=center>(.*?)</td><tdalign=center>(.*?)</td></tr>");
        Matcher matcher_section = pattern_section.matcher(string_data);

        while (matcher_section.find()) {
            Grade grade_class_temp = new Grade(matcher_section.group(1),Integer.valueOf(matcher_section.group(2)),
                    matcher_section.group(3),Integer.valueOf(matcher_section.group(4)));
            grades.add(grade_class_temp);
        }
        return grades;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener_query.showDialog();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        if (login())
            return getNeedData();
        else
            return false;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        listener_query.closeDialog();
        if (success) {
            listener_query.showGradeData(gradeList,"某同学#" + (user_id.length()>3?user_id.substring(user_id.length()-3):user_id) + ":");
        }
        else if (!internet)
            listener_query.showErrorDialog("网络错误");
        else if (!psw)
            listener_query.showErrorDialog("账号或密码错误");
        else
            listener_query.showErrorDialog("数据读取错误");
    }
}
