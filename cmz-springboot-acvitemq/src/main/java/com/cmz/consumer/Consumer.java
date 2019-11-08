package com.cmz.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cmz.constant.SystemConstant;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 上午11:48:29
 * @description 消费者
 */
@Component
public class Consumer {
	
	@JmsListener(destination = SystemConstant.CMZ_TEST_QUEUE)
	public void handleMessage(String message) {
		System.out.println("收到消息：" + message);
	}
	
}
