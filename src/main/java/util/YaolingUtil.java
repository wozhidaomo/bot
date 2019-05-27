package util;

import cc.moecraft.utils.FileUtils;
import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class YaolingUtil {
    public static Map<String,String> yaolings=new HashMap<>();
    public static void initYaolings(){
        try{

            File filePath = new File("/ztf/bot/data.json");
            String input = FileUtils.readFileAsString(filePath);
            JSONObject jsonObject= JSON.parseObject(input);
            JSONArray dataArray=jsonObject.getJSONArray("Data");
            Iterator<Object> yaolingInfo = dataArray.iterator();
            while (yaolingInfo.hasNext()){
                JSONObject yaoling = (JSONObject) yaolingInfo.next();
                yaolings.put((String)yaoling.get("Name"),String.valueOf(yaoling.get("Id")));
            }
        }catch (Exception e){e.printStackTrace();}


    }
}
