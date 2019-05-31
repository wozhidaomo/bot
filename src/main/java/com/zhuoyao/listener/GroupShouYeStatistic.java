package com.zhuoyao.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.message.components.ComponentImage;
import com.zhuoyao.limit.LimitQuery;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GroupShouYeStatistic extends IcqListener {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @EventHandler
    public void onGroupMessageEvent(EventGroupMessage event) {
        System.out.println("接到消息");

        try {
            Instant now=Instant.now();
            DATE_TIME_FORMATTER.parse(now.toString());
            System.out.println(now);
            if (event.getMessage().trim().startsWith("##")&&!event.getMessage().trim().startsWith("##启动")) {
                event.respond("hey " + event.getSender().getInfo().getNickname() + " Start executing your orders");
                String qq = String.valueOf(event.getSender().getId());


            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void main(String[] args) {


    }
}
