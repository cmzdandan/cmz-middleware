package com.cmz.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 下午6:01:18
 * @description Sentinel Exception Util
 */
public final class ExceptionUtil {

	public static void handleException(BlockException ex) {
		// Handler method that handles BlockException when blocked.
		// The method parameter list should match original method, with the last additional
		// parameter with type BlockException. The return type should be same as the original method.
		// The block handler method should be located in the same class with original method by default.
		// If you want to use method in other classes, you can set the blockHandlerClass 
		// with corresponding Class (Note the method in other classes must be static).
		System.out.println("Oops: " + ex.getClass().getCanonicalName());
	}

}
