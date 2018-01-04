package com.wu.general.utils;


import java.io.Serializable;

/**
 * 固定容量Queue
 *
 * @author zlei
 */
public class FixedQueue<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private final T[] elements;

	private int h; // head index

	private int t; // tail index

	private int size;


	public FixedQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);

		elements = (T[]) new Object[capacity];
	}

	public T offer(T e) {
		T r = null;

		if (size == elements.length) { // queue full
			r = elements[h++];

			if (h >= elements.length)
				h = 0;
		} else
			size++;

		elements[t++] = e;

		if (t >= elements.length)
			t = 0;

		return r;
	}

	public T poll() {
		if (size > 0) {
			T e = elements[h++];

			if (h >= elements.length)
				h = 0;

			size--;

			return e;
		} else
			return null;
	}

	public T peek() {
		return size > 0 ? elements[h] : null;
	}

	public T tail() {
		return size > 0 ? elements[t] : null;
	}

	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}

		h = 0;
		t = 0;
		size = 0;
	}

	public int size() {
		return size;
	}

	public int capacity() {
		return elements.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == elements.length;
	}

}
