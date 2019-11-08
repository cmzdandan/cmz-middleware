package com.cmz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.service.DemoService;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 下午5:13:23
 * @description 测试实例Controller入口
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		System.out.println("This is a demo for test.");
		return demoService.test();
	}
	
}
