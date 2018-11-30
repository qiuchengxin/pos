package com.market.pos;

import com.market.pos.service.LemocWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
        SpringApplication.run(PosApplication.class, args);
        start();
    }
}
