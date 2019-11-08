package com.cmz.activemq;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月22日 下午10:04:58
 * @description 异步消息投递示例
 */
public class JmsProducerAsyncSendDemo {
	
	public static final String ACTIVEMQ_URL = "tcp://10.0.30.91:61616";
	
	public static final String QUEUE_NAME = "queue-async-send";

	public static void main(String[] args) throws JMSException {
		// 创建连接工厂
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		// 设置异步发送
		activeMQConnectionFactory.setUseAsyncSend(true);
		// 创建连接
		Connection connection = activeMQConnectionFactory.createConnection();
		// 开始连接
		connection.start();
		
		// 创建会话(设置是否开启事务，应答模式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建目的地(队列)
		Queue queue = session.createQueue(QUEUE_NAME);
		// 创建生产者[与普通的有区别了，类型变了]
		ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
		TextMessage message = null;
		for (int i = 0; i < 3; i++) {
			message = session.createTextMessage("message --- " + i);
			message.setJMSMessageID(UUID.randomUUID().toString() + "cmz-order");
			String jmsMessageID = message.getJMSMessageID();
			//producer.send(message);
			// 异步发送，接收回调
			producer.send(message, new AsyncCallback() {
				
				@Override
				public void onException(JMSException exception) {
					System.out.println("message send fail, messageID : " + jmsMessageID);
				}
				
				@Override
				public void onSuccess() {
					System.out.println("message send success, messageID : " + jmsMessageID);
				}
			});
		}
		producer.close();
		session.close();
		connection.close();
		System.out.println("all message send to queue finished.");
	}

}
