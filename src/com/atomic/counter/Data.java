package com.atomic.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Data {

	private static AtomicInteger count = new AtomicInteger(0);

	public static void increment() {
		count.incrementAndGet();
	}

	public static void decrement() {
		count.decrementAndGet();
	}
	
	public static int getCount() {
		return count.get();
	}
}
