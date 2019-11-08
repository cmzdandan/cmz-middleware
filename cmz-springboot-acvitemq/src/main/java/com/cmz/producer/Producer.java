package com.cmz.producer;

import java.util.UUID;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月18日 下午4:33:02
 * @description 生产者
 */
@Component
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	@Scheduled(fixedDelay = 3000)
	public void produceMessage() {
		jmsMessagingTemplate.convertAndSend(queue, UUID.randomUUID().toString().substring(0, 10));
	}
	
}
