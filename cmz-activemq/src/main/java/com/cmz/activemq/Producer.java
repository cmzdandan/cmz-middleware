package com.cmz.activemq;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年6月25日 下午1:56:14
 * @description activemq-producer
 */
public class Producer {

	private static final String ACTIVEMQ_URL = "tcp://10.0.30.91:61616";
	private static final String QUEUE_NAME = "cmz.test.queue";
	
	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		// 创建连接并开始连接
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建目的地(队列)
		Queue queue = session.createQueue(QUEUE_NAME);
		// 创建生产者
		MessageProducer producer = session.createProducer(queue);
		for (int i = 0; i < 3; i++) {
			// 创建消息
			TextMessage textMessage = session.createTextMessage("message-" + i);
			// 发送消息
			producer.send(textMessage);
		}
		// 关闭各种连接
		producer.close();
		session.close();
		connection.close();
		System.out.println("消息发送完成");
	}

}
