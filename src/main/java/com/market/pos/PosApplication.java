package com.market.pos;

import com.market.pos.tool.goldPriceSearch.GoldPriceSearch;
import com.market.pos.tool.lemoc.LemocWebSocketClientFactory;
import com.market.pos.tool.timeTask.TimeTask;
import org.java_websocket.client.WebSocketClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.market.pos.mapper")
@EnableCaching
public class PosApplication {

    public static void start()throws Exception {
        WebSocketClient lemocWebSocketClient = LemocWebSocketClientFactory.getLemocWebSocketClient();
        lemocWebSocketClient.connect();
    }

    public static void main(String[] args) throws Exception {
        // 启动spring-boot的主程序
        SpringApplication.run(PosApplication.class, args);
        //启动QQ转发中间件Lemoc
        start();
        //启动定时任务
//        TimeTask.timeTask();
    }
}
