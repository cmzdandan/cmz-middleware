package com.cmz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年7月11日 下午7:27:12
 * @description zookeeper demo
 */
public class CuratorDemo {

	private static final String CONNECTION_STR = "10.0.30.91:2181";

	private static final String NAMESPACE = "/com.cmz.test";

	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR)
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace(NAMESPACE).build();
		curatorFramework.start(); // 启动
		// 调用增删改查方法进行业务操作
	}

	// 增
	private static void createData(CuratorFramework curatorFramework) throws Exception {
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/data/program",
				"test".getBytes());
	}

	// 删
	private static void deleteData(CuratorFramework curatorFramework) throws Exception {
		Stat stat = new Stat();
		String value = new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
		curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
	}

	// 改
	private static void updateData(CuratorFramework curatorFramework) throws Exception {
		curatorFramework.setData().forPath("/data/program", "up".getBytes());

	}

	// 查
	private static String getDate(CuratorFramework curatorFramework) throws Exception {
		return new String(curatorFramework.getData().forPath("/data/program"), "UTF-8");
	}

}
