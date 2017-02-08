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
    private String course_data_string = "";
    public GradeLoginModel(String user_id, String user_psw, MyLoginListener listener) {
        super(user_id, user_psw, listener);
    }

    private boolean getNeedData(){
        try {
//            Log.i("Login", cookie);
            URL bxqcj_url = new URL("http://zhjw.scu.edu.cn/bxqcjcxAction.do");
            HttpURLConnection connection = (HttpURLConnection) bxqcj_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            connection.setRequestProperty("Host","202.115.47.141");
//            connection.setRequestProperty("Connection","keep-alive");
//            connection.setRequestProperty("Referer","http://zhjw.scu.edu.cn/menu/s_menu.jsp");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//            connection.setRequestProperty("Upgrade-Insecure-Requests","1");
//            connection.setRequestProperty("Accept-Encoding","gzip, deflate, sdch");
//            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

            connection.connect();
            Log.i("Grade",connection.getRequestProperty("Host"));
            BufferedReader kb_reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GB2312"));
            String line;
            boolean isNeed = false;
            while ((line = kb_reader.readLine()) != null) {
//                Log.i("Grade","1" + line);
            }
//            Pattern pattern = Pattern.compile("&nbsp|\\s|\"|;|望江|华西|江安");
//            Matcher matcher = pattern.matcher(course_data_string);
//            course_data_string = matcher.replaceAll("");
//            Log.i("Login",course_data_string);
//            Log.i("Login", course_data_string.substring(0,course_data_string.length()/2));
//            Log.i("Login", course_data_string.substring(course_data_string.length()/2,course_data_string.length()));
            connection.disconnect();
//            coursesList = analystCourseData(course_data_string);
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

    private ArrayList<Course> analystCourseData(String string_data){
        ArrayList<Course> courses_temp = new ArrayList<>();
        Pattern pattern_section = Pattern.compile("<tdwidth=11%>(.*?)<trbgcolor=#FFFFFF>");
        Matcher matcher_section = pattern_section.matcher(string_data);

        int section = 1;
        while (matcher_section.find()) {
            String each = matcher_section.group(1);
//            Log.i("Login", each);
            Pattern pattern_eachday = Pattern.compile("<td>(.*?)(<br>)?</td>");
            Matcher matcher_eachday = pattern_eachday.matcher(each);
            int week = 1;
            while (matcher_eachday.find()){
                String name = matcher_eachday.group(1);
//                Log.i("Login",name);
                if (!name.equals("")) {
                    Pattern pattern_name = Pattern.compile("_.*?\\((.*?)\\)");
                    Matcher matcher_name = pattern_name.matcher(name);
                    if (matcher_name.find()){
                        String place = "@";
                        place += matcher_name.group(1);
                        name = name.substring(0,name.length() - place.length() - 1);
                        name = name + place;
                    }
                    courses_temp.add(new Course(name,section,1," ",week));
                }
                week++;
            }
            section++;
        }
//        Log.i("Login",courses_temp.toString());

        //合并课程
        for (int i = 0; i < courses_temp.size(); i++){
            Course temp = courses_temp.get(i);
            for (int j = i + 1; j < courses_temp.size(); j++){
                Course temp1 = courses_temp.get(j);
                if (temp.getName().equals(temp1.getName())
                        &&temp.getDay()==temp1.getDay()
                        &&temp.getStart()+temp.getSize()==temp1.getStart()){
                    temp.setSize(temp.getSize()+1);
                    courses_temp.remove(j);
                    j--;
                }
            }
        }
//        Log.i("Login",courses_temp.toString());
        return courses_temp;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.showDialog();
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
        Log.i("Login",success + "");
        listener.closeDialog();
        if (success) {
            listener.showGradeData();
        }
        else if (!internet)
            listener.showErrorDialog("网络错误");
        else if (!psw)
            listener.showErrorDialog("账号或密码错误");
        else
            listener.showErrorDialog("数据读取错误");
    }
}
