package com.zhuoyao.scanyaoling;

import com.alibaba.fastjson.JSONObject;
import com.zhuoyao.vo.WssVo;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class client {
    public static void main(String[] args) {
        try {
            /**
             * js中的加密过程
             * var e = function(e) {
             *     for (var n = new ArrayBuffer(2 * e.length), r = new Uint16Array(n), t = 0, o = e.length; t < o; t++) r[t] = e.charCodeAt(t);
             *     return r;
             * }, n = function(n) {
             *     var r = e(JSON.stringify(n)), t = r.length, o = new ArrayBuffer(4);
             *     new DataView(o).setUint32(0, t);
             *     var s = new Uint8Array(4 + t);
             *     return s.set(new Uint8Array(o), 0), s.set(r, 4), console.log(s, s.buffer, t), s.buffer;
             * };
             */
            
            // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@ticker";
//            String url = "wss://stream.binance.com:9443/ws/ethbtc@depth20";
            String url = "wss://publicld.gwgo.qq.com?account_value=0&account_type=0&appid=0&token=0";
            URI uri = new URI(url);
            String currentversion="sprite_0e4ebf1344bf35582f7504ee265f32eb.json";
            String version="v0.9.512.2316";

            WssVo wv=new WssVo();
            wv.setRequest_type("1001");
            wv.setLongtitude("39.954474285439886");
            wv.setLatitude("116.32746934890747");
            wv.setRequestid("1001");
            wv.setPlatform("0");
            WebSocketClient mWs = new WebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {
                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {

                }

                @Override
                public void onError(Exception e) {

                }
            };
            mWs.connect();

            Thread.sleep(5000);

                System.out.println("haha");
            mWs.send("123");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
