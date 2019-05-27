//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhuoyao.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import com.zhuoyao.limit.LimitQuery;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HttpUtil;
import util.YaolingUtil;

public class Listener extends IcqListener {
    String city = "";
    String yaoling = "";
    static String yaolingName = "";
    Map<String, String> m = new HashMap();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
    String [] citys={"上海","大连","成都","深圳","西安","北京","杭州","泉州","南京","梅州","武汉","广州","厦门","拉萨","福州","郑州","温州","重庆","长春"};
    public Listener() {
    }

    @EventHandler
    public void onPMEvent(EventGroupMessage event) {
        System.out.println("接到消息");

        try {
            if (event.getMessage().trim().startsWith("##")&&!event.getMessage().trim().startsWith("##启动")) {
                event.respond("hey " + event.getSender().getInfo().getNickname() + " Start executing your orders");
                String qq = String.valueOf(event.getSender().getId());

                if (!"1215708294".equals(qq) && !"1157071001".equals(qq)) {
                    if (LimitQuery.limits.get(String.valueOf(event.getSender().getId())) != null) {
                        Long timestampp = (Long)LimitQuery.limits.get(String.valueOf(event.getSender().getId()));
                        Long diff = ((new Date()).getTime() - timestampp) / 1000L;
                        if (diff > 900L) {
                            LimitQuery.limits.put(String.valueOf(event.getSender().getId()), (new Date()).getTime());
                            this.domessage(event);
                        } else {
                            event.respond(event.getSender().getInfo().getNickname()+"你距离上一次查询的时间太近了，请在" + (900L - diff) + "秒后再试");
                        }
                    } else {
                        LimitQuery.limits.put(String.valueOf(event.getSender().getId()), (new Date()).getTime());
                        this.domessage(event);
                    }
                } else {
                    System.out.println("测试人员请求中，无限制。");
                    this.domessage(event);
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public  void domessage(EventGroupMessage event) {
        String mess = event.getMessage().trim();
        String nojing = mess.substring(2);
        String[] commond = nojing.split("，");
        yaolingName = commond[1];
        this.yaoling = (String)YaolingUtil.yaolings.get(commond[1]);
        this.city = commond[0];
        Long count= Arrays.stream(citys).filter((c)->c.equals(city)).count();
        if(count!=1) {
            event.respond("你输入的地区有误。");
            return;
        }
        this.m.put("yao_id", this.yaoling);
        this.m.put("city", this.city);
        String html = HttpUtil.doPost("https://www.51yoli.com/air", this.m);
        String result = this.parseHtml(html);
        event.getHttpApi().sendPrivateMsg(event.getSender().getId(), result);
        event.respond("hey " + event.getSender().getInfo().getNickname() + " Your order has been completed.Please check the Private chat message！");
    }

    public String parseHtml(String h) {
        StringBuilder result = new StringBuilder();
        Document containerDoc = Jsoup.parse(h);
        Elements map_table_data = containerDoc.getElementsByClass("map_table_data");
        if (map_table_data.size() == 0) {
            String beforeHtml = HttpUtil.doget();
            Document beforeDoc = Jsoup.parse(beforeHtml);
            String csrf = beforeDoc.select("meta[name='csrf-token']").attr("content");
            HttpUtil.csrf = csrf;
            String html = HttpUtil.doPost("https://www.51yoli.com/air", this.m);
            result.append(this.parseHtml(html));
        } else {
            Elements module = containerDoc.getElementsByClass("map_table_data");
            Elements clearfix = module.select("tr");
            clearfix.select("td");
            Iterator var14 = clearfix.iterator();
            result.append(this.yaolingName+":"+System.getProperty("line.separator"));
            while(var14.hasNext()) {
                Element clearfixli = (Element)var14.next();
                Elements tds = clearfixli.select("td");
//                String name = ((Element)tds.get(1)).text(); //妖灵name
                String zuobiao = ((Element)tds.get(4)).text().replace("复制 反复制 经度 纬度", "").replace("有礼管家公众号","");
                result.append(zuobiao + System.getProperty("line.separator"));
            }

            System.out.println("ok");
        }

        return result.toString();
    }
}
