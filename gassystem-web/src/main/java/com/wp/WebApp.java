package com.wp;

import com.wp.websocket.SocketHandler;
import com.wp.websocket.listener.MqttListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by 王萍 on 2017/6/8 0008.
 */
//@Configuration
//@EnableAutoConfiguration
@SpringBootApplication
@EnableWebSocket
public class WebApp extends SpringBootServletInitializer implements WebSocketConfigurer {


    @Autowired
    private SocketHandler socketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(socketHandler, "websocket").withSockJS();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApp.class);
    }

//    @Bean
//    public GasDataUtil getGasDataUtil() {
//        return new GasDataUtil();
//    }

//    @Bean
//    public InfluxTemplate getInfluxdbTemplate() {
//        return new InfluxTemplate();
//    }

//    @Bean
//    public MQTT getMqtt() {
//        return new MQTT();
//    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(WebApp.class);
        //MqttListener为单例
        application.addListeners(MqttListener.getInstance());
        application.run(args);

    }


}
