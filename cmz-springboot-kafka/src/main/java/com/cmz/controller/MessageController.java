package com.cmz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.constant.SystemConstant;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月3日 下午9:10:58
 * @description 消息生产入口
 */
@Controller
@RequestMapping("/test")
public class MessageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@ResponseBody
	@RequestMapping("/sendMessage")
	public String sendMessage() {
		ListenableFuture<SendResult<String,String>> listenableFuture = kafkaTemplate.send(SystemConstant.USER_LOG_TOPIC, "cmz-producer", "test send kafka message.");
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String,String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("发送成功，" + result);
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("发送失败啦");
				ex.printStackTrace();
			}
		});
		return "success";
	}

}
