package com.cmz.boot.activemq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cmz.ActivemqApplication;
import com.cmz.producer.Producer;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月18日 下午5:09:01
 * @description 单元测试类
 */
@SpringBootTest(classes = ActivemqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {

	@Resource
	private Producer produceQueue;
	
	@Test
	public void testSend() throws Exception {
		produceQueue.produceMessage();
	}
	
}
