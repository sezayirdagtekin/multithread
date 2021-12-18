package com.blokingque.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueData {

	private static BlockingQueue queue = new ArrayBlockingQueue<>(50);

	public static void take() throws InterruptedException {
		queue.take();
	}
	
	public static void put(String value) throws InterruptedException  {
		queue.put(value);
	}

}
