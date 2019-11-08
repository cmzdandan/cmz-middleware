package com.cmz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月18日 下午4:31:12
 * @description SpringBoot启动类
 */
@SpringBootApplication
@EnableScheduling // 启用定时调度
@EnableJms // 启动消息队列
public class ActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqApplication.class, args);
	}

}
