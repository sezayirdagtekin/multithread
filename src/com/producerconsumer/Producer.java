package com.producerconsumer;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Producer implements Callable<String> {
	
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
				while (BufferedData.isFull(buffer)) {
					System.out.println("Buffer is  full size:" + buffer.size());
					// wait
					isFullCondition.await();
				}

				buffer.add(1);
				System.out.println("One item is added to buffer");
				// signal
				isEmptyCondition.signalAll();

				count++;
			} finally {
				lock.unlock();

			}

		}
		return "Produced:" + count;
	}
}