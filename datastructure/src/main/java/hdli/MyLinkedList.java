package hdli;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Description 链表
 * @Author: Lihuaidong
 * @Date: Created at 15:22 2019/6/23
 */
public class MyLinkedList<T> implements Iterable<T> {

	private int theSize;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	private static class Node<T> {

		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}

//		public Node(Node<T> x, Node<T> p, Node<T> n) {
//			data = x.data;
//			prev = p;
//			next = n;
//		}

		public T data;
		public Node<T> prev;
		public Node<T> next;
	}

	public MyLinkedList() {
		clear();
	}

	public void clear() {
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	public boolean add(T x) {
		add(size(), x);
		return true;
	}

	public void add(int dx, T x) {
		addBefore(getNode(dx), x);
	}

	public T get(int idx) {
		return getNode(idx).data;
	}

	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldValue = p.data;
		p.data = newVal;
		return oldValue;
	}

	public T remove(int idx) {
		return remove(getNode(idx));
	}

	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<T>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount--;

		return p.data;
	}

	private Node<T> getNode(int idx) {
		Node<T> p;
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++) {
				p = p.next;
			}
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}

		return p;
	}

	private class LinkedListIterator implements java.util.Iterator<T> {

		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next() {
			if (modCount != expectedModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}

			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		@Override
		public void remove() {
			if (modCount != expectedModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

}
