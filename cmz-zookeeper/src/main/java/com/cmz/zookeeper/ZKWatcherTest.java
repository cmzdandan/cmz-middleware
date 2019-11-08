package com.cmz.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年8月9日 上午11:04:30
 * @description 注册监听
 */
public class ZKWatcherTest {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zookeeper = new ZooKeeper("10.0.30.91:2181", 1000, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println("event.type" + event.getType());
			}
		});
		// 创建节点
		zookeeper.create("/watch", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 注册监听
		zookeeper.exists("/watch", true);
		Thread.sleep(1000);
		// 修改节点的值触发监听
		zookeeper.setData("/watch", "1".getBytes(), -1);
		System.in.read();
		zookeeper.close();
	}

}
