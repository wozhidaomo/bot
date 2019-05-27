package com.zhuoyao.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HttpUtil;
import util.YaolingUtil;

import java.util.*;

public class AutoSendGroup extends IcqListener {
    String city = "";
    String yaoling = "";
    static String yaolingName = "";
    Map<String, String> m = new HashMap();
    public Timer timer = new Timer();
    Stack<String> messageStack=new Stack<>();//message stack
    String [] citys={"上海","大连","成都","深圳","西安","北京","杭州","泉州","南京","梅州","武汉","广州","厦门","拉萨","福州","郑州","温州","重庆","长春"};


    @EventHandler
    public void sendByCode(EventGroupMessage event){

//        if (event.getMessage().trim().startsWith("##")&&"513792267".equals(event.getSender().getId())&&"717040834".equals(event.groupId)) {
        Long count=Arrays.stream(citys).filter((c)->c.equals("北京")).count();//暂时先不验证
//        timer.cancel();
        if (event.getMessage().trim().startsWith("##启动")) {
            System.out.println("启动自动输出坐标功能");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!messageStack.empty()){
                        event.respond(messageStack.pop());
                    }else{
                        domessage(event);
                    }
                }
            },0,10000);



        }else if(event.getMessage().trim().startsWith("##关闭")){
            timer.cancel();

        }

    }


    public  void domessage(EventGroupMessage event) {
        String mess = event.getMessage().trim();
        String nojing = mess.substring(2);
        String[] commond = nojing.split("，");
        yaolingName = commond[1];
        this.yaoling = (String)YaolingUtil.yaolings.get(commond[1]);
        this.city = citys[new Random().nextInt(10)+1];//随机取出地区
        this.m.put("yao_id", this.yaoling);
        this.m.put("city", this.city);
        String html = HttpUtil.doPost("https://www.51yoli.com/air", this.m);
        this.parseHtmlAndSave(html);
//        event.getHttpApi().sendPrivateMsg(event.getSender().getId(), result);
//        event.respond("hey " + event.getSender().getInfo().getNickname() + " Your order has been completed.Please check the Private chat message！");
    }

    public void parseHtmlAndSave(String h) {
        StringBuilder result = new StringBuilder();
        Document containerDoc = Jsoup.parse(h);
        Elements map_table_data = containerDoc.getElementsByClass("map_table_data");
        if (map_table_data.size() == 0) {
            String beforeHtml = HttpUtil.doget();
            Document beforeDoc = Jsoup.parse(beforeHtml);
            String csrf = beforeDoc.select("meta[name='csrf-token']").attr("content");
            HttpUtil.csrf = csrf;
            String html = HttpUtil.doPost("https://www.51yoli.com/air", this.m);
            this.parseHtmlAndSave(html);
        } else {
            Elements module = containerDoc.getElementsByClass("map_table_data");
            Elements clearfix = module.select("tr");
            clearfix.select("td");
            Iterator var14 = clearfix.iterator();
//            result.append(this.yaolingName+":"+System.getProperty("line.separator"));
            while(var14.hasNext()) {
                Element clearfixli = (Element)var14.next();
                Elements tds = clearfixli.select("td");
//                String name = ((Element)tds.get(1)).text(); //妖灵name
                String zuobiao = ((Element)tds.get(4)).text().replace("复制 反复制 经度 纬度", "").replace("有礼管家公众号","");
//                result.append(zuobiao);
                messageStack.push(zuobiao);
            }

//            System.out.println("ok");
        }

//        return result.toString();
    }
}
