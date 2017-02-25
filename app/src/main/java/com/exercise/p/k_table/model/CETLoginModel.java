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
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by p on 2017/2/8.
 */

public class CETLoginModel extends AbstractLogin {
    private CET cet;
    private MyLoginListener_Query listener_query;
    public CETLoginModel(String user_id, String user_psw, MyLoginListener_Query listener) {
        super(user_id, user_psw);
        listener_query = listener;
    }

    private boolean getNeedData(){
        String cet_data_string = "";
        try {
            URL url = new URL("http://www.chsi.com.cn/cet/query?zkzh=" + user_id + "&xm=" + user_psw);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Referer","http://www.chsi.com.cn/cet/");
            urlConnection.setRequestProperty("Upgrade-Insecure-Requests","1");
            urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            urlConnection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            boolean isNeed = false;
            while ((line = reader.readLine()) != null){
                if (isNeed)
                    cet_data_string += line;
                else if (line.contains("查询结果"))
                    isNeed = true;
                if (line.contains("继续查询"))
                    break;
            }
            Pattern pattern = Pattern.compile("&nbsp|\\s|\"|;|\'");
            Matcher matcher = pattern.matcher(cet_data_string);
            cet_data_string = matcher.replaceAll("");
            urlConnection.disconnect();
            Log.i("CET",cet_data_string);
            cet = analystGradeData(cet_data_string);
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

    private CET analystGradeData(String string_data){
        Pattern pattern = Pattern.compile("总<spanclass=space></span>分：</th><tdcolspan=2class=fontBold><spanclass=colorRed>(.*?)</span></td>");
        Matcher matcher = pattern.matcher(string_data);
        int listen = 0;
        int read = 0;
        int write = 0;
        int all = 0;
        if (matcher.find()) {
            all = Integer.valueOf(matcher.group(1));
        }
        pattern = Pattern.compile("听<spanclass=space_long></span>力：</span></th><td>(.*?)</td>");
        matcher = pattern.matcher(string_data);
        if (matcher.find()) {
            listen = Integer.valueOf(matcher.group(1));
        }
        pattern = Pattern.compile("阅<spanclass=space_long></span>读：</span></th><td>(.*?)</td>");
        matcher = pattern.matcher(string_data);
        if (matcher.find()) {
            read = Integer.valueOf(matcher.group(1));
        }
        pattern = Pattern.compile("写作和翻译：</span></th><td>(.*?)</td></tr>");
        matcher = pattern.matcher(string_data);
        if (matcher.find()) {
            write = Integer.valueOf(matcher.group(1));
        }
        return new CET(listen,read,write,all);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener_query.showDialog();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        return getNeedData();
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        listener_query.closeDialog();
        if (success) {
            listener_query.showCETData(cet,"某同学#" + user_psw + ":");
        }
        else if (!internet)
            listener_query.showErrorDialog("网络错误");
        else if (!psw)
            listener_query.showErrorDialog("账号或密码错误");
        else
            listener_query.showErrorDialog("数据读取错误");
    }
}
