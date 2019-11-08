package com.cmz.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月22日 下午10:27:10
 * @description 延时投递示例
 */
public class JmsProducerDelaySendDemo {

	public static final String ACTIVEMQ_URL = "tcp://10.0.30.91:61616";
	
	public static final String QUEUE_NAME = "queue-delay-send";

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		Connection connection = activeMQConnectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(QUEUE_NAME);
		MessageProducer producer = session.createProducer(queue);
		// 设置延时投递
		long delay = 3 * 1000;// 延时投递的时间
		long period = 4 * 1000;// 重复投递的间隔时间
		int repeat = 5;// 重复5次
		
		TextMessage message = null;
		for (int i = 0; i < 3; i++) {
			message = session.createTextMessage("message --- " + i);
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
			message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
			producer.send(message);
		}
		producer.close();
		session.close();
		connection.close();
		System.out.println("all message send to queue finished.");
	}

}
