package com.zhuoyao.scanyaoling;

import com.alibaba.fastjson.JSONObject;
import com.zhuoyao.vo.WssVo;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.nio.ByteBuffer;


public class DataStructure {
    static Session session;
    public static  void main(String[] args){


            String currentversion="sprite_0e4ebf1344bf35582f7504ee265f32eb.json";
            String version="v0.9.512.2316";

            WssVo wv=new WssVo();
            wv.setRequest_type("1001");
            wv.setLongtitude("39.954474285439886");
            wv.setLatitude("116.32746934890747");
            wv.setRequestid("1001");
            wv.setPlatform("0");

//            System.out.println(JSONObject.toJSONString(wv).getBytes());
//            System.out.println("i dont know");
        try {
            String url = "wss://publicld.gwgo.qq.com?account_value=0&account_type=0&appid=0&token=0"; //
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            System.out.println("Connecting to " + url);
            session=container.connectToServer(MyClientEndpoint.class, URI.create(url));
            System.out.println(JSONObject.toJSONString(wv));
            ByteBuffer byteBuffer=ByteBuffer.wrap(JSONObject.toJSONString(wv).getBytes());
            session.getBasicRemote().sendBinary(byteBuffer);


        } catch ( Exception ex) {
            ex.printStackTrace();
        }

    }




    }



