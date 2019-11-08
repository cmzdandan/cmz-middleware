package com.cmz.consumer;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cmz.constant.SystemConstant;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月3日 下午9:07:24
 * @description 消费者
 */
@Component
public class UserLogConsumer {

	private static final Logger logger = LoggerFactory.getLogger(UserLogConsumer.class);

	@KafkaListener(topics = { SystemConstant.USER_LOG_TOPIC })
	public void consumer(ConsumerRecord<?, ?> consumerRecord) {
		// 判断是否为null
		Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
		logger.info("recieve record =" + kafkaMessage);
		if (kafkaMessage.isPresent()) {
			// 得到Optional实例中的值
			Object message = kafkaMessage.get();
			System.err.println("消费消息:" + message);
		}
	}

}
