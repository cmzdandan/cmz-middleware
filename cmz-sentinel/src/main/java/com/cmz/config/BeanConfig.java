package com.cmz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 下午5:42:18
 * @description 系统的一些Bean配置
 */
@Configuration
public class BeanConfig {

	@Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
	
}
