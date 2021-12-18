
package com.blokingque.producerconsumer;

import java.util.concurrent.Callable;

class Producer implements Callable<String> {

	@Override
	public String call() throws Exception {

		int count = 0;
		while (count < 50) {
			BlockingQueueData.put(String.valueOf(count));
			count++;
		}
		return "Produced:" + count;
	}
}
