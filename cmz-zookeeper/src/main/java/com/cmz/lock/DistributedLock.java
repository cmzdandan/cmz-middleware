package com.cmz.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年7月30日 下午6:34:22
 * @description zookeeper分布式锁
 */
public class DistributedLock {

	private static final String CONNECTION_STR = "10.0.30.91:2181";

	public static void main(String[] args) {
		CuratorFramework curatorFramework = null;
		curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR).sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
		curatorFramework.start();
		final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "-> 尝试获取锁");
				try {
					lock.acquire();
					System.out.println(Thread.currentThread().getName()+"-> 获得锁成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(4000);
					lock.release();
					System.out.println(Thread.currentThread().getName()+"-> 释放锁成功");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}, "Thread-" + i).start();
		}
	}

}
