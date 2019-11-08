package com.cmz.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cmz.service.DemoService;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 下午5:30:58
 * @description DemoService实现类
 *              <p>
 *              若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入
 *              blockHandler 处理逻辑。
 *              <p>
 * 				若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将
 *              BlockException 直接抛出。
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Override
	@SentinelResource(value = "hello", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
	public void hello() {
		System.out.println("hello");
	}
	
	/**
	 * 这是原方法，加上Sentinel注解，对这个方法进行限流
	 */
	@Override
	@SentinelResource(value = "test", blockHandler = "exceptionHandler", fallback = "testFallback")
	public String test() {
		return String.valueOf(System.nanoTime());
	}

	/**
	 * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致
	 * 
	 * @param ex
	 * @return
	 */
	public String exceptionHandler(BlockException ex) {
		ex.printStackTrace();
		return "Oops, error occurred";
	}

	/**
	 * Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数
	 * 
	 * @return
	 */
	public String testFallback() {
		return "testFallback";
	}

}
