package com.wu.general.foundation.service.impl.shiroServiceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import com.wu.general.foundation.service.shiroService.ClientService;
import com.wu.general.utils.FixedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 检查用户登录异常状态服务实现
 *
 * @author zlei
 */
@Service
public class ClientServiceImpl implements ClientService {
	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final ConcurrentMap<String, FixedQueue<Long>> hostFailMap = new ConcurrentHashMap<>();

	private volatile Thread cleanThread;


	// TODO: 从配置文件里面读取 by hzl 2016/3/3
	private int maxFailCount = 3; // 每个client允许最多登录失败次数

	// TODO: 从配置文件里面读取 by hzl 2016/3/3
	private long maxFailInterval = 60; // 每个client允许最多登录失败次数间隔时间, second

	// TODO: 从配置文件里面读取 by hzl 2016/3/3
	private int invalidateTtl = 180; // 失效间隔, second

	// TODO: 从配置文件里面读取 by hzl 2016/3/3
	private int cleanTtl = 31; // 清理线程等待间隔, second


	public int getClientLoginFailCount(String host) {
		FixedQueue<Long> queue = hostFailMap.get(host);

		if (queue != null) {
			synchronized (queue) {
				return queue.size();
			}
		}

		return 0;
	}

	public void increaseClientLoginFailCount(String host) {
		ConcurrentMap<String, FixedQueue<Long>> map = hostFailMap;

		FixedQueue<Long> queue = map.get(host);
		if (queue == null) {
			synchronized (map) {
				queue = map.get(host);

				if (queue == null) {
					queue = new FixedQueue<>(maxFailCount);

					map.put(host, queue);
				}
			}
		}

		synchronized (queue) {
			queue.offer(new Date().getTime());
		}
	}

	public boolean isExcessiveLoginAttempt(String host) {
		FixedQueue<Long> queue = hostFailMap.get(host);

		if (queue != null) {

			Long p1 = null;
			Long p2 = null;

			synchronized (queue) {
				if (queue.isFull()) {
					p1 = queue.tail();
					p2 = queue.peek();
				}
			}

			if (p1 != null && p2 != null && p1 - p2 < maxFailInterval * 1000)
				return true;
		}

		return false;
	}

	@PostConstruct
	public void init() {
		cleanThread = new Thread() {
			@Override
			public void run() {
				boolean stop = false;
				while (!stop) {
					try {
						sleep(cleanTtl * 1000);

						doCleanup();

					} catch (InterruptedException e) {
						stop = true;
					}
				}
			}

			private void doCleanup() {
				ConcurrentMap<String, FixedQueue<Long>> map = hostFailMap;

				for (Iterator<Map.Entry<String, FixedQueue<Long>>> it = map.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, FixedQueue<Long>> entry = it.next();
					FixedQueue<Long> queue = entry.getValue();

					Long lastFail;
					synchronized (queue) {
						lastFail = queue.tail();
					}

					if (lastFail != null && (new Date().getTime() - lastFail > invalidateTtl * 1000)) {
						it.remove();

						synchronized (queue) {
							queue.clear(); // help GC
						}
					}
				}
			}
		};

		cleanThread.setName("client-service-cleanThread");
		cleanThread.setDaemon(true);
		cleanThread.start();

		log.info("{} started", cleanThread.getName());
	}

	@PreDestroy
	public void destory() {
		while (cleanThread.isAlive()) {
			cleanThread.interrupt();

			log.info("wait {} to stop...", cleanThread.getName());
			try {
				cleanThread.join(1000);
			} catch (InterruptedException ignore) {
			}
		}

		log.info("{} stopped", cleanThread.getName());

		hostFailMap.clear();
		cleanThread = null;
	}

}
