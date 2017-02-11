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

public class ExamLoginModel extends AbstractLogin {
    private ArrayList<Exam> examList;
    private MyLoginListener_Query listener_query;
    public ExamLoginModel(String user_id, String user_psw, MyLoginListener_Query listener) {
        super(user_id, user_psw);
        listener_query = listener;
    }

    private boolean getNeedData(){
        String exam_data_string = "";
        try {
            URL bxqcj_url = new URL("http://zhjw.scu.edu.cn/ksApCxAction.do?oper=getKsapXx");
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
                Log.i("Exam",line);
                if (isNeed)
                    exam_data_string += line;
                else if (line.contains("备注"))
                    isNeed = true;
            }

            Pattern pattern = Pattern.compile("&nbsp|\\s|\"|;|\'");
            Matcher matcher = pattern.matcher(exam_data_string);
            exam_data_string = matcher.replaceAll("");
            connection.disconnect();
            examList = analystGradeData(exam_data_string);
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

    private ArrayList<Exam> analystGradeData(String string_data){
        Log.i("Exam",string_data);
        ArrayList<Exam> exams = new ArrayList<>();
        Pattern pattern_section = Pattern.compile("<trclass=odd><td>(.*?)</td><td>.*?</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>.*?</td><td>.*?</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td></td><td></td></tr>");
        Matcher matcher_section = pattern_section.matcher(string_data);

        while (matcher_section.find()) {
            Exam exam = new Exam(matcher_section.group(1),matcher_section.group(4),
                    matcher_section.group(2)+matcher_section.group(3),
                    matcher_section.group(5)+ " " + matcher_section.group(6),matcher_section.group(7));
            exams.add(exam);
        }
        return exams;
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
            listener_query.showExamData(examList,"某同学#" + (user_id.length()>3?user_id.substring(user_id.length()-3):user_id) + ":");
        }
        else if (!internet)
            listener_query.showErrorDialog("网络错误");
        else if (!psw)
            listener_query.showErrorDialog("账号或密码错误");
        else
            listener_query.showErrorDialog("数据读取错误");
    }
}
