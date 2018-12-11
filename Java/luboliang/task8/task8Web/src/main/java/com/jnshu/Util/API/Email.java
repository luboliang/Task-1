package com.jnshu.Util.API;

import com.jnshu.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Email {

    public static String emailCode(User user) throws IOException {
        final String url = "http://api.sendcloud.net/apiv2/mail/send";
        final String apiUser = "luboliang_test_AVtO5y";
        final String apiKey = "KDLnBGk3T2peZ4D5";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httPost = new HttpPost(url);

        List params = new ArrayList();
        // 您需要登录SendCloud创建API_USER，使用API_USER和API_KEY才可以进行邮件的发送。
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("from", "xxx@cW7EedTBZKptHOSQrfdDOzAYTMh3Jyw0.sendcloud.org"));//发信域名
        params.add(new BasicNameValuePair("fromName", ""));
        params.add(new BasicNameValuePair("to", user.getEmail()));//收件人
        params.add(new BasicNameValuePair("subject", "我是验证码"));//我是标题
//        params.add(new BasicNameValuePair("html", "test_template"));
//            params.add(new BasicNameValuePair("t"))
        String code=String.valueOf(100000 + (int) (Math.random() * 999999));
        params.add(new BasicNameValuePair("plain", "您的验证码为"+code));//模板

        httPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        // 请求
        HttpResponse response = httpclient.execute(httPost);
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            // 读取xml文档
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } else {
            System.err.println("error");
        }
        httPost.releaseConnection();
        return code;
    }

    public static void main(String[] args) throws IOException {
        User user =new User();
        user.setEmail("812497683@qq.com");
        System.out.println(Email.emailCode(user));
    }

}
