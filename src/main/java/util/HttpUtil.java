package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    private static String charset = "utf-8";
    private static HttpClient httpClient = HttpClients.createDefault();
    public static String csrf="";//全局crsf用来躲过跨站攻击防御

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String doPost(String url, Map<String, String> map) {
        HttpPost httpPost = null;
        String result = null;
        try {
            httpPost = new HttpPost(url);
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
//            httpPost.setHeader("Host", "www.51yoli.com");
            httpPost.setHeader("User-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0");
            httpPost.setHeader("Accept","atext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpPost.setHeader("Accept-Encoding","gzip, deflate, br");
//            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.setHeader("X-CSRF-TOKEN",csrf);
//            httpPost.setHeader("referer","https://www.51yoli.com/game?yao_id=2000199&city=%E5%8C%97%E4%BA%AC");
//            httpPost.setHeader("X-Requested-Wit","XMLHttpRequest");
            httpPost.setHeader("Cookie"," UM_distinctid=16aea71b14e2c2-0c62c1eb3f1bc8-5d4e211f-1fa400-16aea71b14fed0; CNZZDATA1277639987=227364865-1558706696-%7C1558706696; Hm_lvt_6c9e6e168b46aaa118c7ed52e9a02f43=1558711487; Hm_lpvt_6c9e6e168b46aaa118c7ed52e9a02f43=1558711487; XSRF-TOKEN=eyJpdiI6IlFkUGdvS2l6UkVpb0ZBV053YURYUVE9PSIsInZhbHVlIjoicHNOTVwvUjVCeVhIUTl3MkNkVEF6TWNiamgxVDdwOFN5WUxrN1NnemtvWERKNitSMXFJY04yWVhKSmNoV2FWRXJJZ3I3ZzhzTWNORXdMa1wvWlJ3ZittQT09IiwibWFjIjoiNjgwNTc1M2M1ZWZmODU1NDk5ZWY2YzU2NDMwM2U4ZjViY2U5YmJjZjMwNGQ3NTBiMWVmOGZmMTkwNTM3NDY0MiJ9; 51YOLI-SID=eyJpdiI6InRWTU12XC83K0d6NVNwd01nWDZqXC9HUT09IiwidmFsdWUiOiI3N2pzYmphaG1MU2d3UjhCT3ZLSDFoUmRBclNmYjRxTEtycm53YmFuY21KbDhPZUVaSWxsXC92NjY2bWFpdEUraU9qS25URHNoTFRVSU5NVEF5ZE54Snc9PSIsIm1hYyI6IjlhZTI3M2YyZWMzZGNmNDk4YWNmZjU3Y2M1MmJhODExODY2NTNjODU4YTRkZDI1Y2IwNWQzN2UzZDY2ZTM0MDcifQ%3D%3D");
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    public  static String doget(){
        String result="";
        try{
            HttpGet get=new HttpGet("https://www.51yoli.com");
            HttpResponse response = httpClient.execute(get);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
//            System.out.println("123465");
//            System.out.println("123465");
//            System.out.println("123465");
        }catch (Exception e){
e.printStackTrace();
        }
return result;

    }

}

