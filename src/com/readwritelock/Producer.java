package com.readwritelock;

import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Callable<String> {

	private Random random = new Random();

	@Override
	public String call() throws Exception {

		int count = 0;
		while (count < 1_000) {
			long key = random.nextInt(1_000);
			// Race condition prevented with ReadWriteLock
			CacheMap.put(key, Long.toString(key));
			if (CacheMap.get(key) == null) {
				System.out.println(key + " has not been put in the map!");
			}
			count++;
		}
		return "count is:" + count;
	}

}
