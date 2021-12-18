package com.blokingque.producerconsumer;

import java.util.concurrent.Callable;

class Consumer implements Callable<String> {

	@Override
	public String call() throws Exception {

		int count = 0;
		while (count < 50) {
			BlockingQueueData.take();
			count++;
		}
		return "Consumed:" + count;
	}
}
