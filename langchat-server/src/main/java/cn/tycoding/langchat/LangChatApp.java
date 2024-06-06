package cn.tycoding.langchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tycoding
 * @since 2024/2/4
 */
@EnableAsync
@SpringBootApplication
public class LangChatApp {

    public static void main(String[] args) {
        SpringApplication.run(LangChatApp.class, args);
    }
}
