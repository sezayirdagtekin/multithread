package com.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheMap {

	private static Map<Long, String> cache = new HashMap<>();

	private static ReadWriteLock lock = new ReentrantReadWriteLock();

	private static Lock readLock = lock.readLock();
	private static Lock writeLock = lock.writeLock();

	/**
	 * Only one thread modify map. During modify none can read it
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String put(Long key, String value) {
		try {
			writeLock.lock();
			return cache.put(key, value);

		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * Many thread can read in parallel
	 * 
	 * @param key
	 * @return
	 */
	public static String get(Long key) {

		try {
			readLock.lock();
			return cache.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public static Map<Long, String> getCache() {
		return cache;
	}

}
