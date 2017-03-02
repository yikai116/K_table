package com.exercise.p.k_table.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by p on 2017/2/8.
 */

public abstract class AbstractLogin extends AsyncTask<Void,Void,Boolean>{
    String user_id;
    String user_psw;
    boolean internet = true;
    boolean psw = true;
    String cookie;

    /**
     * login 默认登录川大教务处
     * @return 返回是否登录成功
     */
    boolean login(){
        try {
            URL login_url = new URL("http://zhjw.scu.edu.cn/loginAction.do");
            HttpURLConnection connection = (HttpURLConnection) login_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000*5);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            String params = "zjh=" + URLEncoder.encode(user_id,"UTF-8") + "&mm=" + URLEncoder.encode(user_psw,"UTF-8");
            os.write(params.getBytes());
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GB2312"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("密码不正确")||line.contains("证件号不存在")){
                    psw = false;
                    return false;
                }
            }
//            Log.i("Login","正确");
            Map<String, List<String>> header = connection.getHeaderFields();
            cookie = header.get("Set-Cookie").get(0);
            connection.disconnect();
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
    AbstractLogin(String user_id, String user_psw){
        this.user_id = user_id;
        this.user_psw = user_psw;
    }

    /**
     * 执行异步任务
     */
    public void run(){
        this.execute();
    }
}
