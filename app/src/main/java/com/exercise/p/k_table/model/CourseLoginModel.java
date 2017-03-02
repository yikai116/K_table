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
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by p on 2017/1/24.
 */

public class CourseLoginModel extends AbstractLogin{


    private ArrayList<Course> coursesList;
    private MyLoginListener_Course listener_course;

    /**
     *
     * @param user_id 账号
     * @param user_psw 密码
     * @param listener 回调
     */
    public CourseLoginModel(String user_id, String user_psw, MyLoginListener_Course listener) {
        super(user_id, user_psw);
        listener_course = listener;
    }
    /**
     * getNeedData 得到相应的数据
     * @return 返回是否得到成功
     */
    private boolean getNeedData(){
        String course_data_string = "";
        try {
//            Log.i("Login", cookie);
            URL bxqkb_url = new URL("http://zhjw.scu.edu.cn/xkAction.do?actionType=6");
            HttpURLConnection connection = (HttpURLConnection) bxqkb_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
            connection.connect();
            BufferedReader kb_reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GB2312"));
            String line;
            boolean isNeed = false;
            while ((line = kb_reader.readLine()) != null) {
                Log.i("Login",line);
                if (line.contains("选课结果列表")){
                    isNeed = true;
                }
                if (isNeed)
                    course_data_string += line;
//                if (line.contains("选课结果列表"))
//                    break;
            }
            Pattern pattern = Pattern.compile("&nbsp|\\s|\"|;|望江|华西|江安");
            Matcher matcher = pattern.matcher(course_data_string);
            course_data_string = matcher.replaceAll("");
            Log.i("Login",course_data_string);
            Log.i("Login", course_data_string.substring(0,course_data_string.length()/2));
            Log.i("Login", course_data_string.substring(course_data_string.length()/2,course_data_string.length()));
            connection.disconnect();
            coursesList = analystCourseData(course_data_string);
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
    /**
     * analystGradeData 在getNeedDate中调用
     * @param string_data 返回分析数据得到的信息
     * @return 信息
     */
    private ArrayList<Course> analystCourseData(String string_data){
        ArrayList<Course> courses_temp = new ArrayList<>();
        Pattern pattern_course = Pattern.compile("<trclass=oddonMouseOut=this.className='even'" +
                "onMouseOver=this.className='evenfocus'><tdrowspan=1>.*?</td><tdrowspan=1>.*?</td>" +
                "<tdrowspan=1>(.*?)</td><tdrowspan=1>.*?</td><tdrowspan=1>(.*?)</td>" +
                "<tdrowspan=1>(.*?)</td><tdrowspan=1>.*?</td><tdrowspan=1>(.*?)</td>" +
                "<tdrowspan=1align=center>.*?<tdrowspan=1>.*?</td><tdrowspan=1>.*?</td>" +
                "<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>.*?</td><td>(.*?)</td>" +
                "<td>(.*?)</td></tr>");
        Matcher matcher_course = pattern_course.matcher(string_data);
        while (matcher_course.find()) {
            String name = matcher_course.group(1);
            if (name.equals("")){
                name += " ";
            }
            Float credit = Float.valueOf(matcher_course.group(2));
            String type = matcher_course.group(3);
            if (type.equals("")){
                type += " ";
            }
            String teacher = matcher_course.group(4);
            if (teacher.equals("")){
                teacher += " ";
            }
            String week_of_class = matcher_course.group(5);
            if (week_of_class.equals("")){
                week_of_class += " ";
            }
            int day = Integer.parseInt(matcher_course.group(6));
            String temp_size = matcher_course.group(7);
            StringTokenizer tokenizer = new StringTokenizer(temp_size,"~");
            int start = Integer.parseInt(tokenizer.nextToken());
            int size = Integer.valueOf(tokenizer.nextToken()) - start + 1;
            String place = "@" + matcher_course.group(8) + matcher_course.group(9);
            if (place.equals("")){
                place += " ";
            }
            courses_temp.add(new Course(name,start,size,teacher,day,place,type,week_of_class,credit));
        }
        Log.i("Login",courses_temp.toString());


        pattern_course = Pattern.compile("<trclass=oddonMouseOut=this.className='even'" +
                "onMouseOver=this.className='evenfocus'><tdrowspan=2>.*?</td><tdrowspan=2>.*?</td>" +
                "<tdrowspan=2>(.*?)</td><tdrowspan=2>.*?</td><tdrowspan=2>(.*?)</td>" +
                "<tdrowspan=2>(.*?)</td><tdrowspan=2>.*?</td><tdrowspan=2>(.*?)</td>" +
                "<tdrowspan=2align=center>.*?</td><tdrowspan=2>.*?</td><tdrowspan=2>.*?</td>" +
                "<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>.*?</td><td>(.*?)</td>" +
                "<td>(.*?)</td></tr>" +
                "<trclass=oddonMouseOut=this.className='even'onMouseOver=this.className='evenfocus'>" +
                "<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>.*?</td><td>(.*?)</td>" +
                "<td>(.*?)</td></tr>");
        matcher_course = pattern_course.matcher(string_data);
        while (matcher_course.find()) {
            String name = matcher_course.group(1);
            if (name.equals("")){
                name += " ";
            }
            Float credit = Float.valueOf(matcher_course.group(2));
            String type = matcher_course.group(3);
            if (type.equals("")){
                type += " ";
            }
            String teacher = matcher_course.group(4);
            if (teacher.equals("")){
                teacher += " ";
            }
            String week_of_class1 = matcher_course.group(5);
            if (week_of_class1.equals("")){
                week_of_class1 += " ";
            }
            int day1 = Integer.parseInt(matcher_course.group(6));
            String temp_size1 = matcher_course.group(7);
            StringTokenizer tokenizer1 = new StringTokenizer(temp_size1,"~");
            int start1 = Integer.parseInt(tokenizer1.nextToken());
            int size1 = Integer.valueOf(tokenizer1.nextToken()) - start1 + 1;
            String place1 = "@" + matcher_course.group(8) + matcher_course.group(9);
            if (place1.equals("")){
                place1 += " ";
            }
            courses_temp.add(new Course(name,start1,size1,teacher,day1,place1,type,week_of_class1,credit));
            String week_of_class2 = matcher_course.group(10);
            if (week_of_class2.equals("")){
                week_of_class2 += " ";
            }
            int day2 = Integer.parseInt(matcher_course.group(11));
            String temp_size2 = matcher_course.group(12);
            StringTokenizer tokenizer2 = new StringTokenizer(temp_size2,"~");
            int start2 = Integer.parseInt(tokenizer2.nextToken());
            int size2 = Integer.valueOf(tokenizer2.nextToken()) - start2 + 1;
            String place2 = "@" + matcher_course.group(13) + matcher_course.group(14);
            if (place2.equals("")){
                place2 += " ";
            }
            courses_temp.add(new Course(name,start2,size2,teacher,day2,place2,type,week_of_class2,credit));
        }
        return courses_temp;
    }

//    异步任务
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener_course.showDialog();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (login())
            return getNeedData();
        return false;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        listener_course.closeDialog();
        if (success) {
            listener_course.showCourseData(coursesList,user_id,user_psw);
            try {
                MemoryAccess.saveCourseToSD(coursesList);
            } catch (IOException e) {
                e.printStackTrace();
                listener_course.toastError("文件存放错误");
            }
        }
        else if (!internet)
            listener_course.showErrorDialog("网络错误");
        else if (!psw)
            listener_course.showErrorDialog("账号或密码错误");
        else
            listener_course.showErrorDialog("数据读取错误");
    }

}
