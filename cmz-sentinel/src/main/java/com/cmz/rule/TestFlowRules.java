package com.cmz.rule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年9月4日 下午5:45:46
 * @description 为test这个资源创建规则
 */
@Component
public class TestFlowRules {

	public static void initFlowRules(){
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		rule.setResource("test");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20
		rule.setCount(20);
	    rules.add(rule);
	    FlowRuleManager.loadRules(rules);
	}
	
}
