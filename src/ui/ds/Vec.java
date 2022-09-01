package ui.ds;

import java.util.Arrays;

public class Vec<T> {
	int capacity;
	private Object[] arr;
	private Object[] draft;
	private int len2 = 0;
	private int len = 0;

	public Vec(int capacity) {
		this.capacity = capacity;
		draft = new Object[capacity];
		arr = new Object[capacity];
	}

	public void querry(T value) {
		if (len > 0) {
			if (!peek().equals(value))
				push(value);
		} else {
			push(value);
		}
	}

	public void push(T value) {
		if (len == capacity) {
			drop();
		}

		arr[len] = value;
		len++;

		len2 = 0;
		draft = new Object[capacity];
	}

	public int len() {
		return len;
	}

	public int len2() {
		return len2;
	}

	public void undo() {
		if (len > 1) {
			draft[len2] = pop();
			len2++;
		}
	}

	public void redo() {
		if (len2 > 0) {
			len2 -= 1;
			arr[len] = draft[len2];
			len += 1;
			draft[len2] = null;
		}
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		if (len == 0)
			throw new IndexOutOfBoundsException();
		return (T) arr[len - 1];
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		len -= 1;
		T val = (T) arr[len];
		arr[len] = null;
		return val;
	}

	public void drop() {
		Object[] temp = new Object[capacity];

		System.arraycopy(arr, 1, temp, 0, len - 1);

		arr = temp;

		len -= 1;
	}

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
}
