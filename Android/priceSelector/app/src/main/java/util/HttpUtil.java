package util;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient类的get请求，post请求封装
 *
 * Created by Westchennn on 17/3/17.
 */
public class HttpUtil {

    private HttpClient httpClient;

    public HttpUtil(){
        httpClient = new DefaultHttpClient();
    }

    public String get(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == 200){
            return EntityUtils.toString(response.getEntity(),"utf-8");
        }else
            return null;
    }

    public String post(String url, Map<String,String> list){
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paprams = new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : list.entrySet())
            paprams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        try {
            post.setEntity(new UrlEncodedFormEntity(paprams, HTTP.UTF_8));
            HttpResponse response = httpClient.execute(post);
            Log.v("!!!!!!!!!!!!!!","状态码" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity());
            }else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
