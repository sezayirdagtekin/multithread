package com.producerconsumer;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Consumer implements Callable<String> {

	List<Integer> buffer = BufferedData.getBuffer();
	Lock lock = DataLock.LOCK;
	Condition isEmptyCondition = DataLock.EMPTY_CONDITION;
	Condition isFullCondition = DataLock.FULL_CONDITION;

	@Override
	public String call() throws Exception {

		int count = 0;
		while (count < 50) {
			try {
				lock.lock();
				while (buffer.isEmpty()) {
					System.out.println("Buffer is  empty! size:" + buffer.size());
					// wait
					if (!isEmptyCondition.await(10, TimeUnit.MILLISECONDS)) {
						throw new TimeoutException("Consumer timeout! Maybe there is no data in producer!");
					}
				}
				buffer.remove(buffer.size() - 1);
				System.out.println("One item is removed from buffer");
				// signal

				isFullCondition.signalAll();

			} finally {
				lock.unlock();
			}

			count++;
		}
		return "Consumed:" + count;
	}
}
