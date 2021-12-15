package com.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class DataLock {

	 static final Lock LOCK = new ReentrantLock();
	 static final Condition EMPTY_CONDITION = LOCK.newCondition();
	 static final Condition FULL_CONDITION = LOCK.newCondition();


}
