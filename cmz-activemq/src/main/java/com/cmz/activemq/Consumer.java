package com.cmz.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年6月25日 下午2:24:53
 * @description activemq-consumer
 */
public class Consumer {

	private static final String ACTIVEMQ_URL = "tcp://10.0.30.91:61616";
	private static final String QUEUE_NAME = "cmz.test.queue";
	
	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUE_NAME);
		MessageConsumer consumer = session.createConsumer(queue);
		// 这种 consumer.receive() 是阻塞方式接收消息
		/*
		while(true) {
			TextMessage textMessage = (TextMessage) consumer.receive();
			if(null != textMessage) {
				System.out.println(textMessage.getText());
			} else {
				break;
			}
		}
		*/
		// 这种设置监听器的方式是非阻塞的
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if(message != null && message instanceof TextMessage) {
					try {
						TextMessage textMessage = (TextMessage) message;
						System.out.println(textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
		// 这种监听的方式，需要给监听留一点时间，否则还没有来得及监听，连接就关闭了
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}

}
