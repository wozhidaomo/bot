package com.zhuoyao.scanyaoling;

import com.alibaba.fastjson.JSONObject;
import com.zhuoyao.vo.WssVo;
import org.apache.commons.lang.ArrayUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class client {
    public static void main(String[] args) {
        try {
            /**
             *
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
//            String currentversion = "sprite_0e4ebf1344bf35582f7504ee265f32eb.json";
//            String version = "v0.9.512.2316";
//
            WssVo wv = new WssVo();
            wv.setRequest_type("1001");
            wv.setLongtitude("39.954474285439886");
            wv.setLatitude("116.32746934890747");
            wv.setRequestid("1001");
            wv.setPlatform("0");
            System.out.println(JSONObject.toJSONString(wv));
            System.out.println(JSONObject.toJSONString(wv).length());
            WebSocketClient mWs = new WebSocketClient(uri) {
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
//            mWs.connect();
//
//            Thread.sleep(5000);
//            ScriptEngineManager sem = new ScriptEngineManager();
//            ScriptEngine engine = sem.getEngineByName("js");
//            String js;
//
//
//            js = "var names = [\"john\", \"jerry\", \"bob\"]\n";
//            js += "var a = names[0];\n";
//            js += "print(a)";
//
//            engine.eval(js);

//            String js2 = "";
//            engine.eval(js2);
//            String jsName = "F:\\ideaProject\\bot\\src\\main\\java\\com\\zhuoyao\\scanyaoling\\EncryptMessage.js";
////读取js
//            FileReader fileReader = new FileReader(jsName);
////执行指定脚本
//            engine.eval(fileReader);
//            if (engine instanceof Invocable) {
//                Invocable in = (Invocable) engine;
//
////                System.out.println(in.invokeFunction("json2buffer", JSONObject.toJSONString(wv),JSONObject.toJSONString(wv).length()));
////                Object o=in.invokeFunction("json2buffer", JSONObject.toJSONString(wv),JSONObject.toJSONString(wv).length());
//                System.out.println("11111");
//                System.out.println("11111");
//                System.out.println("11111");
//                byte[] bb=JSONObject.toJSONString(wv).getBytes();
//                byte[] bbb={0,0,0,123};
//                byte[] result=ArrayUtils.addAll(bbb,bb);
////                byte[] aa=(byte[])(in.invokeFunction("json2buffer", JSONObject.toJSONString(wv),JSONObject.toJSONString(wv).length()));
//                mWs.send(result);

//            }

//            System.out.println("haha");

//            mWs.send("123");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
