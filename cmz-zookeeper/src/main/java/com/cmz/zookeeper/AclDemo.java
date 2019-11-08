package com.cmz.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * @author chen.mz
 * @email 1034667543@qq.com
 * @create 2019年7月11日 下午7:58:40
 * @description zookeeper 带权限的操作示例
 */
public class AclDemo {

	private static String CONNECTION_STR = "10.0.30.91:2181";

	public static void main(String[] args) throws Exception {
		demo();
	}

	private static void demo() throws Exception {
		//
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR)
				.sessionTimeoutMs(5000).authorization("digest", "admin:admin".getBytes())
				.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		List<ACL> list = new ArrayList<>();
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE,
				new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
		list.add(acl);
		curatorFramework.setACL().withACL(ZooDefs.Ids.CREATOR_ALL_ACL).forPath("/temp");

		// 设置权限集
		List<ACL> acls = new ArrayList<>();
		Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("u1:us"));
		Id id2 = new Id("digest", DigestAuthenticationProvider.generateDigest("u2:us"));
		acls.add(new ACL(ZooDefs.Perms.ALL, id1)); // 针对u1，有 read 权限， 针对 u2 有读和删除权限
		acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.READ, id2));
		// 创建带权限的节点
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(acls, false)
				.forPath("/auth", "sc".getBytes());

		// 访问授权节点
		// 创建权限集合
		List<AuthInfo> authInfos = new ArrayList<>();
		AuthInfo authInfo = new AuthInfo("digest", "u1:us".getBytes());
		authInfos.add(authInfo);
		// 创建授权的连接
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.13.102:2181")
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).authorization(authInfos)
				.namespace("curator").build();
		// 开启
		client.start();
		// 访问节点数据
		String result = new String(client.getData().forPath("/auth"), "UTF-8");
		System.out.println(result);
	}

}
