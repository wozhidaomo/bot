package com.zhuoyao;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import com.zhuoyao.listener.Listener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
@MapperScan("com.zhuoyao.mapper")
public class BotApplication {

static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);


    public static void main(String[] args)
    {
        SpringApplication.run(BotApplication.class, args);

        // 创建机器人对象 ( 传入配置 )
//        PicqBotX bot = new PicqBotX(new PicqConfig(2080).setDebug(false));
//
//        // 添加一个机器人账户 ( 名字, 发送URL, 发送端口 )
//        bot.addAccount("11", "127.0.0.1", 5700);
////        bot.addAccount("Bot01", "127.0.0.1", 31091);
//
//        // 启用HyExp ( 非必要 )
////        bot.setUniversalHyExpSupport(true);
//
//        // 设置异步
//        bot.getConfig().setUseAsyncCommands(true);
//        // 注册事件监听器, 可以注册多个监听器
//        bot.getEventManager().registerListeners(new Listener());
//
//        // 在没有Debug的时候加上这个消息日志监听器
//        if (!bot.getConfig().isDebug())
////            bot.getEventManager().registerListener(new SimpleTextLoggingListener());
//
//        // 启用指令管理器
//        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
////        bot.enableCommandManager("bot -", "!", "/", "~", "！", "我以令咒命之，", "我以令咒命之, ", "test -");
//
//        // 注册指令
//        // 从 v3.0.1.730 之后不会自动注册指令了, 因为效率太低 (≈4000ms), 而且在其他框架上有Bug
////        bot.getCommandManager().registerCommands(commands);
//
//        // Debug输出所有已注册的指令
////        bot.getLogger().debug(bot.getCommandManager().getCommands().toString());
//
//        // 启动机器人, 不会占用主线程
//        bot.startBot();
//        System.out.println("start begin");
//        System.out.println(bot.getAccountManager().getNonAccountSpecifiedApi().canSendImage());
////        ComponentImage img= new ComponentImage("data\\image\\FCD53D272D102C59B79528D21D739FB1.jpg.cqimg");
////        Timer timer=new Timer();
////        timer.schedule(new TimerTask() {
////            @Override
////            public void run() {
////                bot.getAccountManager().getAccounts().get(0).getHttpApi().sendGroupMsg(684898885,"该喝水了大家！");
////                 bot.getAccountManager().getAccounts().get(0).getHttpApi().sendGroupMsg(684898885,new ComponentImage("hs.png").toString(),false);
////            }
////        },0,3600000);
//
//        System.out.println("start end");
    }
}
