package com.cmz.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cmz.constant.SystemConstant;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月18日 下午4:32:24
 * @description 项目相关配置类
 */
@Component
public class AppConfig {
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(SystemConstant.CMZ_TEST_QUEUE);
	}
	
}
